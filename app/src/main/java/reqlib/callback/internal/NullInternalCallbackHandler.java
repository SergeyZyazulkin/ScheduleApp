package reqlib.callback.internal;

import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.response.InternalResponse;

public class NullInternalCallbackHandler<ERR, RES> implements InternalCallbackHandler<ERR, RES> {

    @Override
    public boolean onInternalResponse(InternalResponse<ERR, RES> internalResponse) {
        return true;
    }
}
