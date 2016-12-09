package reqlib.http;

import reqlib.client.ServerParameters;
import reqlib.http.DefaultHttpRequestHandler;
import reqlib.http.HttpRequestHandler;

public class HttpRequestHandlerFactory {

    public static HttpRequestHandler newDefaultHttpRequestHandler(
            ServerParameters serverParameters) {

        return new DefaultHttpRequestHandler(serverParameters);
    }
}
