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
                    <div align="center"><h3>店铺列表</h3></div>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商铺ID</th>
                            <th>商铺名称</th>
                            <th>商铺地址</th>
                            <th>包间数量</th>
                            <th>餐厅描述 </th>
                            <th>餐厅状态</th>
                            <th>联系方式</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list canteenDTOS.content as canteenDTO>
                        <tr>
                            <td>${canteenDTO.canteenId}</td>
                            <td>${canteenDTO.canteenName}</td>
                            <td>${canteenDTO.canteenAddress}</td>
                            <td>${canteenDTO.canteenRoomStock}</td>
                            <td>${canteenDTO.canteenDescription}</td>
                            <td>
                                <#if canteenDTO.canteenStatus = 0>
                                    营业
                                <#elseif canteenDTO.canteenStatus = 1>
                                    打烊了
                                </#if>
                            </td>
                            <td>${canteenDTO.canteenPhone}</td>
                            <td><a href="/seller/canteen/goods?canteenId=${canteenDTO.canteenId}">进店</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li>
                                <a href="#">上一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a>
                            </li>
                        </#if>
                        <#list 1..canteenDTOS.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"> <a href="#">${index}</a></li>
                            <#else >
                                <li><a href="/seller/shops/list?page=${index}&size=${size}"> ${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte canteenDTOS.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/seller/order/shops?page=${currentPage +1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<#--<td>-->
<#--<#if canteenDTO.canteenStatus = 0>-->
    <#--营业-->
<#--<#elseif canteenDTO.canteenStatus = 1>-->
    <#--打烊了-->
<#--</#if>-->
<#--</td>-->

</body>
</html>