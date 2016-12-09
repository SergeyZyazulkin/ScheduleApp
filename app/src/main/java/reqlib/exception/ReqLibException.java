package reqlib.exception;

public class ReqLibException extends Exception {

    public ReqLibException() {
    }

    public ReqLibException(String message) {
        super(message);
    }

    public ReqLibException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReqLibException(Throwable cause) {
        super(cause);
    }
}
