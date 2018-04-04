package ren.wizard.dingtalkclient.message;

import org.junit.Test;
import ren.wizard.dingtalkclient.model.FeedCardMessageItem;

import static org.junit.Assert.*;

public class FeedCardMessageTest {
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        FeedCardMessage feedCardMessage = new FeedCardMessage();
        feedCardMessage.toJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException2() {
        FeedCardMessage feedCardMessage = new FeedCardMessage();
        FeedCardMessageItem item = new FeedCardMessageItem();
        feedCardMessage.getFeedItems().add(item);
        feedCardMessage.toJson();
    }

    @Test
    public void toJson() {
        FeedCardMessage feedCardMessage = new FeedCardMessage();
        FeedCardMessageItem item = new FeedCardMessageItem();
        item.setMessageURL("http://baidu.com");
        item.setPicURL("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        item.setTitle("Test Feed Card Message");
        feedCardMessage.getFeedItems().add(item);
        feedCardMessage.toJson();
    }
}