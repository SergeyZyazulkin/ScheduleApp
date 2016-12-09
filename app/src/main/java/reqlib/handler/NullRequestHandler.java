package reqlib.handler;

import java.util.UUID;

import reqlib.Logger;
import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.handler.RequestHandler;
import reqlib.request.config.RequestConfig;
import reqlib.response.InternalResponse;

public class NullRequestHandler implements RequestHandler {

    @Override
    public <REQ, ERR, RES> UUID execute(RequestConfig<REQ, ERR, RES> requestConfig,
                                        InternalCallbackHandler<ERR, RES> internalCallbackHandler) {

        if (internalCallbackHandler == null) {
            Logger.logInternalError(
                    reqlib.handler.NullRequestHandler.class, "internalCallbackHandler can't be null");
        } else {
            Logger.logInternalError(reqlib.handler.NullRequestHandler.class, "request handling unsupported");

            internalCallbackHandler.onInternalResponse(
                    InternalResponse.<ERR, RES>internalError(null));
        }

        return null;
    }

    @Override
    public boolean detach(UUID requestId) {
        return false;
    }

    @Override
    public boolean repeat(UUID requestId) {
        return false;
    }

    @Override
    public void detachAll() {
    }
}
