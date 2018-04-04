package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextMessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        TextMessage textMessage = new TextMessage();
        textMessage.toJson();
    }

    @Test
    public void normal() {
        TextMessage textMessage = new TextMessage();
        textMessage.setText("asd");
        String json = textMessage.toJson();
        assertNotEquals(json, "null");
        assertFalse(StringUtils.isBlank(json));
    }
}