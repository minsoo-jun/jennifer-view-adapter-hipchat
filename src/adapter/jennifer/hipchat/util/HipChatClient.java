package adapter.jennifer.hipchat.util;

import adapter.jennifer.hipchat.entity.HipchatMessage;
import adapter.jennifer.hipchat.entity.HipchatProp;
import com.jennifersoft.view.adapter.util.LogUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by minsoo.jun on 1/13/17.
 */
public class HipChatClient {
    /**
     * Default connection time out value
     */
    private final int CONNECTION_TIME_OUT	= 10000;

    /**
     * HipchatMessage instance
     */
    private HipchatMessage hipchatMessage;

    /**
     * Default constructor
     * @param message HipchatMessage object
     */
    public HipChatClient(HipchatMessage message){
        this.hipchatMessage = message;
    }

    /**
     * Push message to slack using simple URLConnection
     * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
     */
    public String push(){
        LogUtil.info("HipChatClient : push()");
        HttpURLConnection connection = null;
        try{
            HipchatProp prop = this.hipchatMessage.getProp();
            LogUtil.info("token : " + prop.getToken());
            LogUtil.info("apiId : " + prop.getApiId());
            LogUtil.info("jenniferUrl : " + prop.getJenniferUrl());
            LogUtil.info("hipchatApiServer : " + prop.getHipchatApiServer());
            LogUtil.info("proxyHost : " + prop.getProxyHost());
            LogUtil.info("proxyPort : " + prop.getProxyPort());
            LogUtil.info("isProxy : " + prop.getIsProxy());
            URL url = new URL(prop.getHipchatUrl());

            if("on".equals(prop.getIsProxy())){
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("dev-proxy.db.rakuten.co.jp", 9502));
                connection = (HttpURLConnection) url.openConnection(proxy);
            }else{
                connection = (HttpURLConnection)url.openConnection();
            }

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(CONNECTION_TIME_OUT);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            String payload = hipchatMessage.getText();
            LogUtil.info("payload : " + payload);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(payload);

            out.flush();
            out.close();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder response = new StringBuilder();
            while ( (line = reader.readLine()) != null )
                response.append(line + "\n");

            reader.close();
            return response.toString();
        }catch(Exception ex){
            LogUtil.error("Error while pushing message. Reason : " + ex.toString());
            return null;
        }finally{
            if(connection != null)
                connection.disconnect();
        }
    }

}