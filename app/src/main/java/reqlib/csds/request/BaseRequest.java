package reqlib.csds.request;

public abstract class BaseRequest {

    protected String imei;

    public BaseRequest(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
