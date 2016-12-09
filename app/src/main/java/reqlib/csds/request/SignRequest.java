package reqlib.csds.request;

public abstract class SignRequest extends BaseRequest {

    private String sign;

    public SignRequest(String imei, String sign) {
        super(imei);
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
