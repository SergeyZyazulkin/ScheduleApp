package reqlib.http.util;

import android.net.Uri;

import java.net.URL;
import java.util.Map;
import java.util.Set;

import reqlib.Logger;
import reqlib.client.ServerParameters;
import reqlib.request.config.RequestConfig;
import reqlib.request.transformer.OutputDataType;

public class UrlBuilder {

    public static <REQ, ERR, RES> URL buildUrl(
            ServerParameters serverParameters,
            RequestConfig<REQ, ERR, RES> requestConfig) throws Exception {

        if (serverParameters == null) {
            throw new NullPointerException("serverParameters can't be null");
        }

        Uri.Builder uriBuilder = Uri.parse(serverParameters.getUrl()).buildUpon();
        String path = requestConfig.getRequestParameters().getPath();

        if (path != null && !path.isEmpty()) {
            uriBuilder.appendPath(path);
        }

        if (requestConfig.getRequestTransformer().getOutputDataType() == OutputDataType.QUERY) {
            Set<Map.Entry<String, String>> queryParameters =
                    requestConfig.getRequestTransformer().transformIntoQueryData(
                            requestConfig.getRequestData()).entrySet();

            for (Map.Entry<String, String> queryParameter : queryParameters) {
                uriBuilder.appendQueryParameter(queryParameter.getKey(), queryParameter.getValue());
            }
        }

        String url = uriBuilder.build().toString();
        Logger.logDebugInfo("URL: " + url);
        return new URL(url);
    }
}
