package reqlib.csds;

import java.util.HashMap;

import reqlib.request.config.RequestParameters;
import reqlib.request.config.RequestType;

public class CSDSRequestParameters {

    public static RequestParameters createRequestParameters(String path) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new RequestParameters(path, headers, RequestType.POST);
    }
}
