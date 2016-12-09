package reqlib.response.transformer;

import reqlib.response.transformer.impl.DefaultResponseTransformer;
import reqlib.response.transformer.impl.JsonResponseTransformer;

public class ResponseTransformerFactory {

    public static <ERR, RES> ResponseTransformer<ERR, RES> newDefaultResponseTransformer() {
        return new DefaultResponseTransformer<>();
    }

    public static <ERR, RES> ResponseTransformer<ERR, RES> newJsonResponseTransformer(Class<ERR> cl1, Class<RES> cl2) {
        return new JsonResponseTransformer<>(cl1, cl2);
    }

}
