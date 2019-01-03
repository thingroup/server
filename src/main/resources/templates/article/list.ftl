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
                    <div align="center"><h3>点评列表</h3></div>
                    <table style="text-align: center" class="table table-bordered table-condensed" align="center">
                        <thead>
                        <tr style="background-color: beige;text-align: center">
                            <th>点评id</th>
                            <th>点评标题</th>
                            <th>图片</th>
                            <th>点评内容</th>
                            <th>点评作者</th>
                            <th>作者Id</th>
                            <th>订单id</th>
                            <th>评分</th>
                            <th>赞</th>
                            <th>踩</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list articleList.content as article>
                        <tr id="role_${article.role}" class="role_${article.articleId}">
                            <td onclick="getCommentList('${article.articleId}')">${article.articleId}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.articleName}</td>
                            <td onclick="getCommentList('${article.articleId}')"><img height="100" width="100" src="#" alt=""></td>
                            <td onclick="getCommentList('${article.articleId}')">${article.articleText}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.userName}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.userId}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.orderId}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.score}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.likes}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.dislikes}</td>
                            <td onclick="getCommentList('${article.articleId}')">${article.updateTime}</td>
                            <td><button onclick="showReplyForm('${article.articleId}','${article.userId}','${article.userName}','${article_index}','1')">回复</button>
                                <#if article.role==0>
                                    <input id="banbtn_${article.articleId}" type="submit" onclick="BanAllow('${article.userId}','${article.userName}','${article.articleId}','*')" value="禁止">
                                    <#else>
                                        <input id="banbtn_${article.articleId}" type="submit" onclick="BanAllow('${article.userId}','${article.userName}','${article.articleId}','*')" value="允许">
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="12">
                                <div class="testdiv_${article.articleId}"></div>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination " style="margin-left: 500px">
                    <#if currentPage==1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/article/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
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
                        <li><a href="/seller/article/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
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
                                    <button onclick="sendReply()">回复</button>
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
    var showedIndex;
    var checkId;
    var raid;
    var ruid;
    var runame;

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
        ctext=$("#comment").val();
        var url= 'http://localhost:8087/seller/article/reply';
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
                    ajaxCommentList(raid);
                    showedIndex=raid;
                    $("#comment").val("");
                });
            }else{
                alert("内容不能为空");
            }
        }else{
            alert("用户不能回复自己");
        }
    }

    //删除评论，回复
    function deleteReply(replyId,articleId) {
        $.ajax({
           dataType:"json",
           type:"delete",
           url:"http://localhost:8087/seller/article/delete?replyId="+replyId,
            success:function (data) {
               ajaxCommentList(articleId);
               showedIndex=articleId;
            }
        });
    }

    //展示，隐藏回复列表
    function getCommentList(articleId) {
        if($(".testdiv_"+articleId).text()==""){
            ajaxCommentList(articleId);
            if(showedIndex!=null){
                $(".testdiv_"+showedIndex).slideUp(400);
            }
            showedIndex=articleId;
        }else{
            $(".testdiv_"+showedIndex).slideUp(400);
            if(showedIndex==articleId){
                showedIndex=-1;
            }else{
                $(".testdiv_"+articleId).slideDown(400);
                showedIndex=articleId;
            }
        }
    }

    //获取评论表请求
    function ajaxCommentList(articleId) {
        $.ajax({
            dataType:"json",
            type:"GET",
            url:"http://localhost:8087/seller/article/comments",
            data:{articleId:articleId},
            success:function(data){
                if(data.replies.length>0){
                    var arr=data.replies;
                    var html="<table style='text-align: center' align='center' colspan='12' border='1' table table-bordered table-condensed>" +
                            "<thead>" +
                            "<tr colspan='12'>点评回复:</tr>"+
                            "<tr>" +
                            "<th>回复ID</th>" +
                            "<th>用户ID</th>" +
                            "<th>用户名</th>" +
                            "<th>被回复用户ID</th>" +
                            "<th>被回复用户名</th>" +
                            "<th>内容</th>" +
                            "<th>赞</th>" +
                            "<th>踩</th>" +
                            "<th>修改时间</th>" +
                            "<th colspan='2'>操作</th>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>";
                    for(var i=0;i<arr.length;i++){
                        if(arr[i].role==1){
                            html+="<tr style='background-color: cornsilk'>";
                        }else{
                            html+="<tr id='role_" +arr[i].role+
                                    "' class='role_" +articleId+"_"+arr[i].articleCommentId+
                                    "'>";
                        }
                        html+="<td>"+arr[i].articleCommentId+" </td>"+
                                "<td>"+arr[i].userId+" </td>"+
                                "<td>"+arr[i].userName+" </td>";
                        if(arr[i].lastUid!=null){
                            html+="<td> "+arr[i].lastUid+" </td>"+
                                    "<td>"+arr[i].lastUname+" </td>";
                        }else{
                            html+="<td> 无 </td>"+
                                    "<td> 无 </td>";
                        }
                        html+="<td>"+arr[i].commentText+" </td>"+
                                "<td>"+arr[i].likes+" </td>"+
                                "<td>"+arr[i].dislikes+" </td>"+
                                "<td>"+arr[i].updateTime+" </td>";
                        if(arr[i].role==1){
                            html+="<td><button class='cdelete_"+articleId+"_"+i+"' onclick=\"deleteReply('" +
                                    arr[i].articleCommentId+
                                    "','"+articleId
                                    +"')\">删除</button>"
                        }else{
                            html+="<td><button class='creply_"+articleId+"_"+i+"'onclick=\"showReplyForm(" +
                                    "'"+articleId+"'," +
                                    "'"+arr[i].userId+"'," +
                                    "'"+arr[i].userName+"',"+
                                    "'"+articleId+"_"+i
                                    +"','0')\">回复</button>";
                        }
                        if(arr[i].role==-1){
                            html+="<input type='submit' value='允许' id='banbtn_" +articleId+"_"+arr[i].articleCommentId+
                                    "'" +
                                    " onclick=\"BanAllow('" +
                                    arr[i].userId+"','"+
                                    arr[i].userName+"','"+
                                    articleId+"','"+
                                    arr[i].articleCommentId+
                                    "')\">"
                        }else if(arr[i].role==0){
                            html+="<input type='submit' value='禁止' id='banbtn_" +articleId+"_"+arr[i].articleCommentId+
                                    "'" +
                                    " onclick=\"BanAllow('" +
                                    arr[i].userId+"','"+
                                    arr[i].userName+"','"+
                                    articleId+"','"+
                                    arr[i].articleCommentId+
                                    "')\">"
                        }
                        html+= "</td><tr/>";
                    }
                    html+="</tbody>" +
                            "</table>";
                    $(".testdiv_"+articleId).html(html).slideUp(1);
                    $(".testdiv_"+articleId).slideDown(400);
                }else{
                    $(".testdiv_"+articleId).html("无评论/回复").slideUp(1);
                    $(".testdiv_"+articleId).slideDown(400);
                }
            },error:function(data){
                alert("系统错误");
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
            url:"http://localhost:8087/seller/article/allow",
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
           url:"http://localhost:8087/seller/article/ban",
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
        window.location.href="http://localhost:8087/seller/article/list?page="+page+"&size=10";
    }

</script>
</body>
</html>