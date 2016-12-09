package reqlib.csds.response;

import java.io.Serializable;

public abstract class BaseResponse implements Serializable {

    protected String resultCode;
    protected String resultStr;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }
}
