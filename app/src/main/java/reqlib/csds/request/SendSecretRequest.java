package reqlib.csds.request;

public class SendSecretRequest extends BaseRequest {

    private String login;
    private String password;
    private String signKeyPart;
    private String publicSignKey;

    public SendSecretRequest(String imei, String login, String password, String signKeyPart, String publicSignKey) {
        super(imei);
        this.login = login;
        this.password = password;
        this.signKeyPart = signKeyPart;
        this.publicSignKey = publicSignKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignKeyPart() {
        return signKeyPart;
    }

    public void setSignKeyPart(String signKeyPart) {
        this.signKeyPart = signKeyPart;
    }

    public String getPublicSignKey() {
        return publicSignKey;
    }

    public void setPublicSignKey(String publicSignKey) {
        this.publicSignKey = publicSignKey;
    }
}
