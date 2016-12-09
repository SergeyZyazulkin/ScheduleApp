package reqlib.request.config;

import java.util.HashMap;
import java.util.Map;

import reqlib.request.config.RequestType;

public class RequestParameters {

    private String path;
    private Map<String, String> headers;
    private reqlib.request.config.RequestType requestType;

    public RequestParameters(String path, Map<String, String> headers, reqlib.request.config.RequestType requestType) {
        this.path = path;
        this.headers = headers == null ? new HashMap<String, String>() : headers;
        this.requestType = requestType == null ? reqlib.request.config.RequestType.defaultRequestType() : requestType;
    }

    public static reqlib.request.config.RequestParameters NullObject() {
        return new reqlib.request.config.RequestParameters(null, null, null);
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
