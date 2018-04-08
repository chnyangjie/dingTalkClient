package ren.wizard.dingtalkclient;


import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ren.wizard.dingtalkclient.message.DingMessage;
import ren.wizard.dingtalkclient.model.SendResult;

import java.io.IOException;

/**
 * @author uyangjie
 */
public class DingTalkClient {
    private static DingTalkClient ourInstance = new DingTalkClient();
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private Gson gson = new Gson();

    private DingTalkClient() {
    }

    public static DingTalkClient getInstance() {
        return ourInstance;
    }

    /**
     * send message
     *
     * @param webhook ding talk webhook or access token
     * @param message ding talk message {@link DingMessage}
     * @return {@link SendResult}
     * @throws IOException
     */
    public SendResult sendMessage(String webhook, DingMessage message) throws IOException {
        String url;
        if (webhook.toLowerCase().startsWith("http")) {
            url = webhook;
        } else {
            url = String.format("https://oapi.dingtalk.com/robot/send?access_token=%s", webhook);
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message.toJson(), "utf-8");
        httpPost.setEntity(se);
        HttpResponse response = httpclient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            return gson.fromJson(result, SendResult.class);
        }
        throw new IOException();
    }
}
