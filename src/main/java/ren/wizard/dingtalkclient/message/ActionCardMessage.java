package ren.wizard.dingtalkclient.message;


import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ren.wizard.dingtalkclient.constant.ActionButtonStyle;
import ren.wizard.dingtalkclient.model.ActionCardAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uyangjie
 */
@Data
public class ActionCardMessage implements DingMessage {
    public static final int MAX_ACTION_BUTTON_CNT = 5;
    public static final int MIN_ACTION_BUTTON_CNT = 1;

    private String title;
    private String bannerURL;
    private String briefTitle;
    private String briefText;
    private boolean hideAvatar;
    private ActionButtonStyle actionButtonStyle = ActionButtonStyle.VERTICAL;
    private List<ActionCardAction> actions = new ArrayList<>();

    /**
     * try to add action
     *
     * @param action {@link ActionCardAction}
     * @return is success
     */
    public boolean addAction(ActionCardAction action) {
        if (actions.size() >= MAX_ACTION_BUTTON_CNT) {
            return false;
        }
        actions.add(action);
        return true;
    }

    @Override
    public String toJson() {
        if (actions.size() < MIN_ACTION_BUTTON_CNT) {
            throw new IllegalArgumentException("number of actions can't less than " + MIN_ACTION_BUTTON_CNT);
        }
        for (ActionCardAction item : this.actions) {
            if (StringUtils.isBlank(item.getTitle())) {
                throw new IllegalArgumentException("title should not be blank");
            }
            if (StringUtils.isBlank(item.getActionURL())) {
                throw new IllegalArgumentException("action url should not be blank");
            }
        }

        Map<String, Object> actionCardContent = new HashMap<>((int) Math.ceil(7 / 0.75));
        actionCardContent.put("title", title);

        StringBuilder text = new StringBuilder();
        if (StringUtils.isNotBlank(bannerURL)) {
            text.append(MarkdownMessage.getImageText(bannerURL)).append("\n");
        }
        if (StringUtils.isNotBlank(briefTitle)) {
            text.append(MarkdownMessage.getHeaderText(3, briefTitle)).append("\n");
        }
        if (StringUtils.isNotBlank(briefText)) {
            text.append(briefText).append("\n");
        }
        actionCardContent.put("text", text.toString());

        if (hideAvatar) {
            actionCardContent.put("hideAvatar", "1");
        }
        if (actions.size() == 1) {
            actionCardContent.put("singleTitle", actions.get(0).getTitle());
            actionCardContent.put("singleURL", actions.get(0).getActionURL());
        } else {
            actionCardContent.put("btns", actions);
        }
        switch (actionButtonStyle) {
            case VERTICAL: {
                actionCardContent.put("btnOrientation", "0");
                break;
            }
            case HORIZONTAL: {
                actionCardContent.put("btnOrientation", "1");
                break;
            }
            default: {
                break;
            }
        }
        Map<String, Object> items = new HashMap<>((int) Math.ceil(2 / 0.75));
        items.put("msgtype", "actionCard");
        items.put("actionCard", actionCardContent);
        return new Gson().toJson(items);
    }
}
