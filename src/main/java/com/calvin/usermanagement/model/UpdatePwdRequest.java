package com.calvin.usermanagement.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Calvin Ye
 * @since 2019年07月29日 16:54
 */
public class UpdatePwdRequest {
    @NotNull(message = "old password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20105，old password format error")
    private String oldPwd;
    @NotNull(message = "new password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20106，new password format error")
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
