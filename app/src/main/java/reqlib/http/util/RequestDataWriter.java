package reqlib.http.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

import reqlib.Logger;
import reqlib.exception.InternalException;
import reqlib.request.config.RequestConfig;

public class RequestDataWriter {

    public static <REQ, ERR, RES> void writeRequest(
            HttpURLConnection httpURLConnection,
            RequestConfig<REQ, ERR, RES> requestConfig) throws Exception {

        if (httpURLConnection != null && requestConfig != null) {
            switch (requestConfig.getRequestTransformer().getOutputDataType()) {
                case NULL:
                case QUERY:
                    return;

                case BYTE:
                    byte[] byteRequestData = requestConfig.getRequestTransformer().
                            transformIntoByteData(requestConfig.getRequestData());

                    if (byteRequestData != null) {
                        writeByteData(httpURLConnection, byteRequestData);
                    }

                    return;

                case STRING:
                    String stringRequestData = requestConfig.getRequestTransformer().
                            transformIntoStringData(requestConfig.getRequestData());

                    if (stringRequestData != null) {
                        writeStringData(httpURLConnection, stringRequestData);
                    }

                    return;

                default:
                    throw new InternalException("unknown outputDataType - " +
                            requestConfig.getRequestTransformer().getOutputDataType().name());
            }
        }
    }

    private static void writeByteData(
            HttpURLConnection httpURLConnection, byte[] requestData) throws IOException {

        httpURLConnection.setDoOutput(true);
        DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
        dos.write(requestData);
        dos.flush();
        dos.close();
    }

    private static void writeStringData(
            HttpURLConnection httpURLConnection, String requestData) throws IOException {

        Logger.logDebugInfo("Request: \n" + requestData);
        byte[] byteRequestData = requestData.getBytes(Charset.forName("UTF-8"));
        writeByteData(httpURLConnection, byteRequestData);
    }
}
