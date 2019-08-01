package com.calvin.usermanagement.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Calvin Ye
 * @since 2019年07月29日 16:54
 */

@Data
public class UpdatePwdRequest {

    @NotNull(message = "old password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20105，old password format error")
    private String oldPwd;

    @NotNull(message = "new password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20106，new password format error")
    private String newPwd;
}
