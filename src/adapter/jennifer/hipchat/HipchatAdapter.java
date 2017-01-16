package adapter.jennifer.hipchat;

import adapter.jennifer.hipchat.entity.HipchatMessage;
import adapter.jennifer.hipchat.entity.HipchatProp;
import adapter.jennifer.hipchat.util.ConfUtil;
import adapter.jennifer.hipchat.util.HipChatClient;
import com.jennifersoft.view.adapter.JenniferAdapter;
import com.jennifersoft.view.adapter.JenniferModel;
import com.jennifersoft.view.adapter.model.JenniferEvent;
import com.jennifersoft.view.adapter.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by minsoo.jun on 12/27/16.
 */
public class HipchatAdapter implements JenniferAdapter {
    /**
     * Format the date and time
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void on(JenniferModel[] jennfierModel) {
        LogUtil.info("HipchatAdapter : called");

        HipChatClient client;
        HipchatProp hipchatProperties;
        JenniferEvent eventModel;
        HipchatMessage message;
        try {
            LogUtil.info("JenniferModel length =" + jennfierModel.length);

            hipchatProperties = ConfUtil.getHipchatProperties();

            for (int i = 0; i < jennfierModel.length; i++) {
                eventModel = (JenniferEvent) jennfierModel[i];
                message = new HipchatMessage(hipchatProperties, this.jenniferEventToString(eventModel, hipchatProperties.getJenniferUrl()));
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
     * @return event model as string (Slack message body)
     */
    private String jenniferEventToString(JenniferEvent eventModel, String jenniferUrl) {
        StringBuilder messageBody = new StringBuilder();

        messageBody.append("{\"message\":\"");
        messageBody.append("The following event <b>" + eventModel.getErrorType() + "</b> was caught by JENNIFER<br/>");
        messageBody.append("Here are some additional details<br/>");
        messageBody.append("---------------------------------------------------------------");
        messageBody.append("<table><tr><td>");
        messageBody.append("Affected Domain [ID:NAME]");
        messageBody.append("</td><td>");
        messageBody.append("<a href='" + jenniferUrl + "'>");
        messageBody.append(eventModel.getDomainId() + ":" + eventModel.getDomainName() + "");
        messageBody.append("</a>");
        messageBody.append("</td></tr>");
        messageBody.append("<tr><td>");
        messageBody.append("Affected Instance [ID:NAME]");
        messageBody.append("</td><td>");
        messageBody.append(eventModel.getInstanceId() + ":" + eventModel.getInstanceName());
        messageBody.append("</td></tr>");
        String message = eventModel.getMessage().length() == 0 ? "None" : eventModel.getMessage();
        messageBody.append("<tr><td>");
        messageBody.append("Message ");
        messageBody.append("</td><td>");
        messageBody.append(message);
        messageBody.append("</td></tr><tr><td>");
        String detailedMessage = eventModel.getDetailMessage().length() == 0 ? "None" : eventModel.getDetailMessage();

        messageBody.append("Detailed Message");
        messageBody.append("</td><td><b>");
        messageBody.append(detailedMessage);
        messageBody.append("</b></td></tr><tr><td>");
        String serviceName = eventModel.getServiceName().length() == 0 ? "Not available" : eventModel.getServiceName();
        messageBody.append("Service Name");
        messageBody.append("</td><td>");
        messageBody.append(serviceName);
        messageBody.append("</td></tr><tr><td>");
        String transactionId = eventModel.getTxId() == 0 ? "Not available" : eventModel.getTxId() + "";
        messageBody.append("Transaction Id");
        messageBody.append("</td><td>");
        messageBody.append(transactionId);
        messageBody.append("</td></tr><tr><td>");
        String metricsName = eventModel.getMetricsName().length() == 0 ? "Not available" : eventModel.getMetricsName();
        messageBody.append("Metrics Name");
        messageBody.append("</td><td>");
        messageBody.append(metricsName);
        messageBody.append("</td></tr><tr><td>");
        Date d = new Date(eventModel.getTime());
        messageBody.append("Event Time [Raw:HumanRedable]");
        messageBody.append("</td><td>");
        messageBody.append(eventModel.getTime() + ":" + sdf.format(d));
        messageBody.append("</td></tr></table>");
        messageBody.append("---------------------------------------------------------------");
        messageBody.append("<br/>This message was created automatically by JENNIFER Hipchat Adapter");

        messageBody.append("\",\"color\":\"purple\", \"message_format\":\"html\"}");
        return messageBody.toString();
    }
}
