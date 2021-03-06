package com.aries.hipchat.util;

import com.aries.hipchat.entity.HipchatProp;
import com.aries.extension.util.LogUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Load adapter configuration from configuration file
 */
public class ConfUtilForLocal {

    /**
     * Properties instances to load the configuration
     */
    private static Properties properties = null;

    /**
     * Initialize the Properties object and load the configuration from the configuration file
     * The configuration file name and path must be set in <b>VIEW_SERVER_HOME/conf/server_view.conf<b> using the
     * <b>adapter_config_path</b> option
     */
    public static void load() {
        properties = new Properties();
        FileInputStream in = null;
        String path = "/usr/local/jennifer/jennifer5/server.view/ext/hipchat.adapter/adapter.properties";

        try {
            if (path != null) {
                in = new FileInputStream(path);
                properties.load(in);
            }
        } catch (IOException io) {
            LogUtil.error("Failed to load configuration file: " + io.toString());
        }
    }

    /**
     * Get a configuration value using the provided key
     *
     * @param key configuration key
     * @return String configuration value
     */
    public static String getValue(String key) {
        if (properties == null)
            load();
        return properties.getProperty(key);
    }

    /**
     * Getter for the properties object.
     *
     * @return the properties object
     */
    public static Properties getProperties() {
        if (properties == null)
            load();
        return properties;
    }

    /**
     * Cast the properties into <b>HipchatProp</b> class
     * The following properties must be set in the configuration file
     * <b>hipchart_apiid</b> The incoming WebHook URL
     * <b>hipchat_token</b> The Slack Channel Name (Or username) where message will be delivered
     *
     * @return
     */
    public static HipchatProp getHipchatProperties() {
        HipchatProp prop = new HipchatProp();
        prop.setApiId(getValue("hipchat_apiid"));
        prop.setToken(getValue("hipchat_token"));
        prop.setJenniferUrl(getValue("hipchat_jennifer_url"));
        prop.setHipchatApiServer(getValue("hipchat_api_server"));
        prop.setNotificationLevel(getValue("hipchat_noti_level"));
        prop.setIsProxy(getValue("hipchat_proxy_on"));
        prop.setProxyHost(getValue("hipchat_proxy_host"));
        prop.setProxyPort(getValue("hipchat_proxy_port"));

        return prop;
    }
}
