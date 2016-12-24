package reqlib.hmi;

import java.util.ArrayList;
import java.util.UUID;

import reqlib.callback.client.ClientCallbackHandler;
import reqlib.client.DefaultClientInterface;
import reqlib.client.ServerParameters;
import reqlib.handler.RequestHandlerFactory;
import reqlib.hmi.request.AuthenticateRequest;
import reqlib.hmi.request.CreateRequest;
import reqlib.hmi.request.FindRequest;
import reqlib.hmi.request.RefreshRequest;
import reqlib.hmi.request.SignUpRequest;
import reqlib.hmi.request.UpdateRequest;
import reqlib.hmi.response.AuthenticateResponse;
import reqlib.hmi.response.ScheduleJson;
import reqlib.hmi.response.SignUpResponse;
import reqlib.request.config.RequestConfig;
import reqlib.request.transformer.RequestTransformerFactory;
import reqlib.response.transformer.ResponseTransformerFactory;

public class ScheduleClientInterface extends DefaultClientInterface {

    public ScheduleClientInterface(ServerParameters serverParameters) {
        super(RequestHandlerFactory.newDefaultRequestHandler(serverParameters));
    }

    public UUID signUp(SignUpRequest signUpRequest,
                          ClientCallbackHandler<Void, SignUpResponse> clientCallbackHandler) {

        RequestConfig<SignUpRequest, Void, SignUpResponse> requestConfig =
                new RequestConfig<>(signUpRequest,
                        ScheduleRequestParameters.createPostRequestParameters("account/signup"),
                        RequestTransformerFactory.<SignUpRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, SignUpResponse.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID authenticate(AuthenticateRequest authenticateRequest,
                       ClientCallbackHandler<Void, AuthenticateResponse> clientCallbackHandler) {

        RequestConfig<AuthenticateRequest, Void, AuthenticateResponse> requestConfig =
                new RequestConfig<>(authenticateRequest,
                        ScheduleRequestParameters.createPostRequestParameters("account/authenticate"),
                        RequestTransformerFactory.<AuthenticateRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, AuthenticateResponse.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID create(CreateRequest createRequest,
                             ClientCallbackHandler<Void, ScheduleJson> clientCallbackHandler) {

        RequestConfig<CreateRequest, Void, ScheduleJson> requestConfig =
                new RequestConfig<>(createRequest,
                        ScheduleRequestParameters.createAuthPostRequestParameters("schedule"),
                        RequestTransformerFactory.<CreateRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, ScheduleJson.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID update(UpdateRequest updateRequest,
                       ClientCallbackHandler<Void, ScheduleJson> clientCallbackHandler) {

        RequestConfig<UpdateRequest, Void, ScheduleJson> requestConfig =
                new RequestConfig<>(updateRequest,
                        ScheduleRequestParameters.createAuthPutRequestParameters("schedule"),
                        RequestTransformerFactory.<UpdateRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, ScheduleJson.class)
                );

        requestConfig.setAdd(true);
        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID find(FindRequest findRequest,
                       ClientCallbackHandler<Void, ScheduleJson[]> clientCallbackHandler) {

        RequestConfig<FindRequest, Void, ScheduleJson[]> requestConfig =
                new RequestConfig<>(findRequest,
                        ScheduleRequestParameters.createPostRequestParameters("schedule/find"),
                        RequestTransformerFactory.<FindRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, ScheduleJson[].class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID refresh(RefreshRequest refreshRequest,
                     ClientCallbackHandler<Void, ScheduleJson[]> clientCallbackHandler) {

        RequestConfig<RefreshRequest, Void, ScheduleJson[]> requestConfig =
                new RequestConfig<>(refreshRequest,
                        ScheduleRequestParameters.createPostRequestParameters("schedule/find"),
                        RequestTransformerFactory.<RefreshRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, ScheduleJson[].class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }
}
