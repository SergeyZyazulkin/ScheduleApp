package reqlib.callback.internal;

import reqlib.callback.client.ClientCallbackHandler;
import reqlib.callback.internal.DefaultInternalCallbackHandler;
import reqlib.callback.internal.InternalCallbackHandler;
import reqlib.callback.internal.NullInternalCallbackHandler;

public class InternalCallbackHandlerFactory {

    public static <ERR, RES> InternalCallbackHandler<ERR, RES> newDefaultInternalCallbackHandler(
            ClientCallbackHandler<ERR, RES> clientCallbackHandler) {

        return new DefaultInternalCallbackHandler<>(clientCallbackHandler);
    }

    public static <ERR, RES> InternalCallbackHandler<ERR, RES> newNullInternalCallbackHandler() {
        return new NullInternalCallbackHandler<>();
    }
}
