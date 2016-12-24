package reqlib.request.config;

public enum RequestType {

    GET,
    POST,
    PUT;

    public static reqlib.request.config.RequestType defaultRequestType() {
        return GET;
    }
}