<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">
<div class="container" style="margin-top: 100px">
    <div class="row clearfix">

        <#--订单详情表数据-->
            <div class="col-md-10 column">
                <div align="center"><h3>${canteenDTO.canteenName}家商品列表</h3></div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>名称</th>
                    <th>图片</th>
                    <th>单价</th>
                    <th>库存</th>
                    <th>描述</th>
                    <th>类目</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <if canteenDTO.productInfoList??>
                <#list canteenDTO.productInfoList as productInfo>
                    <tr>
                        <td>${productInfo.productId}</td>
                        <td>${productInfo.productName}</td>
                        <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                        <td>${productInfo.productPrice}</td>
                        <td>${productInfo.productStock}</td>
                        <td>${productInfo.productDescription}</td>
                        <td>${productInfo.categoryType}</td>
                        <td>${productInfo.createTime}</td>
                        <td>${productInfo.updateTime}</td>
                        <td><a href="/seller/canteen/index?productId=${productInfo.productId}">修改</a></td>
                        <td>
                            <#if productInfo.getProductStatus() == 0>
                                <a href="/seller/canteen/off_sale?productId=${productInfo.productId}">下架</a>
                            <#else>
                                <a href="/seller/canteen/on_sale?productId=${productInfo.productId}">上架</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </if>
                </tbody>
            </table>
            <#--<div class="col-md-12 column">-->
                <#--<ul class="pagination pull-right">-->
                <#--<#if currentPage lte 1>-->
                    <#--<li class="disabled"><a href="#">上一页</a></li>-->
                <#--<#else>-->
                    <#--<li><a href="/seller/canteen/list?page=${currentPage - 1}&size=${size}">上一页</a></li>-->
                <#--</#if>-->

                <#--<#list 1..productInfoPage.getTotalPages() as index>-->
                    <#--<#if currentPage == index>-->
                        <#--<li class="disabled"><a href="#">${index}</a></li>-->
                    <#--<#else>-->
                        <#--<li><a href="/seller/canteen/list?page=${index}&size=${size}">${index}</a></li>-->
                    <#--</#if>-->
                <#--</#list>-->

                <#--<#if currentPage gte productInfoPage.getTotalPages()>-->
                    <#--<li class="disabled"><a href="#">下一页</a></li>-->
                <#--<#else>-->
                    <#--<li><a href="/seller/canteen/list?page=${currentPage + 1}&size=${size}">下一页</a></li>-->
                <#--</#if>-->
                <#--</ul>-->
            <#--</div>-->
        </div>
    </div>
</div>
</body>
</html>