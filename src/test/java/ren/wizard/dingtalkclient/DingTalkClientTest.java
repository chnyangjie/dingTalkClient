package ren.wizard.dingtalkclient;

import org.junit.Before;
import org.junit.Test;
import ren.wizard.dingtalkclient.constant.ActionButtonStyle;
import ren.wizard.dingtalkclient.message.*;
import ren.wizard.dingtalkclient.model.ActionCardAction;
import ren.wizard.dingtalkclient.model.FeedCardMessageItem;

import java.io.IOException;

public class DingTalkClientTest {
    private String webhook;
    private String token;
    private DingTalkClient dingTalkClient;


    @Before
    public void setUp() {
        this.dingTalkClient = DingTalkClient.getInstance();

    }

    @Test
    public void sendTextMessage() throws IOException {
        TextMessage textMessage = TextMessage.builder()
                .build();
        textMessage.setText("this is a test text message from ding talk client");
        dingTalkClient.sendMessage(this.webhook, textMessage);
        textMessage.setText("this is a test text message from ding talk client but I passed the token as webhook");
        dingTalkClient.sendMessage(this.token, textMessage);

    }

    @Test
    public void sendMarkDownMessage() throws IOException {
        MarkdownMessage markdownMessage = MarkdownMessage.builder()
                .item("# THIS IS A TEST MARKDOWN MESSAGE")
                .item("> and this message is from ding talk client")
                .build();
        markdownMessage.setTitle("markdown message tesst");
        dingTalkClient.sendMessage(this.token, markdownMessage);
    }

    @Test
    public void sendFeedCardMessage() throws IOException {
        FeedCardMessage feedCardMessage = FeedCardMessage.builder().build();
        FeedCardMessageItem item = new FeedCardMessageItem();
        item.setMessageURL("http://baidu.com");
        item.setPicURL("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        item.setTitle("Test Feed Card Message");
        feedCardMessage.getFeedItems().add(item);
        feedCardMessage.getFeedItems().add(item);
        feedCardMessage.toJson();
        dingTalkClient.sendMessage(this.token, feedCardMessage);
    }

    @Test
    public void sendLinkMessage() throws IOException {
        LinkMessage linkMessage = LinkMessage.builder().build();
        linkMessage.setMessageUrl("http://baidu.com");
        linkMessage.setPicUrl("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        linkMessage.setTitle("Test Link Message");
        linkMessage.setText("This is a tests link message");
        dingTalkClient.sendMessage(this.token, linkMessage);
    }

    @Test
    public void sendActionCardMessage() throws IOException {
        ActionCardMessage actionCardMessage = ActionCardMessage.builder().build();
        actionCardMessage.setTitle("This is a action card message");
        actionCardMessage.setActionButtonStyle(ActionButtonStyle.HORIZONTAL);
        actionCardMessage.setBannerURL("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        actionCardMessage.setBriefTitle("This is action card message's title");
        actionCardMessage.setBriefText("This is action card message's content");
        actionCardMessage.setHideAvatar(true);
        ActionCardAction item = new ActionCardAction();
        item.setTitle("test button");
        item.setActionURL("http://baidu.com");
        actionCardMessage.addAction(item);
        actionCardMessage.addAction(item);
        actionCardMessage.addAction(item);
        dingTalkClient.sendMessage(this.token, actionCardMessage);
    }

    @Test
    public void sendSingleTargetActionCardMessage() throws IOException {
        ActionCardMessage actionCardMessage = ActionCardMessage.builder().build();
        actionCardMessage.setTitle("This is a action card message");
        actionCardMessage.setActionButtonStyle(ActionButtonStyle.HORIZONTAL);
        actionCardMessage.setBannerURL("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        actionCardMessage.setBriefTitle("This is action card message's title");
        actionCardMessage.setBriefText("This is action card message's content");
        actionCardMessage.setHideAvatar(true);
        ActionCardAction item = new ActionCardAction();
        item.setTitle("test button");
        item.setActionURL("http://baidu.com");
        actionCardMessage.addAction(item);
        dingTalkClient.sendMessage(this.token, actionCardMessage);
    }
}