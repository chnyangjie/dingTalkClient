package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkMessageTest {
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        LinkMessage linkMessage = LinkMessage.builder().build();
        linkMessage.toJson();
    }

    @Test
    public void normal() {
        LinkMessage linkMessage = LinkMessage.builder().build();
        linkMessage.setMessageUrl("http://baidu.com");
        linkMessage.setPicUrl("http://img2.niushe.com/upload/201304/19/14-22-31-71-26144.jpg");
        linkMessage.setTitle("Test Link Message");
        linkMessage.setText("This is a tests link message");
        String json = linkMessage.toJson();
        assertFalse(StringUtils.isBlank(json));
        assertNotEquals(json, "null");
    }
}