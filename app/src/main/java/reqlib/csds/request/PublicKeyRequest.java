package reqlib.csds.request;

import reqlib.csds.request.SessionRequest;

public class PublicKeyRequest extends SessionRequest {

    private String publicKey;

    public PublicKeyRequest(String imei, String session, String publicKey) {
        super(imei, null, session);
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
