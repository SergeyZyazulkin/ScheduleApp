package reqlib.client;

public class ServerParameters {

    private String url;
    private Integer readTimeout;
    private Integer connectionTimeout;

    public ServerParameters(String url, Integer readTimeout, Integer connectionTimeout) {
        if (url == null) {
            throw new NullPointerException("url can't be null");
        }

        if (readTimeout != null && readTimeout < 0) {
            throw new IllegalArgumentException("readTimeout can't be < 0");
        }

        if (connectionTimeout != null && connectionTimeout < 0) {
            throw new IllegalArgumentException("connectionTimeout can't be < 0");
        }

        this.url = url;
        this.readTimeout = readTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    public String getUrl() {
        return url;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }
}
