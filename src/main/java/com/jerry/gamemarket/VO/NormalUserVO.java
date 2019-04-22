package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 叶俊晖
 * @date 2019/3/20 0020 16:36
 */
@Data
public class NormalUserVO {
    @JsonProperty("id")
    private Integer userId;
    @JsonProperty("name")
    private String userName;
    @JsonProperty("phone")
    private String userPhone;
    @JsonProperty("gender")
    private Integer userGender;
    @JsonProperty("age")
    private Integer userAge;
    @JsonProperty("wechatid")
    private String wechatId;
    @JsonProperty("icon")
    private String userIcon;
    @JsonProperty("email")
    private String userEmail;
}
