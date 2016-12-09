package reqlib.http;

import reqlib.request.config.RequestConfig;
import reqlib.response.InternalResponse;

public interface HttpRequestHandler {

    <REQ, ERR, RES> InternalResponse<ERR, RES> execute(
            RequestConfig<REQ, ERR, RES> requestConfig) throws Exception;
}
