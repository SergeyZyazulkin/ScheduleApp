package reqlib.csds.response;

public class GetTokenResponse extends BaseResponse {

    private String session;
    private Long lifetime;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }
}
