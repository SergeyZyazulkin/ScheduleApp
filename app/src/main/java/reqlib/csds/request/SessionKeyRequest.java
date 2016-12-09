package reqlib.csds.request;

import reqlib.csds.request.SessionRequest;

public class SessionKeyRequest extends SessionRequest {

    public SessionKeyRequest(String imei, String session) {
        super(imei, null, session);
    }
}
