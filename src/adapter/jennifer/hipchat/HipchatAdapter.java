package adapter.jennifer.hipchat;

import adapter.jennifer.hipchat.entity.HipchatMessage;
import adapter.jennifer.hipchat.entity.HipchatProp;
import adapter.jennifer.hipchat.util.ConfUtil;
import adapter.jennifer.hipchat.util.ConfUtilForLocal;
import adapter.jennifer.hipchat.util.HipChatClient;
import com.aries.extension.data.EventData;
import com.aries.extension.handler.EventHandler;
import com.jennifersoft.view.adapter.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by minsoo.jun on 2018-11-09
 * https://www.hipchat.com/docs/apiv2/method/send_room_notification
 */
public class HipchatAdapter implements EventHandler {
    /**
     * Format the date and time
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void on(EventData[] eventData) {
        LogUtil.info("HipchatAdapter : called");

        HipChatClient client;
        HipchatProp hipchatProperties;
        EventData eventModel;
        HipchatMessage message;
        try {
            LogUtil.info("eventData length =" + eventData.length);

            hipchatProperties = ConfUtil.getHipchatProperties();
            // for local testing.
            if(hipchatProperties.getToken() == null){
                hipchatProperties = ConfUtilForLocal.getHipchatProperties();
            }

            for (int i = 0; i < eventData.length; i++) {
                eventModel = (EventData) eventData[i];
                message = new HipchatMessage(hipchatProperties, this.jenniferEventToString(eventModel, hipchatProperties));
                client = new HipChatClient(message);
                client.push();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String representation of the event to be used as the slack message body
     *
     * @param eventModel the event model
     * @return event model as string (hipchat message body)
     */
    private String jenniferEventToString(EventData eventModel, HipchatProp hipchatProperties) {
        StringBuilder messageBody = new StringBuilder();

        messageBody.append("{\"message\":\"");
        messageBody.append("[" + eventModel.eventLevel +"] The following event <b>" + eventModel.errorType + "</b> was caught by JENNIFER<br/>");
        messageBody.append("Here are some additional details<br/>");
        messageBody.append("---------------------------------------------------------------");
        messageBody.append("<table><tr><td>");
        messageBody.append("Affected Domain [ID:NAME]");
        messageBody.append("</td><td>");
        messageBody.append("<a href='" + hipchatProperties.getJenniferUrl() + "'>");
        messageBody.append(eventModel.domainId + ":" + eventModel.domainName + "");
        messageBody.append("</a>");
        messageBody.append("</td></tr>");
        messageBody.append("<tr><td>");
        messageBody.append("Affected Instance [ID:NAME]");
        messageBody.append("</td><td>");
        messageBody.append(eventModel.instanceId + ":" + eventModel.instanceName);
        messageBody.append("</td></tr>");
        String message = eventModel.message.length() == 0 ? "None" : eventModel.message;
        messageBody.append("<tr><td>");
        messageBody.append("Message ");
        messageBody.append("</td><td>");
        messageBody.append(message);
        messageBody.append("</td></tr><tr><td>");
        String detailedMessage = eventModel.detailMessage.length() == 0 ? "None" : eventModel.detailMessage;

        messageBody.append("Detailed Message");
        messageBody.append("</td><td><b>");
        messageBody.append(detailedMessage);
        messageBody.append("</b></td></tr><tr><td>");
        String serviceName = eventModel.serviceName.length() == 0 ? "Not available" : eventModel.serviceName;
        messageBody.append("Service Name");
        messageBody.append("</td><td>");
        messageBody.append(serviceName);
        messageBody.append("</td></tr><tr><td>");
        String transactionId = eventModel.txid == 0 ? "Not available" : eventModel.txid + "";
        messageBody.append("Transaction Id");
        messageBody.append("</td><td>");
        messageBody.append(transactionId);
        messageBody.append("</td></tr><tr><td>");
        String metricsName = eventModel.metricsName.length() == 0 ? "Not available" : eventModel.metricsName;
        messageBody.append("Metrics Name");
        messageBody.append("</td><td>");
        messageBody.append(metricsName);
        messageBody.append("</td></tr><tr><td>");
        Date d = new Date(eventModel.time);
        messageBody.append("Event Time ");
        messageBody.append("</td><td>");
        messageBody.append(sdf.format(d));
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data configFilePath").append("</td><td>").append(eventModel.instanceData.configFilePath);
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data version" ).append("</td><td>").append( eventModel.instanceData.version);
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data description").append("</td><td>").append(eventModel.instanceData.description);
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data ipAddress").append("</td><td>").append( eventModel.instanceData.ipAddress);
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data platform").append("</td><td>").append( eventModel.instanceData.platform);
        messageBody.append("</td></tr><tr><td>");
        messageBody.append("  Instance Data hostName").append("</td><td>").append( eventModel.instanceData.hostName);
        messageBody.append("</td></tr></table>");



        messageBody.append("---------------------------------------------------------------");
        messageBody.append("<br/>This message was created automatically by JENNIFER Hipchat Adapter v1.1.0 Manggu");

        String color = "green";
        Boolean notify = false;
        if ("WARNING".equals(eventModel.eventLevel)){
            color = "purple";
        }else if("FATAL".equals(eventModel.eventLevel)){
            color = "red";
        }
        if ("NORMAL".equals(hipchatProperties.getNotificationLevel())){
            notify = true;
        }else if ("WARNING".equals(hipchatProperties.getNotificationLevel())){
            if("WARNING".equals(eventModel.eventLevel) ||"FATAL".equals(eventModel.eventLevel) ){
                notify = true;
            }
        }else if ("FATAL".equals(hipchatProperties.getNotificationLevel())){
            if("FATAL".equals(eventModel.eventLevel) ){
                notify = true;
            }
        }
        messageBody.append("\",\"color\":\"" + color + "\", \"message_format\":\"html\", \"notify\":" + notify + "}");

        return messageBody.toString();
    }
}
