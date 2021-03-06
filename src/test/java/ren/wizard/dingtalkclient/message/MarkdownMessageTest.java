package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class MarkdownMessageTest {
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        MarkdownMessage markdownMessage = MarkdownMessage.builder().build();
        markdownMessage.toJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void noTitle() {
        MarkdownMessage markdownMessage = MarkdownMessage.builder().build();
        markdownMessage.getItems().add("# THIS IS A TEST MARKDOWN MESSAGE");
        markdownMessage.getItems().add("> and this message is from ding talk client");
        markdownMessage.toJson();
    }

    @Test
    public void normal() {
        MarkdownMessage markdownMessage = MarkdownMessage.builder()
                .item("# THIS IS A TEST MARKDOWN MESSAGE")
                .item("> and this message is from ding talk client")
                .build();
        markdownMessage.setTitle("this is a test message");
        String json = markdownMessage.toJson();
        assertFalse(StringUtils.isBlank(json));
        assertNotEquals(json, "null");
    }
}