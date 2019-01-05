package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.CanArticleVO;
import com.jerry.gamemarket.VO.CanCommentVo;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.dto.CanArticleDTO;
import com.jerry.gamemarket.dto.CanCommentDTO;
import com.jerry.gamemarket.dto.CanLikeDTO;
import com.jerry.gamemarket.dto.LikeDTO;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.form.LikesForm;
import com.jerry.gamemarket.service.CanArticleService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2019/1/4 0004 13:17
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/buyer/article/canteen")
public class BuyerCanArticleController {

    @Autowired
    CanArticleService articleService;

    @PostMapping("/createArticle")
    public ResultVO<Map<String,String>> createArticle(@Valid CreateArticle article, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建文章】 参数不正确，createArticle{}", article);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        article.setUserId("abc");
        article.setUserName("abc");
        CanArticleDTO articleDTO=articleService.OneArticle(article.getUserId(),article.getCanteenId());
        if(articleDTO!=null){
            log.error("【创建文章】 已经创建过，避免刷评,createCanArticle{}",article);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        articleService.createArticle(article);
        CanArticleDTO createResult=articleService.OneArticle(article.getUserId(),article.getCanteenId());
        Map<String,String> map=new HashMap<>();
        map.put("articleId",createResult.getCaId()+"");
        return ResultVOUtil.success(map);
    }

    @GetMapping("/list")
    public ResultVO<List<CanArticleVO>> list(){
        List<CanArticleDTO> dtos=articleService.ArticleList();
        List<CanArticleVO> articleVOS=new ArrayList<>();
        //测试用例uid=testUser_1
        String uid="testUser_1";
        List<CanLikeDTO> likeDTOS=articleService.LikeList(uid);
        for(CanArticleDTO x:dtos){
            CanArticleVO vo=new CanArticleVO();
            BeanUtils.copyProperties(x,vo);
            CanLikeDTO canLikeDTO=articleService.findByUidAid(uid,x.getCaId());
            if(canLikeDTO!=null){
                vo.setStatus(canLikeDTO.getType());
            }
            if(vo.getText().length()>10){
                vo.setText(vo.getText().substring(0,9)+"......");
            }
            articleVOS.add(vo);
        }
        return ResultVOUtil.success(articleVOS);
    }

    @PostMapping("/createComment")
    public ResultVO<CanCommentDTO> createComment(@Valid CreateComment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建文章评论】 参数不正确，createComment{}", comment);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        comment.setUserId("testUser_1");
        comment.setUserName("testUserName_1");
        articleService.createComment(comment);
        CanCommentDTO createResult=articleService.LatestComment(comment.getUserId());
        return ResultVOUtil.success(createResult);
    }

    @DeleteMapping("/deleteArticle")
    public ResultVO deleteArticle(@RequestParam("caId") Integer articleId,
                                  @RequestParam("userId") String userId){
        //测试用例uid=abc
        String uid="abc";
        if(!userId.equals(uid)){
            log.error("【删除文章】用户不匹配, deleteArticle{}");
            return ResultVOUtil.error(404,"删除失败");
        }
        articleService.deleteArticle(articleId,uid);
        return ResultVOUtil.success();
    }

    @DeleteMapping("/deleteComment")
    public ResultVO deleteComment(@RequestParam("canCommentId") Integer commentId,
                                  @RequestParam("userId") String userId){
        //测试用例uid=abc
        String uid="abc";
        if(!userId.equals(uid)){
            log.error("【删除评论】用户不匹配, deleteComment{}");
            return ResultVOUtil.error(404,"删除失败");
        }
        articleService.deleteCommentByUid(commentId,uid);
        return ResultVOUtil.success();
    }

    @PostMapping("/addlikes")
    public ResultVO addLikes(@Valid LikesForm likesForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【文章点赞】 参数不正确，addLikes{}", likesForm);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        String uid="testUser_1";
        if(uid==null){
            //登录拦截
        }
        Integer status=likesForm.getStatus();
        Integer type=likesForm.getType();
        CanLikeDTO likeDTO=new CanLikeDTO();
        BeanUtils.copyProperties(likesForm,likeDTO);
        likeDTO.setCanCommentId(likesForm.getArticleCommentId());
        likeDTO.setCaId(likesForm.getArticleId());
        likeDTO.setUserId(uid);
        //未有点赞、踩记录
        System.out.println("test+ status="+status+"_type="+type);
        if(status==0){
            //创建记录
            if(type>0){
                if(likeDTO.getCanCommentId()==null){
                    articleService.addAlike(likeDTO);
                }else{
                    articleService.addClike(likeDTO);
                }
            }else{
                if(likeDTO.getCanCommentId()==null){
                    articleService.addAHate(likeDTO);
                }else{
                    articleService.addCHate(likeDTO);
                }
            }
        }else{
            //取消赞，踩
            if(status==type){
                if(type>0){
                    if(likeDTO.getCanCommentId()==null){
                        articleService.reduceAlike(likeDTO);
                    }else{
                        articleService.reduceClike(likeDTO);
                    }
                }else{
                    if(likeDTO.getCanCommentId()==null){
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

    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("articleId")Integer caid,
                               Map<String,Object> map){
        CanArticleDTO dto=articleService.userfindByAid(caid);
        CanArticleVO vo=new CanArticleVO();
        BeanUtils.copyProperties(dto,vo);
        //测试用户testUser_1
        String uid="testUser_1";
        CanLikeDTO likeDTO=articleService.findByUidAid(uid,caid);
        if(likeDTO!=null){
            vo.setStatus(likeDTO.getType());
        }
        List<CanCommentDTO> cList=articleService.UserGetCommentsByAidManager(caid);
        List<CanCommentVo> voList=new ArrayList<>();
        map.put("detail",vo);
        for(CanCommentDTO x:cList){
            CanCommentVo canCommentVo=new CanCommentVo();
            BeanUtils.copyProperties(x,canCommentVo);
            canCommentVo.setText(x.getCommentText());
            CanLikeDTO canLikeDTO=articleService.findByUidCid(uid,x.getCanCommentId());
            if(canLikeDTO!=null){
                canCommentVo.setStatus(canLikeDTO.getType());
            }
            if(x.getLastUid()==null){
                canCommentVo.setLastUname("");
                canCommentVo.setLastUid("");
            }
            voList.add(canCommentVo);
        }
        map.put("commentList",voList);
        return ResultVOUtil.success(map);
    }

}
