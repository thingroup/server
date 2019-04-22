package com.jerry.gamemarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.dto.CommentDTO;
import com.jerry.gamemarket.dto.LikeDTO;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.entity.NormalUser;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.service.ArticleService;
import com.jerry.gamemarket.service.BanUserService;
import com.jerry.gamemarket.service.NormalUserService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import com.lly835.bestpay.rest.type.Get;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 叶俊晖
 * @date 2018/12/27 0027 15:21
 */

@Slf4j
@RestController
@RequestMapping("/seller/article")
public class SellerArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    BanUserService banUserService;
    @Autowired
    NormalUserService userService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size){
        //查询点评列表
        PageRequest request=new PageRequest(page-1,size);
        Page<Article> articlePage=articleService.findAll(request);
        map.put("currentPage",page);
        map.put("size",size);
        map.put("articleList",articlePage);
        map.put("pagesize",articlePage.getTotalPages());
        return new ModelAndView("article/list",map);
    }

    @PostMapping("/reply")
    public void reply(@Valid CreateComment comment,
                              BindingResult bindingResult,
                              Map<String,Object> map){
        //测试用例sellerId=qwe，sellerName=admin
        String sellerId="qwe";
        String sellerName="admin";
        comment.setUserId(sellerId);
        comment.setUserName(sellerName);
        comment.setRole(1);
        articleService.createComment(comment);

    }

    @DeleteMapping("/delete")
    public ResultVO deleteReply(@RequestParam("replyId") Integer replyId){
        articleService.deleteComment(replyId);
        return ResultVOUtil.success();
    }

    @GetMapping("/comments")
    public  Map<String, Object> getComments(Integer articleId){
        List<ArticleComment> response=articleService.getCommentsByAidManager(articleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("replies", response);
        return map;
    }

    @PostMapping("/ban")
    public ResultVO banUser(@RequestParam("userId") String userId,
                            @RequestParam("userName")String userName,
                            @RequestParam("articleId")String articleId,
                            @RequestParam("commentId")String commentId){
        banUserService.BanUser(userId,userName,"【订单点评】/评论/回复用语违规");
        if(commentId.equals("*")){
            banUserService.updateARole(Integer.parseInt(articleId));
        }else{
            banUserService.updateCRole(Integer.parseInt(commentId));
        }
        userService.updateRoleByUid(userId);
        return ResultVOUtil.success();
    }

    @PostMapping("/allow")
    public ResultVO AllowUser(@RequestParam("userId") String userId,
                            @RequestParam("articleId")String articleId,
                            @RequestParam("commentId")String commentId){
        banUserService.allowUser(userId);
        userService.updateRoleByUid(userId);
        if(commentId.equals("*")){
            banUserService.ReturnARole(Integer.parseInt(articleId));
        }else{
            banUserService.ReturnCRole(Integer.parseInt(commentId));
        }
        return ResultVOUtil.success();
    }
}
