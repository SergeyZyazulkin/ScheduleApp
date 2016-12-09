package reqlib;

import java.util.UUID;

public interface BaseInterface {

    boolean detach(UUID requestId);
    boolean repeat(UUID requestId);
    void detachAll();
}
