package reqlib.handler;

import java.util.UUID;

import reqlib.BaseInterface;
import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.request.config.RequestConfig;

public interface RequestHandler extends BaseInterface {

    <REQ, ERR, RES> UUID execute(RequestConfig<REQ, ERR, RES> requestConfig,
                                 InternalCallbackHandler<ERR, RES> internalCallbackHandler);
}
