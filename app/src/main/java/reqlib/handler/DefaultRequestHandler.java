package reqlib.handler;

import android.os.AsyncTask;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import reqlib.Logger;
import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.callback.internal.InternalCallbackHandlerFactory;
import reqlib.client.ServerParameters;
import reqlib.exception.ConnectionException;
import reqlib.exception.InternalException;
import reqlib.handler.RequestHandler;
import reqlib.http.HttpRequestHandler;
import reqlib.http.HttpRequestHandlerFactory;
import reqlib.request.config.RequestConfig;
import reqlib.response.InternalResponse;

public class DefaultRequestHandler implements RequestHandler {

    private ConcurrentHashMap<UUID, RequestTask> performingRequests;
    private HttpRequestHandler httpRequestHandler;

    public DefaultRequestHandler(ServerParameters serverParameters) {
        this.httpRequestHandler =
                HttpRequestHandlerFactory.newDefaultHttpRequestHandler(serverParameters);

        this.performingRequests = new ConcurrentHashMap<>();
    }

    @Override
    public <REQ, ERR, RES> UUID execute(RequestConfig<REQ, ERR, RES> requestConfig,
                                        InternalCallbackHandler<ERR, RES> internalCallbackHandler) {

        UUID uuid = UUID.randomUUID();
        RequestTask<REQ, ERR, RES> requestTask = new RequestTask<>(uuid, internalCallbackHandler);
        performingRequests.put(uuid, requestTask);
        requestTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestConfig);
        return uuid;
    }

    @Override
    public boolean detach(UUID requestId) {
        if (requestId == null) {
            return false;
        }

        RequestTask requestTask = performingRequests.get(requestId);
        return requestTask != null && requestTask.cancel(true);
    }

    @Override
    public boolean repeat(UUID requestId) {
        if (requestId == null) {
            return false;
        }

        RequestTask requestTask = performingRequests.get(requestId);
        return requestTask != null && requestTask.repeat();
    }

    @Override
    public void detachAll() {
        Set<UUID> tasks =  performingRequests.keySet();

        for (UUID taskId : tasks) {
            detach(taskId);
        }
    }

    private class RequestTask<REQ, ERR, RES>
            extends AsyncTask<RequestConfig<REQ, ERR, RES>, Void, InternalResponse<ERR, RES>> {

        private UUID uuid;
        private InternalCallbackHandler<ERR, RES> internalCallbackHandler;
        private InternalResponse<ERR, RES> internalResponse;

        public RequestTask(UUID uuid, InternalCallbackHandler<ERR, RES> internalCallbackHandler) {
            this.uuid = uuid;

            this.internalCallbackHandler = internalCallbackHandler == null ?
                    InternalCallbackHandlerFactory.<ERR, RES>newNullInternalCallbackHandler() :
                    internalCallbackHandler;
        }

        @Override
        protected InternalResponse<ERR, RES> doInBackground(
                RequestConfig<REQ, ERR, RES>... requestConfigs) {

            try {
                Logger.logTaskRequest(uuid, "started");

                if (requestConfigs.length != 1) {
                    throw new InternalException();
                }

                return httpRequestHandler.execute(requestConfigs[0]);
            } catch (ConnectionException e) {
                Logger.logTaskRequest(uuid, "connection error", e);
                return InternalResponse.connectionError(e);
            } catch (Exception e) {
                Logger.logTaskRequest(uuid, "internal error", e);
                return InternalResponse.internalError(e);
            }
        }

        @Override
        protected void onPostExecute(InternalResponse<ERR, RES> res) {
            internalResponse = res;

            if (internalCallbackHandler.onInternalResponse(internalResponse)) {
                removeTask();
                Logger.logTaskRequest(uuid, "completed");
            } else {
                Logger.logTaskRequest(uuid, "skipped");
            }
        }

        @Override
        protected void onCancelled(InternalResponse<ERR, RES> internalResponse) {
            onCancelled();
        }

        @Override
        protected void onCancelled() {
            removeTask();
            Logger.logTaskRequest(uuid, "cancelled");
        }

        public boolean repeat() {
            boolean repeated = internalResponse != null
                    && internalCallbackHandler.onInternalResponse(internalResponse);

            if (repeated) {
                removeTask();
                Logger.logTaskRequest(uuid, "repeated");
            } else {
                Logger.logTaskRequest(uuid, "repeat skipped");
            }

            return repeated;
        }

        private void removeTask() {
            if (uuid != null) {
                performingRequests.remove(uuid);
            }
        }
    }
}
