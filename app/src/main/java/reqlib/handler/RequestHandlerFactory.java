package reqlib.handler;

import reqlib.client.ServerParameters;
import reqlib.handler.DefaultRequestHandler;
import reqlib.handler.RequestHandler;

public class RequestHandlerFactory {

    public static RequestHandler newDefaultRequestHandler(ServerParameters serverParameters) {
        return new DefaultRequestHandler(serverParameters);
    }

    public static RequestHandler newNullRequestHandler() {
        return new NullRequestHandler();
    }
}
