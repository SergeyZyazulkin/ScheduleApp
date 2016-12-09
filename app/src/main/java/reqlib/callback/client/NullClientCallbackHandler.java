package reqlib.callback.client;

public class NullClientCallbackHandler<ERR, RES> implements ClientCallbackHandler<ERR, RES> {

    @Override
    public boolean onSuccess(RES response) {
        return true;
    }

    @Override
    public boolean onError(ERR error) {
        return true;
    }

    @Override
    public boolean onConnectionError() {
        return true;
    }

    @Override
    public boolean onInternalError() {
        return true;
    }
}
