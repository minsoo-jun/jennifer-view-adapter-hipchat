package com.aries.hipchat.util;

import com.aries.hipchat.entity.HipchatProp;
import com.aries.extension.util.PropertyUtil;

public class ConfUtil {

    /**
     * Adapter ID
     */
    private static final String ADAPTER_ID = "hipchat";

    private static final String HIPCHAT_APIID = "hipchat_apiid";

    private static final String HIPCHAT_TOKEN = "hipchat_token";

    private static final String HIPCHAT_NOTI_LEVEL = "hipchat_noti_level" ;

    private static final String HIPCHAT_JENNIFER_URL = "hipchat_jennifer_url";

    private static final String PROXY_ON = "hipchat_proxy_on" ;

    private static final String PROXY_HOST ="hipchat_proxy_host";

    private static final String PROXY_PORT ="hipchat_proxy_port";

    /**
     * Get a configuration value using the provided key
     * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
     * @return String configuration value
     */
    public static String getValue(String key) {
        return PropertyUtil.getValue(ADAPTER_ID, key);
    }

    /**
     * Get the telegram properties
     * @return TelegramProperties instance
     */
    public static HipchatProp getHipchatProperties() {

        HipchatProp properties = new HipchatProp();
        properties.setToken(getValue(HIPCHAT_TOKEN));
        properties.setApiId(getValue(HIPCHAT_APIID));
        properties.setIsProxy(getValue(PROXY_ON));
        properties.setJenniferUrl(getValue(HIPCHAT_JENNIFER_URL));
        properties.setNotificationLevel(getValue(HIPCHAT_NOTI_LEVEL));
        properties.setProxyHost(getValue(PROXY_HOST));
        properties.setProxyPort(getValue(PROXY_PORT));

        return properties;
    }
}

