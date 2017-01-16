package test.adapter.jennifer.hipchat;

import adapter.jennifer.hipchat.HipchatAdapter;
import com.jennifersoft.view.adapter.JenniferModel;
import com.jennifersoft.view.adapter.model.JenniferEvent;

/**
 * Created by minsoo.jun on 12/27/16.
 */
public class HipchatAdapter_test {

    public static void main(String[] args) {
        try {

            JenniferModel[] jm = new JenniferEvent[2];

            JenniferEvent je = new JenniferEvent();
            short domainId = 1;
            je.setErrorType("Test error Type");
            je.setDomainId(domainId);
            je.setInstanceId(100001);
            je.setDetailMessage("Jennifer Alert Test");
            je.setServiceName("Test Service Name");
            je.setMessage("Test Message");
            je.setMetricsName("Test MetricsName");
            jm[0] = je;

            je = new JenniferEvent();
            je.setErrorType("2 Test error Type");
            je.setDomainId(domainId);
            je.setInstanceId(100002);
            je.setDetailMessage("Jennifer Alert Test2");
            je.setServiceName("Test Service Name ");
            je.setMessage("Test Message 2");
            je.setMetricsName("Test MetricsName 2");
            jm[1] = je;


            HipchatAdapter ha = new HipchatAdapter();
            ha.on(jm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
