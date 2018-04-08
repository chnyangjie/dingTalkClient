package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TextMessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        TextMessage textMessage = TextMessage.builder().build();
        textMessage.toJson();
    }

    @Test
    public void normal() {
        TextMessage textMessage = TextMessage.builder().build();
        textMessage.setText("asd");
        String json = textMessage.toJson();
        assertNotEquals(json, "null");
        assertFalse(StringUtils.isBlank(json));
    }
}