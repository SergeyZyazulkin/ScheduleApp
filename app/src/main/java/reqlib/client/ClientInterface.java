package reqlib.client;

import java.util.UUID;

import reqlib.BaseInterface;
import reqlib.callback.client.ClientCallbackHandler;
import reqlib.request.config.RequestConfig;

public interface ClientInterface extends BaseInterface {

    <REQ, ERR, RES> UUID execute(RequestConfig<REQ, ERR, RES> requestConfig,
                                 ClientCallbackHandler<ERR, RES> clientCallbackHandler);
}
