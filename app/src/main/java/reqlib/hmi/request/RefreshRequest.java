package reqlib.hmi.request;

public class RefreshRequest {

    private String _id;

    public RefreshRequest(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
