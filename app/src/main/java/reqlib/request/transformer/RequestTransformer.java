package reqlib.request.transformer;

import java.util.Map;

import reqlib.request.transformer.OutputDataType;

public interface RequestTransformer<REQ> {

    OutputDataType getOutputDataType();
    byte[] transformIntoByteData(REQ requestData) throws Exception;
    String transformIntoStringData(REQ requestData) throws Exception;
    Map<String, String> transformIntoQueryData(REQ requestData) throws Exception;
}
