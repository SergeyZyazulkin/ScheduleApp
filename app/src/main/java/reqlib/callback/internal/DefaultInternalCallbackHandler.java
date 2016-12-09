package reqlib.callback.internal;

import reqlib.Logger;
import reqlib.callback.client.ClientCallbackHandler;
import reqlib.callback.client.NullClientCallbackHandler;
import reqlib.response.InternalResponse;

public class DefaultInternalCallbackHandler<ERR, RES> implements InternalCallbackHandler<ERR, RES> {

    protected ClientCallbackHandler<ERR, RES> handler;

    public DefaultInternalCallbackHandler(ClientCallbackHandler<ERR, RES> handler) {
        this.handler = handler == null ? new NullClientCallbackHandler<ERR, RES>() : handler;
    }

    @Override
    public boolean onInternalResponse(InternalResponse<ERR, RES> internalResponse) {
        if (internalResponse == null) {
            Logger.logInternalError(
                    reqlib.callback.internal.DefaultInternalCallbackHandler.class,
            "internalResponse can't be null");

            return handler.onInternalError();
        }

        switch (internalResponse.getResponseType()) {
            case SUCCESS:
                if (internalResponse.getResponse() == null) {
                    Logger.logInternalError(
                            reqlib.callback.internal.DefaultInternalCallbackHandler.class,
                    "internalResponse has null response");

                    return handler.onInternalError();
                }

                return handler.onSuccess(internalResponse.getResponse());

            case ERROR:
                if (internalResponse.getError() == null) {
                    Logger.logInternalError(
                            reqlib.callback.internal.DefaultInternalCallbackHandler.class,
                    "internalResponse has null error");

                    return handler.onInternalError();
                }

                return handler.onError(internalResponse.getError());

            case CONNECTION_ERROR:
                return handler.onConnectionError();

            case INTERNAL_ERROR:
                return handler.onInternalError();

            default:
                Logger.logInternalError(
                        reqlib.callback.internal.DefaultInternalCallbackHandler. class,"unknown ResponseType");

                return handler.onInternalError();
        }
    }
}
