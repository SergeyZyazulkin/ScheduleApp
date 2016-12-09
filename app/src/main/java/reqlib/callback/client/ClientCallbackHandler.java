package reqlib.callback.client;

public interface ClientCallbackHandler<ERR, RES> {

    boolean onSuccess(RES response);
    boolean onError(ERR error);
    boolean onConnectionError();
    boolean onInternalError();
}
