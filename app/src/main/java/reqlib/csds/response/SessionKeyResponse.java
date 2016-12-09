package reqlib.csds.response;

import reqlib.csds.response.BaseResponse;

public class SessionKeyResponse extends BaseResponse {

    private String sessionKey;
    private Long lifetime;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }
}
