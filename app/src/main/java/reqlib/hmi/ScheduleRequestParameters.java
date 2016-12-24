package reqlib.hmi;

import java.util.HashMap;

import reqlib.request.config.RequestParameters;
import reqlib.request.config.RequestType;
import zsp.android.sa.data.AppData;

public class ScheduleRequestParameters {

    public static RequestParameters createPostRequestParameters(String path) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new RequestParameters(path, headers, RequestType.POST);
    }

    public static RequestParameters createAuthPostRequestParameters(String path) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", AppData.token);
        return new RequestParameters(path, headers, RequestType.POST);
    }

    public static RequestParameters createAuthPutRequestParameters(String path) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", AppData.token);
        return new RequestParameters(path, headers, RequestType.PUT);
    }
}
