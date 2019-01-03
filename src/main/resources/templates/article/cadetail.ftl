<html>
<#include "../common/header.ftl">
<style>
    #role_-1{background-color: #ff8b8c;}
    #role_0{background-color: azure}
    #role_1{background-color: cornsilk}
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
                    <div align="center"><h3>文章详情</h3></div>
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
                        <tr id="role_${detail.role}" class="role_${detail.caId}">
                            <td>${detail.caId}</td>
                            <td>${detail.name}</td>
                            <td><img height="100" width="100" src="#" alt=""></td>
                            <td>${detail.text}</td>
                            <td>${detail.userName}</td>
                            <td>${detail.userId}</td>
                            <td>${detail.score}</td>
                            <td>${detail.likes}</td>
                            <td>${detail.dislikes}</td>
                            <td>${detail.updateTime}</td>
                            <td><button onclick="showReplyForm('${detail.caId}','${detail.userId}','${detail.userName}','*','1')">评论</button>
                                <#if detail.role==0>
                                    <input id="banbtn_${detail.caId}" type="submit" onclick="BanAllow('${detail.userId}','${detail.userName}','${detail.caId}','*')" value="禁止">
                                <#else>
                                    <input id="banbtn_${detail.caId}" type="submit" onclick="BanAllow('${detail.userId}','${detail.userName}','${detail.caId}','*')" value="允许">
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="12">
                                <div class="testdiv_${detail.caId}">
                                    <div align="center"><h3>文章评论回复</h3></div>

                                        <#if commentsize!=0 >
                                        <table style="text-align: center" class="table table-bordered table-condensed" align="center">
                                            <thead>
                                            <th>评论id</th>
                                            <th>评论用户名</th>
                                            <th>评论用户Id</th>
                                            <th>被评论用户名</th>
                                            <th>被评论用户Id</th>
                                            <th>内容</th>
                                            <th>赞</th>
                                            <th>踩</th>
                                            <th>修改时间</th>
                                            <th colspan="2">操作</th>
                                            </thead>
                                            <tbody>
                                                <#list commentList as comment>
                                                <tr id="role_${comment.role}" class="role_${detail.caId}_${comment.canCommentId}">
                                                    <td>${comment.canCommentId}</td>
                                                    <td>${comment.userName}</td>
                                                    <td>${comment.userId}</td>
                                                    <#if comment.lastUid??>
                                                        <td>${comment.lastUname}</td>
                                                        <td>${comment.lastUid}</td>
                                                        <#else>
                                                        <td>无</td>
                                                        <td>无</td>
                                                    </#if>
                                                    <td>${comment.commentText}</td>
                                                    <td>${comment.likes}</td>
                                                    <td>${comment.dislikes}</td>
                                                    <td>${comment.updateTime}</td>
                                                    <td>
                                                        <#if comment.role==1>
                                                            <button onclick="deleteReply('${comment.canCommentId}')">删除</button>
                                                            <#else>
                                                                <button onclick="showReplyForm('${comment.caId}','${comment.userId}','${comment.userName}','${comment_index}','0')">回复</button>
                                                        </#if>

                                                        <#if comment.role==0>
                                                            <input id="banbtn_${comment.caId}_${comment.canCommentId}" type="submit" onclick="BanAllow('${comment.userId}','${comment.userName}','${comment.caId}','${comment.canCommentId}')" value="禁止">
                                                        <#elseif comment.role=-1>
                                                            <input id="banbtn_${comment.caId}_${comment.canCommentId}" type="submit" onclick="BanAllow('${comment.userId}','${comment.userName}','${comment.caId}','${comment.canCommentId}')" value="允许">
                                                        </#if>
                                                    </td>
                                                </tr>
                                                </#list>
                                            </tbody>
                                            <#else>
                                            <h1>无</h1>
                                        </#if>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--回复表单-->
                <div id="replyForm" style="visibility: hidden;margin-left: 100px">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="3">         点评回复       </th>
                            <th><button onclick="hideReplyForm()">×</button></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="4"><p><label id="lastUName"></label></p></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <input style="height: 30px;width: 170px" id="comment" name="commentText">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button id="clearbtn" onclick="clearCommentText()">清空</button>
                            </td>
                            <td colspan="2"></td>
                            <td>
                                <button onclick="sendReply()">发送</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        //测试用户qwe
        var currentUser="qwe";
        var Replybtn=false;
        var checkId;
        var raid;
        var ruid;
        var runame;


        //页面跳转
        function getCommentList(aid) {
            window.location.href="http://localhost:8087/seller/article/canteen/detail?articleId="+aid;
        }

        //清空，回复回复内容
        var savedata;
        function clearCommentText() {
            if($("#clearbtn").text()=="清空"){
                savedata=$("#comment").val();
                if($("#comment").val()!=""){
                    $("#clearbtn").text("恢复");
                    $("#comment").val("");
                }
            }else{
                $("#comment").val(savedata);
                $("#clearbtn").text("清空");
            }
        }

        //初始化回复框
        function setReplyForm(aid,uid,uname,type) {
            var x=event.clientX;
            var y=event.clientY;
            document.getElementById("replyForm").style.top=y;
            document.getElementById("replyForm").style.left=x+100;
            document.getElementById("replyForm").style.visibility="visible";
            if(type==0){
                document.getElementById("lastUName").innerHTML="回复 @ ："+uname.valueOf()+" : ";
                ruid=uid;
                runame=uname;
            }else{
                document.getElementById("lastUName").innerHTML="评论 : "+uname.valueOf()+" 的点评 : ";
            }
            raid=aid;
            Replybtn=true;
        }

        //展示回复框
        function showReplyForm(aid,uid,uname,index,type){
            if(!Replybtn){
                checkId=index;
                setReplyForm(aid,uid,uname,type);
            }else{
                if(checkId==index){
                    hideReplyForm();
                }else{
                    checkId=index;
                    setReplyForm(aid,uid,uname,type);
                    $("#comment").val("");
                }
            }
        }
        //隐藏回复框
        function hideReplyForm() {
            Replybtn=false;
            document.getElementById("replyForm").style.visibility="hidden";
        }

        //发送回复
        function sendReply() {
            raid=${detail.caId};
            ctext=$("#comment").val();
            var url= 'http://localhost:8087/seller/article/canteen/reply';
            var comment={
                "articleId":raid,
                "lastUname":runame,
                "lastUid":ruid,
                "commentText":ctext
            };
            if(currentUser!=ruid){
                if(ctext!=""){
                    $.post(url,comment,function (data) {
                        alert("发送成功");
                        hideReplyForm();
                        $("#comment").val("");
                        window.location.href="http://localhost:8087/seller/article/canteen/detail?articleId="+${detail.caId};
                    });
                }else{
                    alert("内容不能为空");
                }
            }else{
                alert("用户不能回复自己");
            }
        }

        //删除评论，回复
        function deleteReply(replyId) {
            $.ajax({
                dataType:"json",
                type:"delete",
                url:"http://localhost:8087/seller/article/canteen/delete?replyId="+replyId,
                success:function (data) {
                    window.location.href="http://localhost:8087/seller/article/canteen/detail?articleId="+${detail.caId};
                }
            });
        }

        //禁止，允许
        function BanAllow(uid,uname,articleId,commentId) {
            if(commentId=="*"){
                if($("#banbtn_"+articleId).val()=="禁止"){
                    banUser(uid,uname,articleId,commentId);
                    $(".role_"+articleId).attr('id','role_-1');
                    $("#banbtn_"+articleId).val("允许");
                }else{
                    allowUser(uid,articleId,commentId);
                    $(".role_"+articleId).attr('id','role_0');
                    $("#banbtn_"+articleId).val("禁止");
                }
            }else{
                if($("#banbtn_"+articleId+"_"+commentId).val()=="禁止"){
                    banUser(uid,uname,articleId,commentId);
                    $(".role_"+articleId+"_"+commentId).attr('id','role_-1');
                    $("#banbtn_"+articleId+"_"+commentId).val("允许");
                }else{
                    allowUser(uid,articleId,commentId);
                    $(".role_"+articleId+"_"+commentId).attr('id','role_0');
                    $("#banbtn_"+articleId+"_"+commentId).val("禁止");
                }
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