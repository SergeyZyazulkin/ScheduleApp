package reqlib.response;

import reqlib.response.ResponseType;

public class InternalResponse<ERR, RES> {

    private reqlib.response.ResponseType responseType;
    private ERR error;
    private RES response;
    private Exception exception;

    public static <ERR, RES> reqlib.response.InternalResponse<ERR, RES> success(RES response) {
        return new reqlib.response.InternalResponse<>(reqlib.response.ResponseType.SUCCESS, null, response, null);
    }

    public static <ERR, RES> reqlib.response.InternalResponse<ERR, RES> error(ERR error) {
        return new reqlib.response.InternalResponse<>(reqlib.response.ResponseType.ERROR, error, null, null);
    }

    public static <ERR, RES> reqlib.response.InternalResponse<ERR, RES> connectionError(Exception exception) {
        return new reqlib.response.InternalResponse<>(reqlib.response.ResponseType.CONNECTION_ERROR, null, null, exception);
    }

    public static <ERR, RES> reqlib.response.InternalResponse<ERR, RES> internalError(Exception exception) {
        return new reqlib.response.InternalResponse<>(reqlib.response.ResponseType.INTERNAL_ERROR, null, null, exception);
    }

    public InternalResponse(reqlib.response.ResponseType responseType, ERR error,
                            RES response, Exception exception) {

        this.responseType = responseType == null ?
                reqlib.response.ResponseType.defaultResponseType() : responseType;

        this.error = error;
        this.response = response;
        this.exception = exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public ERR getError() {
        return error;
    }

    public RES getResponse() {
        return response;
    }

    public Exception getException() {
        return exception;
    }
}
