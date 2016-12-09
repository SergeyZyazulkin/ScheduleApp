package reqlib.client;

import java.util.UUID;

import reqlib.callback.client.ClientCallbackHandler;
import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.callback.internal.InternalCallbackHandlerFactory;
import reqlib.client.ClientInterface;
import reqlib.handler.RequestHandler;
import reqlib.handler.RequestHandlerFactory;
import reqlib.request.config.RequestConfig;

public abstract class DefaultClientInterface implements ClientInterface {

    private RequestHandler requestHandler;

    protected DefaultClientInterface(RequestHandler requestHandler) {
        this.requestHandler = requestHandler == null ?
                RequestHandlerFactory.newNullRequestHandler() : requestHandler;
    }

    @Override
    public <REQ, ERR, RES> UUID execute(RequestConfig<REQ, ERR, RES> requestConfig,
                                           ClientCallbackHandler<ERR, RES> clientCallbackHandler) {

        InternalCallbackHandler<ERR, RES> internalCallbackHandler =
                InternalCallbackHandlerFactory.newDefaultInternalCallbackHandler(
                        clientCallbackHandler);

        return requestHandler.execute(requestConfig, internalCallbackHandler);
    }

    @Override
    public boolean detach(UUID requestId) {
        return requestHandler.detach(requestId);
    }

    @Override
    public void detachAll() {
        requestHandler.detachAll();
    }

    @Override
    public boolean repeat(UUID requestId) {
        return requestHandler.repeat(requestId);
    }
}
