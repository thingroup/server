package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.ArticleVO;
import com.jerry.gamemarket.VO.CommentVO;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.dto.CommentDTO;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.CreateArticle;
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

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid CreateArticle article, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建点评】 参数不正确，createArticle{}", article);
            throw new GameException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //测试用例uid=abc
        article.setUserId("abc");
        article.setUserName("abc");
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
            for (ArticleDTO x:
               dtos  ) {
                ArticleVO avo=new ArticleVO();
                BeanUtils.copyProperties(x,avo);
                List<CommentVO> commentVOS=new ArrayList<>();
                for (CommentDTO commentDTO:
                     x.getCommentList()) {
                    CommentVO commentVO=new CommentVO();
                    BeanUtils.copyProperties(commentDTO,commentVO);

                }
                articleVOS.add(avo);
            }
            return ResultVOUtil.success(articleVOS);
    }
}
