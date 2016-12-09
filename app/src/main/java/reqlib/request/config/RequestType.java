package reqlib.request.config;

public enum RequestType {

    GET,
    POST;

    public static reqlib.request.config.RequestType defaultRequestType() {
        return GET;
    }
}