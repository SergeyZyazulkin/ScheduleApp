package reqlib.csds.request;

import reqlib.csds.request.SessionRequest;

public class FileRequest extends SessionRequest {

    private String fileName;

    public FileRequest(String imei, String session, String fileName) {
        super(imei, null, session);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
