package reqlib.http.util;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Map;

import reqlib.Logger;
import reqlib.client.ServerParameters;
import reqlib.request.config.RequestParameters;

public class RequestParametersFiller {

    public static void fillParameters(
            HttpURLConnection httpURLConnection,
            ServerParameters serverParameters,
            RequestParameters requestParameters) throws ProtocolException {

        if (httpURLConnection != null) {
            if (serverParameters != null) {
                httpURLConnection.setConnectTimeout(serverParameters.getConnectionTimeout());
                httpURLConnection.setReadTimeout(serverParameters.getReadTimeout());
            }

            if (requestParameters != null) {
                httpURLConnection.setRequestMethod(requestParameters.getRequestType().name());

                for (Map.Entry<String, String> header : requestParameters.getHeaders().entrySet()) {
                    if (header.getKey() != null && header.getValue() != null) {
                        httpURLConnection.setRequestProperty(header.getKey(), header.getValue());
                    } else {
                        Logger.logInternalError(reqlib.http.util.RequestParametersFiller.class,
                                "header with null key and/or value");
                    }
                }
            }
        }
    }
}
