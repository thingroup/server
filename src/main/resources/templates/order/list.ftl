<html>
<#include "../common/header.ftl">
<#--<script language="javascript" type="text/javascript">-->
    <#--function gettypename(){-->
        <#--$("#typename").val();-->
        <#--var typename = $('#typeid option:selected') .text();//选中的值-->
        <#--var typename_val = $('#typeid option:selected') .val();//选中的value-->
        <#--if(typename_val==""){-->
            <#--$("#typename").val("");-->
        <#--}else{-->
            <#--$("#typename").val(typename);-->
        <#--}-->
    <#--}-->
<#--</script>-->

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 50px">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-10 column">

                    <div align="center"><h3>订单列表</h3></div>
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">检索订单</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                            <form class="navbar-form navbar-left" role="form" method="post" action="/seller/order/findByCase">
                                <div class="form-group">
                                    <select name="tip" class="form-control" >
                                        <option>---请选择---</option>
                                        <option value="order_id">订单ID</option>
                                        <option value="buyer_name">姓名</option>
                                        <option value="buyer_phone">手机号</option>
                                        <option value="buyer_address">地址</option>
                                        <option value="order_amount">金额</option>
                                        <option value="canteen_id">店铺ID</option>
                                        <option value="order_status">订单状态 </option>
                                        <option value="pay_status">支付状态</option>
                                    </select>
                                </div>  <div class="nav navbar-left">
                                <input name="text" type="text" class="form-control"/>
                            </div>
                                <button class="btn btn-default" type="submit" >Search</button>
                            </form>

                        </div>

                    </nav>

                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号 </th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>店铺ID</th>
                            <th>订单状态 </th>
                            <th>支付状态</th>
                            <th>创建时间 </th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOS.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.buyerAddress}</td>
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
                        <#list 1..orderDTOS.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"> <a href="#">${index}</a></li>
                            <#else >
                                <li><a href="/seller/order/list?page=${index}&size=${size}"> ${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOS.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/seller/order/list?page=${currentPage +1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>