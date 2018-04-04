package ren.wizard.dingtalkclient.model;

import lombok.Data;

/**
 * @author uyangjie
 */
@Data
public class SendResult {
    private boolean isSuccess;
    private Integer errorCode;
    private String errorMsg;
}
