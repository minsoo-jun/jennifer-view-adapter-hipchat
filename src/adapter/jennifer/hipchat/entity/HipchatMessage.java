package adapter.jennifer.hipchat.entity;

import org.json.JSONObject;

/**
 * Created by minsoo.jun on 12/27/16.
 */
public class HipchatMessage {

    private final String API_ID = "";
    private final String KEY_TOKEN = "TOKEN";
    private final String KEY_TEXT = "TEXT";
    private final String KEY_ATTACHMENT = "MESSAGE";

    private final String text;

    /**
     * The JSON object that will be used to construct the message payload
     */
    private JSONObject hipchatMessage = new JSONObject();

    /**
     * Hipchat properties instnace
     */
    private HipchatProp prop;

    /**
     * Default constructor
     *
     * @param prop HipchatProp object
     * @param text Message Body
     */
    public HipchatMessage(HipchatProp prop, String text) {
        this.prop = prop;
        this.text = text;
    }

    public HipchatProp getProp() {
        return this.prop;
    }

    public String getText() {
        return this.text;
    }
}
