package ren.wizard.dingtalkclient.message;

import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uyangjie
 */
@Data
public class TextMessage implements DingMessage {
    private String text;
    private List<String> atMobiles = new ArrayList<>();
    private boolean isAtAll;

    @Override
    public String toJson() throws IllegalArgumentException {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("text should not be blank");
        }
        Map<String, Object> items = new HashMap<>((int) Math.ceil(3 / 0.75));
        items.put("msgtype", "text");
        Map<String, Object> atItems = new HashMap<>((int) Math.ceil(2 / 0.75));
        if (atMobiles != null && !atMobiles.isEmpty()) {
            atItems.put("atMobiles", atMobiles);
        }
        if (isAtAll) {
            atItems.put("isAtAll", true);
        }
        items.put("at", atItems);
        Map<String, String> textContent = new HashMap<>((int) Math.ceil(1 / 0.75));
        textContent.put("content", text);
        items.put("text", textContent);
        return new Gson().toJson(items);
    }
}
