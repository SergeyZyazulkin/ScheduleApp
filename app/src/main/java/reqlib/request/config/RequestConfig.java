package reqlib.request.config;

import reqlib.request.config.RequestParameters;
import reqlib.request.transformer.RequestTransformer;
import reqlib.request.transformer.RequestTransformerFactory;
import reqlib.response.transformer.ResponseTransformer;
import reqlib.response.transformer.ResponseTransformerFactory;

public class RequestConfig<REQ, ERR, RES> {

    private REQ requestData;
    private RequestParameters requestParameters;
    private RequestTransformer<REQ> requestTransformer;
    private ResponseTransformer<ERR, RES> responseTransformer;
    private boolean add;

    public RequestConfig(REQ requestData,
                         RequestParameters requestParameters,
                         RequestTransformer<REQ> requestTransformer,
                         ResponseTransformer<ERR, RES> responseTransformer) {

        this.requestData = requestData;

        this.requestParameters = requestParameters == null ?
                RequestParameters.NullObject() : requestParameters;

        this.requestTransformer = requestTransformer == null ?
                RequestTransformerFactory.<REQ>newDefaultRequestTransformer() : requestTransformer;

        this.responseTransformer = responseTransformer == null ?
                ResponseTransformerFactory.<ERR, RES>newDefaultResponseTransformer() :
                responseTransformer;
    }

    public static <REQ, ERR, RES> reqlib.request.config.RequestConfig<REQ, ERR, RES> NullObject() {
        return new reqlib.request.config.RequestConfig<>(null, null, null, null);
    }

    public REQ getRequestData() {
        return requestData;
    }

    public RequestParameters getRequestParameters() {
        return requestParameters;
    }

    public RequestTransformer<REQ> getRequestTransformer() {
        return requestTransformer;
    }

    public ResponseTransformer<ERR, RES> getResponseTransformer() {
        return responseTransformer;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
