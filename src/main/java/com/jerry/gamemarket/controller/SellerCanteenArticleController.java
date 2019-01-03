package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.CanteenArticle;
import com.jerry.gamemarket.entity.CanteenComment;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.service.BanUserService;
import com.jerry.gamemarket.service.CanArticleService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 14:24
 */
@Slf4j
@RestController
@RequestMapping("/seller/article/canteen")
public class SellerCanteenArticleController {
    @Autowired
    CanArticleService canArticleService;

    @Autowired
    BanUserService banUserService;

    CanteenArticle subText(CanteenArticle article){
        if(article.getText().length()>10){
            article.setText(article.getText().substring(0,10)+"......");
        }
        return article;
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size){
        //查询点评列表
        PageRequest request=new PageRequest(page-1,size);
        Page<CanteenArticle> articlePage=canArticleService.findCanArticle(request);
        map.put("currentPage",page);
        map.put("size",size);
        map.put("CanarticleList",articlePage.getContent().stream().map(e->subText(e)).collect(Collectors.toList()));
        map.put("pagesize",articlePage.getTotalPages());
        return new ModelAndView("article/calist",map);
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
        canArticleService.createComment(comment);
    }

    @DeleteMapping("/delete")
    public ResultVO deleteReply(@RequestParam("replyId") Integer replyId){
        canArticleService.deleteComment(replyId);
        return ResultVOUtil.success();
    }

    @GetMapping("/comments")
    public  Map<String, Object> getComments(Integer articleId){
        List<CanteenComment> response=canArticleService.getCommentsByAidManager(articleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("replies", response);
        return map;
    }

    @PostMapping("/ban")
    public ResultVO banUser(@RequestParam("userId") String userId,
                            @RequestParam("userName")String userName,
                            @RequestParam("articleId")String articleId,
                            @RequestParam("commentId")String commentId){
        banUserService.BanUser(userId,userName,"【文章】/评论/回复用语违规");
        if(commentId.equals("*")){
            banUserService.updateCARole(Integer.parseInt(articleId));
        }else{
            banUserService.updateCCRole(Integer.parseInt(commentId));
        }
        return ResultVOUtil.success();
    }

    @PostMapping("/allow")
    public ResultVO AllowUser(@RequestParam("userId") String userId,
                              @RequestParam("articleId")String articleId,
                              @RequestParam("commentId")String commentId){
        banUserService.allowUser(userId);
        if(commentId.equals("*")){
            banUserService.ReturnCARole(Integer.parseInt(articleId));
        }else{
            banUserService.ReturnCCRole(Integer.parseInt(commentId));
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("articleId")Integer caid,
                               Map<String,Object> map){
        map.put("detail",canArticleService.findByAid(caid));
        map.put("commentList",canArticleService.getCommentsByAidManager(caid));
        map.put("commentsize",canArticleService.getCommentsByAidManager(caid).size());
        return new ModelAndView("article/cadetail",map);
    }
}
