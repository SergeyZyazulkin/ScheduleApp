package reqlib.csds.request;

public abstract class SessionRequest extends SignRequest {

    protected String session;

    public SessionRequest(String imei, String sign, String session) {
        super(imei, sign);
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
