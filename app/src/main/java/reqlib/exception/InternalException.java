package reqlib.exception;

import reqlib.exception.ReqLibException;

public class InternalException extends ReqLibException {

    public InternalException() {
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }
}
