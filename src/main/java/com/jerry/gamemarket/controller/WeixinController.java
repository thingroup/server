package com.jerry.gamemarket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 微信请求
 * Created by Administrator on 2018/11/20 0018.
 */
@CrossOrigin
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code")  String  code) {
        log.info("进入回调函数");
        log.info("code={}" , code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx4bd58aa2a23729c9&secret=56efea63b62766bd30e46ca498105b0b&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url , String.class);
        log.info("result={}" , result);
    }
}
