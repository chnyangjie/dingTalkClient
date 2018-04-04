package ren.wizard.dingtalkclient.message;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import ren.wizard.dingtalkclient.constant.ActionButtonStyle;
import ren.wizard.dingtalkclient.model.ActionCardAction;

import static org.junit.Assert.*;

public class ActionCardMessageTest {
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException() {
        ActionCardMessage actionCardMessage = new ActionCardMessage();
        actionCardMessage.toJson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentException2() {
        ActionCardMessage actionCardMessage = new ActionCardMessage();
        actionCardMessage.setTitle("This is a action card message");
        actionCardMessage.setActionButtonStyle(ActionButtonStyle.VERTICAL);
        actionCardMessage.setBannerURL("http://baidu.com");
        actionCardMessage.setHideAvatar(false);
        ActionCardAction item = new ActionCardAction();
        actionCardMessage.addAction(item);
        actionCardMessage.toJson();
    }

    @Test
    public void toJson() {
        ActionCardMessage actionCardMessage = new ActionCardMessage();
        actionCardMessage.setTitle("This is a action card message");
        actionCardMessage.setActionButtonStyle(ActionButtonStyle.VERTICAL);
        actionCardMessage.setBannerURL("http://baidu.com");
        actionCardMessage.setHideAvatar(true);
        ActionCardAction item = new ActionCardAction();
        item.setTitle("test button");
        item.setActionURL("http://baidu.com");
        actionCardMessage.addAction(item);
        String json = actionCardMessage.toJson();
        assertFalse(StringUtils.isBlank(json));
        assertNotEquals(json, "null");
    }
}