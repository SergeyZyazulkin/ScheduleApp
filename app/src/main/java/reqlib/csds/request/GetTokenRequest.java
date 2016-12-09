package reqlib.csds.request;

public class GetTokenRequest extends SignRequest {

    private String login;
    private String textToSign;

    public GetTokenRequest(String imei, String login, String textToSign) {
        super(imei, null);
        this.login = login;
        this.textToSign = textToSign;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTextToSign() {
        return textToSign;
    }

    public void setTextToSign(String textToSign) {
        this.textToSign = textToSign;
    }
}
