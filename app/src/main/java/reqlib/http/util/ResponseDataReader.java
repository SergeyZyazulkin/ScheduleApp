package reqlib.http.util;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

import reqlib.Logger;
import reqlib.exception.InternalException;
import reqlib.response.InternalResponse;
import reqlib.response.transformer.ResponseTransformer;

public class ResponseDataReader {

    public static <ERR, RES> InternalResponse<ERR, RES> readResponse(
            HttpURLConnection httpURLConnection,
            ResponseTransformer<ERR, RES> responseTransformer) throws Exception {

        if (httpURLConnection != null && responseTransformer != null) {
            InputStream is = null;

            try {
                is = httpURLConnection.getInputStream();

                switch (responseTransformer.getInputDataType()) {
                    case NULL:
                        return InternalResponse.success(null);

                    case BYTE:
                        byte[] byteResponseData = IOUtils.toByteArray(is);
                        return responseTransformer.transformByteData(byteResponseData);

                    case STRING:
                        String stringResponseData = IOUtils.toString(is, Charset.forName("UTF-8"));
                        Logger.logDebugInfo("Response: \n" + stringResponseData);
                        return responseTransformer.transformStringData(stringResponseData);

                    default:
                        throw new InternalException("unknown inputDataType - " +
                                responseTransformer.getInputDataType().name());
                }
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        } else {
            Logger.logInternalError(reqlib.http.util.ResponseDataReader.class, "null argument(s)");
            return InternalResponse.internalError(null);
        }
    }
}
