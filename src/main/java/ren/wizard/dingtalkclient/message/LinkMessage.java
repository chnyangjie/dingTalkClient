package ren.wizard.dingtalkclient.message;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author uyangjie
 */
@Data
@Builder
public class LinkMessage implements DingMessage {
    private String title;
    private String text;
    private String picUrl;
    private String messageUrl;

    @Override
    public String toJson() {
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("title should not be blank");
        }

        if (StringUtils.isBlank(messageUrl)) {
            throw new IllegalArgumentException("messageUrl should not be blank");
        }

        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("text should not be blank");
        }
        Map<String, String> linkContent = new HashMap<>((int) Math.ceil(4 / 0.75));
        linkContent.put("title", title);
        linkContent.put("messageUrl", messageUrl);
        linkContent.put("text", text);
        if (StringUtils.isNotBlank(picUrl)) {
            linkContent.put("picUrl", picUrl);
        }
        Map<String, Object> items = new HashMap<>((int) Math.ceil(2 / 0.75));
        items.put("msgtype", "link");
        items.put("link", linkContent);
        return new Gson().toJson(items);
    }
}
