package reqlib.csds.response;

import reqlib.csds.response.BaseResponse;

public class FileResponse extends BaseResponse {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
