package reqlib.csds.response;

public class SendSecretResponse extends BaseResponse {

    private String textToSign;

    public String getTextToSign() {
        return textToSign;
    }

    public void setTextToSign(String textToSign) {
        this.textToSign = textToSign;
    }
}
