package reqlib.csds.request;

public class GetSecretRequest extends BaseRequest {

    private String login;
    private String password;

    public GetSecretRequest(String imei, String login, String password) {
        super(imei);
        this.login = login;
        this.password = password;
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
}
