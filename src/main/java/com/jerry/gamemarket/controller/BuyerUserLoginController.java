package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.NormalUserVO;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.entity.NormalUser;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.CreateUser;
import com.jerry.gamemarket.service.NormalUserService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 叶俊晖
 * @date 2019/3/19 0019 22:37
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/buyer/login")
public class BuyerUserLoginController {
    @Autowired
    private NormalUserService userService;

    @GetMapping("/login")
    public ResultVO<Map<String,Object>> login(@RequestParam("uname")String uname, @RequestParam("upass")String upass){
        Map<String,Object> map=new HashMap<>();
        NormalUser user=userService.checkLogin(uname,upass);
        if(user==null){
            map.put("error","用户不存在");
           // return ResultVOUtil.error(404,"用户不存在");
        }else if(user.getUserRole()==-1){
            map.put("error","用户已被禁止");
           // return ResultVOUtil.error(404,"用户不存在");
        }else if(user.getUserRole()==-2){
            map.put("error","密码不正确");
          //  return ResultVOUtil.error(404,"用户不存在");
        }else{
            NormalUserVO userVO=new NormalUserVO();
            BeanUtils.copyProperties(user,userVO);
            map.put("user",userVO);
        }
        return ResultVOUtil.success(map);
    }
    @PostMapping("/register")
    public ResultVO<Map<String,Object>> register(@Valid CreateUser createUser, BindingResult bindingResult){
        Map<String,Object> map=new HashMap<>();
        if (bindingResult.hasErrors()) {
            log.error("【注册用户】 参数不正确，register{}", createUser);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        if(userService.isExist(createUser.getUserName())>0){
            map.put("error","用户已存在");
        }else{
            NormalUser user=userService.register(createUser);
            NormalUserVO userVO=new NormalUserVO();
            BeanUtils.copyProperties(user,userVO);
            map.put("newUser",userVO);
        }
        return ResultVOUtil.success(map);
    }
}
