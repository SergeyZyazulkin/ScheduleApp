package reqlib.request.transformer.impl;

import com.google.gson.Gson;

import reqlib.request.transformer.OutputDataType;

public class JsonRequestTransformer<REQ> extends DefaultRequestTransformer<REQ> {

    public JsonRequestTransformer() {
        super(OutputDataType.STRING);
    }

    @Override
    public String transformIntoStringData(REQ requestData) throws Exception {
        if (requestData == null) {
            return null;
        }

        Gson gson = new Gson();
        return gson.toJson(requestData);
    }
}
