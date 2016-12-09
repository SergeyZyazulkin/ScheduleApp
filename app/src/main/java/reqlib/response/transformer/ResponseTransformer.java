package reqlib.response.transformer;

import reqlib.response.InternalResponse;
import reqlib.response.transformer.InputDataType;

public interface ResponseTransformer<ERR, RES> {

    InputDataType getInputDataType();
    InternalResponse<ERR, RES> transformByteData(byte[] responseData) throws Exception;
    InternalResponse<ERR, RES> transformStringData(String responseData) throws Exception;
}
