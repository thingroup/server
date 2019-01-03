package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.ArticleVO;
import com.jerry.gamemarket.VO.CommentVO;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.dto.CommentDTO;
import com.jerry.gamemarket.dto.LikeDTO;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.form.LikesForm;
import com.jerry.gamemarket.service.ArticleService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 叶俊晖
 * @date 2018/12/25 0025 14:44
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/buyer/article")
public class BuyerArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/createArticle")
    public ResultVO<Map<String,String>> createArticle(@Valid CreateArticle article, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建点评】 参数不正确，createArticle{}", article);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        article.setUserId("abc");
        article.setUserName("abc");
        ArticleDTO articleDTO=articleService.OneArticle(article.getUserId(),article.getCanteenId());
        if(articleDTO!=null){
            log.error("【创建点评】 已经创建过，避免刷评,createArticle{}",article);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        articleService.createArticle(article);
        ArticleDTO createResult=articleService.OneArticle(article.getUserId(),article.getCanteenId());
        Map<String,String> map=new HashMap<>();
        map.put("articleId",createResult.getArticleId()+"");
        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<ArticleDTO>>list(@RequestParam("canteenId") String canteenId){
        if (StringUtils.isEmpty(canteenId)) {
            log.error("【查询点评列表】canteenId为空");
            throw new GameException(ResultEnum.PARAM_ERROR);
        }
        List<ArticleDTO> dtos=articleService.ArticleList(canteenId);
        List<ArticleVO> articleVOS=new ArrayList<>();
        //测试用例uid=abc
        String uid="abc";
        List<LikeDTO> likeDTOS=articleService.LikeList(uid,canteenId);
            for (ArticleDTO x:
               dtos  ) {
                ArticleVO avo=new ArticleVO();
                BeanUtils.copyProperties(x,avo);
                LikeDTO likeDTO=articleService.findByUidAid(uid,x.getArticleId()+"");
                if(likeDTO!=null){
                    avo.setStatus(likeDTO.getType());
                }
                List<CommentVO> commentVOS=new ArrayList<>();
                for (CommentDTO commentDTO:
                     x.getCommentList()) {
                    CommentVO commentVO=new CommentVO();
                    BeanUtils.copyProperties(commentDTO,commentVO);
                    likeDTO=articleService.findByUidCid(uid,commentDTO.getArticleCommentId()+"");
                    if(likeDTO!=null){
                        commentVO.setStatus(likeDTO.getType());
                    }
                    commentVOS.add(commentVO);
                }
                avo.setCommentList(commentVOS);
                articleVOS.add(avo);
            }
            return ResultVOUtil.success(articleVOS);
    }

    @PostMapping("/createComment")
    public ResultVO<CommentDTO> createComment(@Valid CreateComment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建评论】 参数不正确，createComment{}", comment);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        comment.setUserId("abc");
        comment.setUserName("abc");
        articleService.createComment(comment);
        CommentDTO createResult=articleService.LatestComment(comment.getUserId());
        return ResultVOUtil.success(createResult);
    }

    @DeleteMapping("/deleteArticle")
    public ResultVO deleteArticle(@RequestParam("articleId") Integer articleId,
                                  @RequestParam("userId") String userId){
        //测试用例uid=abc
        String uid="abc";
        if(!userId.equals(uid)){
            log.error("【删除评论】用户不匹配, deleteArticle{}");
            return ResultVOUtil.error(404,"删除失败");
        }
        articleService.deleteArticle(articleId,uid);
        return ResultVOUtil.success();
    }

    @DeleteMapping("/deleteComment")
    public ResultVO deleteComment(@RequestParam("articleCommentId") Integer commentId,
                                  @RequestParam("userId") String userId){
        //测试用例uid=abc
        String uid="abc";
        if(!userId.equals(uid)){
            log.error("【删除评论】用户不匹配, deleteComment{}");
            return ResultVOUtil.error(404,"删除失败");
        }
        articleService.deleteComment(commentId);
        return ResultVOUtil.success();
    }

    @PostMapping("/addlikes")
    public ResultVO addLikes(@Valid LikesForm likesForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【点赞】 参数不正确，addLikes{}", likesForm);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        String uid="abc";
        if(uid==null){
            //登录拦截
        }
        Integer status=likesForm.getStatus();
        Integer type=likesForm.getType();
        LikeDTO likeDTO=new LikeDTO();
        BeanUtils.copyProperties(likesForm,likeDTO);
        likeDTO.setUserId(uid);
        //未有点赞、踩记录
        if(status==0){
            //创建记录
            if(type>0){
                if(likeDTO.getArticleCommentId()==null){
                    articleService.addAlike(likeDTO);
                }else{
                    articleService.addClike(likeDTO);
                }
            }else{
                if(likeDTO.getArticleCommentId()==null){
                    articleService.addAHate(likeDTO);
                }else{
                    articleService.addCHate(likeDTO);
                }
            }
        }else{
            //取消赞，踩
            if(status==type){
                if(type>0){
                    if(likeDTO.getArticleCommentId()==null){
                        articleService.reduceAlike(likeDTO);
                    }else{
                        articleService.reduceClike(likeDTO);
                    }
                }else{
                    if(likeDTO.getArticleCommentId()==null){
                        articleService.reduceAHate(likeDTO);
                    }else{
                        articleService.reduceCHate(likeDTO);
                    }
                }
            }else{
                //修改赞踩
                articleService.changeLike(likeDTO);
            }
        }
        return ResultVOUtil.success();
    }
}
