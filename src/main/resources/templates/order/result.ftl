<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 50px">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-10 column">

                    <div align="center"><h3>为您查找到以下订单</h3></div>

                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号 </th>
                            <th>金额</th>
                            <th>店铺ID</th>
                            <th>订单状态 </th>
                            <th>支付状态</th>
                            <th>创建时间 </th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list queryResults.results as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.orderAmount}</td>
                            <td>${orderDTO.canteenId}</td>
                            <td>
                                <#if orderDTO.orderStatus = 0>
                                    新订单
                                <#elseif orderDTO.orderStatus = 1>
                                    订单已完成
                                <#else>
                                    订单已取消
                                </#if>
                            </td>
                            <td>
                                <#if orderDTO.payStatus = 0>
                                    未支付
                                <#elseif orderDTO.payStatus = 1>
                                    已支付
                                <#else>
                                    等待支付
                                </#if>
                            </td>
                            <td>${orderDTO.createTime}</td>
                            <td><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td>
                                <#if orderDTO.getOrderStatus() == 0>
                                    <a href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                </#if>
                            <#--<#if orderDTO.getOrderStatus() = 2>-->
                            <#--<a href="/seller/order/cancel?orderId=${orderDTO.orderId}" class="disabled">已取消</a>-->
                            <#--</#if>-->
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#--<div class="col-md-12 column">-->
                        <#--<ul class="pagination pull-right">-->
                        <#--<#if currentPage lte 1>-->
                            <#--<li>-->
                                <#--<a href="#">上一页</a>-->
                            <#--</li>-->
                        <#--<#else >-->
                            <#--<li>-->
                                <#--<a href="/seller/order/findByCasebutton?page=${currentPage -1}&size=${size}">上一页</a>-->
                            <#--</li>-->
                        <#--</#if>-->
                        <#--<#list 1..queryResults.total/size as index>-->
                            <#--<#if currentPage == index>-->
                                <#--<li class="disabled"> <a href="#">${index}</a></li>-->
                            <#--<#else >-->
                                <#--<li><a href="/seller/order/findByCasebutton?page=${index}&size=${size}"> ${index}</a></li>-->
                            <#--</#if>-->
                        <#--</#list>-->
                        <#--<#if currentPage gte queryResults.total>-->
                            <#--<li class="disabled">-->
                                <#--<a href="#">下一页</a>-->
                            <#--</li>-->
                        <#--<#else >-->
                            <#--<li>-->
                                <#--<a href="/seller/order/findByCasebutton?page=${currentPage +1}&size=${size}">下一页</a>-->
                            <#--</li>-->
                        <#--</#if>-->
                        <#--</ul>-->
                    <#--</div>-->
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>