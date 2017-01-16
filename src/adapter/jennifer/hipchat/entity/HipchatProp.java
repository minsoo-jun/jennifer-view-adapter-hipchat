package adapter.jennifer.hipchat.entity;

/**
 * Created by minsoo.jun on 12/27/16.
 */
public class HipchatProp {
    private String token;
    private String apiId;
    private String jenniferUrl;
    private String hipchatApiServer;
    private String proxyHost;
    private String proxyPort;
    private String isProxy;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(String isProxy) {
        this.isProxy = isProxy;
    }

    /**
     * @return
     */
    public String getHipchatUrl(){
        return this.getHipchatApiServer() + this.apiId + "/notification?auth_token=" + this.token;
    }
    public String getHipchatApiServer() {
        if(this.hipchatApiServer == null){
            this.hipchatApiServer = "https://api.hipchat.com/v2/room/";
        }
        return hipchatApiServer;
    }

    public void setHipchatApiServer(String hipchatApiServer) {
        this.hipchatApiServer = hipchatApiServer;
    }

    public String getJenniferUrl() {
        return jenniferUrl;
    }

    public void setJenniferUrl(String jenniferUrl) {
        this.jenniferUrl = jenniferUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiId() { return apiId;}

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
}
