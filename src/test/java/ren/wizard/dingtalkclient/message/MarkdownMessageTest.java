package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarkdownMessageTest {
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.toJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noTitle() {
        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.getItems().add("# THIS IS A TEST MARKDOWN MESSAGE");
        markdownMessage.getItems().add("> and this message is from ding talk client");
        markdownMessage.toJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void normal() {
        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.setTitle("this is a test message");
        markdownMessage.getItems().add("# THIS IS A TEST MARKDOWN MESSAGE");
        markdownMessage.getItems().add("> and this message is from ding talk client");
        String json = markdownMessage.toJson();
        assertFalse(StringUtils.isBlank(json));
        assertNotEquals(json, "null");
    }
}