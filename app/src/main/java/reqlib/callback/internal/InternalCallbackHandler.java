package reqlib.callback.internal;

import reqlib.response.InternalResponse;

public interface InternalCallbackHandler<ERR, RES> {

    boolean onInternalResponse(InternalResponse<ERR, RES> internalResponse);
}
