package reqlib.csds.response;

public class GetSecretResponse extends BaseResponse {

    private String signKeyPart;
    private String storageKeyPart;
    private String textToSign;
    private String storageKey;

    public String getSignKeyPart() {
        return signKeyPart;
    }

    public void setSignKeyPart(String signKeyPart) {
        this.signKeyPart = signKeyPart;
    }

    public String getStorageKeyPart() {
        return storageKeyPart;
    }

    public void setStorageKeyPart(String storageKeyPart) {
        this.storageKeyPart = storageKeyPart;
    }

    public String getTextToSign() {
        return textToSign;
    }

    public void setTextToSign(String textToSign) {
        this.textToSign = textToSign;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }
}
