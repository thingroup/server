package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.exception.GameException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.jerry.gamemarket.enums.ResultEnum;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;


    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置微信公众号信息
        String url = "http://jtogether.natapp1.cc/gamemarket/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        //2.获取网页code
        return "redirect:" + redirectUrl;
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code , @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】授权失败{}" , e);
           throw new GameException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid="+openId;

    }

//    @GetMapping("/qrAuthorize")
//    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
//        //1.配置微信公众号信息
//        String url = projectUrlConfig.getOpenAuthorizeUrl()+"/sell/wechat/qrUserInfo";
//        String redirectUrl = wxOpenService.buildQrConnectUrl(url , WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN , URLEncoder.encode(returnUrl));
//        //2.获取网页code
//        return "redirect:" + redirectUrl;
//    }
//
//    @GetMapping("/qrUserInfo")
//    public String qrUserInfo(@RequestParam("code") String code , @RequestParam("state") String returnUrl) {
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
//        try {
//            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//            log.error("【微信网页授权】授权失败{}" , e);
//            throw new GameException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
//        }
//
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//        return "redirect:" + returnUrl + "?openid="+openId;
//
//    }
}
