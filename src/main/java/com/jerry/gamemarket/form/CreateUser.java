package com.jerry.gamemarket.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author 叶俊晖
 * @date 2019/3/20 0020 17:12
 */
@Data
public class CreateUser {
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String userPass;
    @NotNull(message = "手机号不能为空")
    private String userPhone;
    private String userGender;
    private Date userBirth;
}
