package ren.wizard.dingtalkclient.message;


import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import ren.wizard.dingtalkclient.message.DingMessage;
import ren.wizard.dingtalkclient.model.FeedCardMessageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uyangjie
 */
@Data
public class FeedCardMessage implements DingMessage {
    private List<FeedCardMessageItem> feedItems = new ArrayList<>();

    @Override
    public String toJson() {

        for (FeedCardMessageItem item : feedItems) {
            if (StringUtils.isBlank(item.getTitle())) {
                throw new IllegalArgumentException("title should not be blank");
            }
            if (StringUtils.isBlank(item.getMessageURL())) {
                throw new IllegalArgumentException("messageURL should not be blank");
            }
            if (StringUtils.isBlank(item.getPicURL())) {
                throw new IllegalArgumentException("picURL should not be blank");
            }
        }
        if (feedItems == null || feedItems.isEmpty()) {
            throw new IllegalArgumentException("feedItems should not be null or empty");
        }
        Map<String, Object> items = new HashMap<>((int) Math.ceil(2 / 0.75));
        items.put("msgtype", "feedCard");
        Map<String, Object> feedCard = new HashMap<>((int) Math.ceil(1 / 0.75));
        feedCard.put("links", feedItems);
        items.put("feedCard", feedCard);
        return new Gson().toJson(items);
    }
}
