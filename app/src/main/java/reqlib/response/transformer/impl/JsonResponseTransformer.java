package reqlib.response.transformer.impl;

import com.google.gson.Gson;

import reqlib.response.InternalResponse;
import reqlib.response.transformer.InputDataType;
import reqlib.response.transformer.impl.DefaultResponseTransformer;

public class JsonResponseTransformer<ERR, RES> extends DefaultResponseTransformer<ERR, RES> {

    private Class<ERR> errClass;
    private Class<RES> resClass;

    public JsonResponseTransformer(Class<ERR> cl1, Class<RES> cl2) {
        super(InputDataType.STRING);
        errClass = cl1;
        resClass = cl2;
    }

    @SuppressWarnings("unchecked")
    @Override
    public InternalResponse<ERR, RES> transformStringData(String responseData) throws Exception {
        RES res = new Gson().fromJson(responseData, resClass);
        return InternalResponse.success(res);
    }
}
