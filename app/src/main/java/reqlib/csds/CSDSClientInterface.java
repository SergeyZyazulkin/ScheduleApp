package reqlib.csds;

import java.util.UUID;

import reqlib.callback.client.ClientCallbackHandler;
import reqlib.client.DefaultClientInterface;
import reqlib.client.ServerParameters;
import reqlib.csds.request.FileRequest;
import reqlib.csds.request.GetSecretRequest;
import reqlib.csds.request.GetTokenRequest;
import reqlib.csds.request.PublicKeyRequest;
import reqlib.csds.request.SendSecretRequest;
import reqlib.csds.request.SessionKeyRequest;
import reqlib.csds.response.FileResponse;
import reqlib.csds.response.GetSecretResponse;
import reqlib.csds.response.GetTokenResponse;
import reqlib.csds.response.PublicKeyResponse;
import reqlib.csds.response.SendSecretResponse;
import reqlib.csds.response.SessionKeyResponse;
import reqlib.handler.RequestHandlerFactory;
import reqlib.request.config.RequestConfig;
import reqlib.request.transformer.RequestTransformerFactory;
import reqlib.response.transformer.ResponseTransformerFactory;

public class CSDSClientInterface extends DefaultClientInterface {

    public CSDSClientInterface(ServerParameters serverParameters) {
        super(RequestHandlerFactory.newDefaultRequestHandler(serverParameters));
    }

    public UUID getSecret(GetSecretRequest getSecretRequest,
                          ClientCallbackHandler<Void, GetSecretResponse> clientCallbackHandler) {

        RequestConfig<GetSecretRequest, Void, GetSecretResponse> requestConfig =
                new RequestConfig<>(getSecretRequest,
                        CSDSRequestParameters.createRequestParameters("GetSecret"),
                        RequestTransformerFactory.<GetSecretRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, GetSecretResponse.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID sendSecret(SendSecretRequest sendSecretRequest,
                          ClientCallbackHandler<Void, SendSecretResponse> clientCallbackHandler) {

        RequestConfig<SendSecretRequest, Void, SendSecretResponse> requestConfig =
                new RequestConfig<>(sendSecretRequest,
                        CSDSRequestParameters.createRequestParameters("SendSecret"),
                        RequestTransformerFactory.<SendSecretRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, SendSecretResponse.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID getToken(GetTokenRequest getTokenRequest,
                           ClientCallbackHandler<Void, GetTokenResponse> clientCallbackHandler) {

        RequestConfig<GetTokenRequest, Void, GetTokenResponse> requestConfig =
                new RequestConfig<>(getTokenRequest,
                        CSDSRequestParameters.createRequestParameters("GetToken"),
                        RequestTransformerFactory.<GetTokenRequest>newJsonRequestTransformer(),
                        ResponseTransformerFactory.newJsonResponseTransformer(
                                Void.class, GetTokenResponse.class)
                );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID file(FileRequest fileRequest,
                     ClientCallbackHandler<Void, FileResponse> clientCallbackHandler) {

        RequestConfig<FileRequest, Void, FileResponse> requestConfig = new RequestConfig<>(
                fileRequest, CSDSRequestParameters.createRequestParameters("File"),
                RequestTransformerFactory.<FileRequest>newJsonRequestTransformer(),
                ResponseTransformerFactory.newJsonResponseTransformer(
                        Void.class, FileResponse.class)
        );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID publicKey(PublicKeyRequest publicKeyRequest,
                          ClientCallbackHandler<Void, PublicKeyResponse> clientCallbackHandler) {

        RequestConfig<PublicKeyRequest, Void, PublicKeyResponse> requestConfig = new RequestConfig<>(
                publicKeyRequest, CSDSRequestParameters.createRequestParameters("PublicKey"),
                RequestTransformerFactory.<PublicKeyRequest>newJsonRequestTransformer(),
                ResponseTransformerFactory.newJsonResponseTransformer(
                        Void.class, PublicKeyResponse.class)
        );

        return execute(requestConfig, clientCallbackHandler);
    }

    public UUID sessionKey(SessionKeyRequest sessionKeyRequest,
                           ClientCallbackHandler<Void, SessionKeyResponse> clientCallbackHandler) {

        RequestConfig<SessionKeyRequest, Void, SessionKeyResponse> requestConfig = new RequestConfig<>(
                sessionKeyRequest, CSDSRequestParameters.createRequestParameters("SessionKey"),
                RequestTransformerFactory.<SessionKeyRequest>newJsonRequestTransformer(),
                ResponseTransformerFactory.newJsonResponseTransformer(
                        Void.class, SessionKeyResponse.class)
        );

        return execute(requestConfig, clientCallbackHandler);
    }
}
