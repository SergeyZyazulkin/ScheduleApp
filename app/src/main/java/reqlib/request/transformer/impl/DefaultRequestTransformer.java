package reqlib.request.transformer.impl;

import java.util.Map;

import reqlib.request.transformer.OutputDataType;
import reqlib.request.transformer.RequestTransformer;

public class DefaultRequestTransformer<REQ> implements RequestTransformer<REQ> {

    private OutputDataType outputDataType;

    public DefaultRequestTransformer() {
        outputDataType = OutputDataType.NULL;
    }

    protected DefaultRequestTransformer(OutputDataType outputDataType) {
        this.outputDataType = outputDataType == null ?
                OutputDataType.defaultOutputDataType() : outputDataType;
    }

    @Override
    public OutputDataType getOutputDataType() {
        return outputDataType;
    }

    @Override
    public byte[] transformIntoByteData(REQ requestData) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String transformIntoStringData(REQ requestData) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String> transformIntoQueryData(REQ requestData) throws Exception {
        throw new UnsupportedOperationException();
    }
}
