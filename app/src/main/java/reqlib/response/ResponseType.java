package reqlib.response;

public enum ResponseType {

    SUCCESS,
    ERROR,
    CONNECTION_ERROR,
    INTERNAL_ERROR;

    public static reqlib.response.ResponseType defaultResponseType() {
        return INTERNAL_ERROR;
    }
}
