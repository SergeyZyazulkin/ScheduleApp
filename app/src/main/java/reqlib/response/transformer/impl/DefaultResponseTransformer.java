package reqlib.response.transformer.impl;

import java.lang.reflect.ParameterizedType;

import reqlib.response.InternalResponse;
import reqlib.response.transformer.InputDataType;
import reqlib.response.transformer.ResponseTransformer;

public class DefaultResponseTransformer<ERR, RES> implements ResponseTransformer<ERR, RES> {

    private InputDataType inputDataType;

    public DefaultResponseTransformer() {
        inputDataType = InputDataType.NULL;
    }

    protected DefaultResponseTransformer(InputDataType inputDataType) {
        this.inputDataType = inputDataType == null ?
                InputDataType.defaultInputDataType() : inputDataType;
    }

    @Override
    public InputDataType getInputDataType() {
        return inputDataType;
    }

    @Override
    public InternalResponse<ERR, RES> transformByteData(byte[] responseData) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public InternalResponse<ERR, RES> transformStringData(String responseData) throws Exception {
        throw new UnsupportedOperationException();
    }

    protected Class getParameterClass(int index) {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[index];
    }
}
