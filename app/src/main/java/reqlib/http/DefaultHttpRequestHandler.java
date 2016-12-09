package reqlib.http;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import reqlib.client.ServerParameters;
import reqlib.exception.ConnectionException;
import reqlib.http.HttpRequestHandler;
import reqlib.http.util.RequestDataWriter;
import reqlib.http.util.RequestParametersFiller;
import reqlib.http.util.ResponseDataReader;
import reqlib.http.util.UrlBuilder;
import reqlib.request.config.RequestConfig;
import reqlib.response.InternalResponse;

public class DefaultHttpRequestHandler implements HttpRequestHandler {

    private ServerParameters serverParameters;

    public DefaultHttpRequestHandler(ServerParameters serverParameters) {
        if (serverParameters == null) {
            throw new NullPointerException("serverParameters can't be null");
        }

        this.serverParameters = serverParameters;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllX509TrustManager()}, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String string, SSLSession ssls) {
                    return true;
                }
            });
        } catch (Exception e) {
            Log.e("ProtectedNotepad", "Error", e);
            throw new IllegalStateException();
        }
    }

    @Override
    public <REQ, ERR, RES> InternalResponse<ERR, RES> execute(
            RequestConfig<REQ, ERR, RES> requestConfig) throws Exception {

        if (requestConfig == null) {
            requestConfig = RequestConfig.NullObject();
        }

        URL url = UrlBuilder.buildUrl(serverParameters, requestConfig);

        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            RequestParametersFiller.fillParameters(
                    httpsURLConnection, serverParameters, requestConfig.getRequestParameters());

            RequestDataWriter.writeRequest(httpsURLConnection, requestConfig);
            httpsURLConnection.connect();
            int responseCode = httpsURLConnection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ConnectionException("Response code: " + responseCode);
            }

            return ResponseDataReader.readResponse(
                    httpsURLConnection, requestConfig.getResponseTransformer());
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    private class TrustAllX509TrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
                                       String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
                                       String authType) {
        }
    }
}
