<html>
<#include "../common/header.ftl">
<style>
    #role_-1{background-color: #ff8b8c;}
    #role_0{background-color: azure}
</style>

<script src="/static/js/jquery.min.js"></script>
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" >
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div align="center"><h3>文章列表</h3></div>
                    <table style="text-align: center" class="table table-bordered table-condensed" align="center">
                        <thead>
                        <tr style="background-color: beige;text-align: center">
                            <th>文章id</th>
                            <th>文章标题</th>
                            <th>图片</th>
                            <th>文章内容</th>
                            <th>作者</th>
                            <th>作者Id</th>
                            <th>评分</th>
                            <th>赞</th>
                            <th>踩</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list CanarticleList as article>
                        <tr id="role_${article.role}" class="role_${article.caId}">
                            <td onclick="getCommentList('${article.caId}')">${article.caId}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.name}</td>
                            <td onclick="getCommentList('${article.caId}')"><img height="100" width="100" src="#" alt=""></td>
                            <td onclick="getCommentList('${article.caId}')">${article.text}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.userName}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.userId}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.score}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.likes}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.dislikes}</td>
                            <td onclick="getCommentList('${article.caId}')">${article.updateTime}</td>
                            <td><button onclick="showReplyForm('${article.caId}','${article.userId}','${article.userName}','${article_index}','1')">评论</button>
                                <#if article.role==0>
                                    <input id="banbtn_${article.caId}" type="submit" onclick="BanAllow('${article.userId}','${article.userName}','${article.caId}','*')" value="禁止">
                                <#else>
                                    <input id="banbtn_${article.caId}" type="submit" onclick="BanAllow('${article.userId}','${article.userName}','${article.caId}','*')" value="允许">
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="12">
                                <div class="testdiv_${article.caId}"></div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination " style="margin-left: 43%">
                    <#if currentPage==1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/article/canteen/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>
                        <li>
                            <a >
                                <select id="page_select">
                                <#list 1..pagesize as t>
                                    <#if t==currentPage>
                                        <option value ="${t}" selected="selected">${t}</option>
                                    <#else>
                                        <option value ="${t}">${t}</option>
                                    </#if>
                                </#list>
                                </select>
                                /${pagesize}
                            </a>
                        </li>
                        <li>
                            <a onclick="chosePage()">GO</a>
                        </li>
                    <#if currentPage == pagesize>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/article/canteen/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        //页面跳转
        function getCommentList(aid) {
            window.location.href="http://localhost:8087/seller/article/canteen/detail?articleId="+aid;
        }

        //禁止，允许
        function BanAllow(uid,uname,articleId,commentId) {
                if($("#banbtn_"+articleId).val()=="禁止"){
                    banUser(uid,uname,articleId,commentId);
                    $(".role_"+articleId).attr('id','role_-1');
                    $("#banbtn_"+articleId).val("允许");
                }else{
                    allowUser(uid,articleId,commentId);
                    $(".role_"+articleId).attr('id','role_0');
                    $("#banbtn_"+articleId).val("禁止");
                }
        }
        //允许用户请求
        function allowUser(uid,articleId,commentId) {
            var data={
                "userId":uid,
                "articleId":articleId,
                "commentId":commentId
            };
            $.ajax({
                dataType:"json",
                type:"POST",
                url:"http://localhost:8087/seller/article/canteen/allow",
                data:data,
                success:function (data) {
                    alert("恢复成功");
                },
                error:function (data) {
                    alert("系统错误");
                }
            });
        }

        //禁止用户请求
        function banUser(uid,uname,articleId,commentId) {
            var data={
                "userId":uid,
                "userName":uname,
                "articleId":articleId,
                "commentId":commentId
            }
            $.ajax({
                dataType:"json",
                type:"POST",
                url:"http://localhost:8087/seller/article/canteen/ban",
                data:data,
                success:function (data) {
                    alert("禁用成功");
                },
                error:function (data) {
                    alert("系统错误");
                }
            });
        }

        //选择页面
        function chosePage() {
            var page=$("#page_select").val();
            window.location.href="http://localhost:8087/seller/article/canteen/list?page="+page+"&size=10";
        }

    </script>
</body>
</html>