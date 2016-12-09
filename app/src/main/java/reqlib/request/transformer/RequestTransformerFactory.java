package reqlib.request.transformer;

import reqlib.request.transformer.RequestTransformer;
import reqlib.request.transformer.impl.DefaultRequestTransformer;
import reqlib.request.transformer.impl.JsonRequestTransformer;

public class RequestTransformerFactory {

    public static <REQ> RequestTransformer<REQ> newDefaultRequestTransformer() {
        return new DefaultRequestTransformer<>();
    }

    public static <REQ> RequestTransformer<REQ> newJsonRequestTransformer() {
        return new JsonRequestTransformer<>();
    }
}
