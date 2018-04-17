package ren.wizard.dingtalkclient;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
     * @param webhook  ding talk webhook or access token
     * @param message  ding talk message {@link DingMessage}
     * @param proxy
     * @param port
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public SendResult sendMessage(String webhook, DingMessage message, String proxy, int port, String username, String password) throws IOException {
        if (StringUtils.isBlank(proxy) && port <= 0) {
            return sendMessage(webhook, message);
        } else {
            HttpClientBuilder builder = HttpClients.custom();
            HttpHost httpHost = new HttpHost(proxy, port);
            if (!StringUtils.isBlank(username)) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(proxy, port),
                        new UsernamePasswordCredentials(username, password)
                );
                builder.setDefaultCredentialsProvider(credsProvider);
            }
            builder.setProxy(httpHost);
            httpclient = builder.build();
            return sendMessage(webhook, message);
        }
    }

    public CloseableHttpClient getHttpclient() {
        return httpclient;
    }

    public void setHttpclient(CloseableHttpClient httpclient) {
        this.httpclient = httpclient;
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
        if (StringUtils.isBlank(webhook)) {
            throw new IllegalArgumentException();
        }
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
            httpPost.releaseConnection();
            return gson.fromJson(result, SendResult.class);
        }
        throw new IOException();
    }
}
