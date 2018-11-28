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
                    <nav class="navbar navbar-default" role="navigation">

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                            <form  role="form" method="post" action="/seller/order/findByCase">

                              <div  style="float: left">
                                <label for="orderId">订单号</label>
                                <input id="orderId" name="orderId" type="text" class="form-control"/>
                              <label for="buyerName">用户姓名</label>
                                <input id="buyerName" name="buyerName" type="text" class="form-control"/>
                               </div>
                                <div style="float: left;margin-left: 25px">
                                    <label for="name">订单状态</label></br>
                                    <select name="orderStatus" class="form-control"><option></option>
                                        <option value="0"
                                        </option>新订单
                                        <option value="1"
                                                </option>已完成订单
                                        <option value="2"
                                                </option>已取消订单
                                    </select>

                                    <label for="name">支付状态</label></br>
                                    <select name="payStatus" class="form-control">
                                        <option></option>
                                        <option value="0"
                                        </option>未支付
                                        <option value="1"
                                        </option>已支付
                                        <option value="2"
                                        </option>等待支付
                                    </select>
                                </div>

                                    <div style="float: left;margin-left: 25px"><label>消费金额最大值</label> <input id="maxAmount" name="maxAmount" type="text" class="form-control"/>
                                    <label>消费金额最小值</label> <input id="minAmount" name="minAmount" type="text" class="form-control"/>
                                </div>
                                    <div  style="float: left;margin-left: 25px">
                                        <label for="orderId">店铺名称</label>
                                        <input id="orderId" name="canteenName" type="text" class="form-control"/>
                                        <label for="buyerName">联系方式</label>
                                        <input id="buyerName" name="buyerPhone" type="text" class="form-control"/>
                                    </div>
                                <button class="btn btn-default" type="submit" style="margin-left: 50px;margin-top: 45px">点击检索</button></div>
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
<script type="text/javascript">
    var searchAjax = function (pageNum) {
        var i = $("#orderId").val().trim();
        var searchOrderDto = {
            orderId: i,
            buyerName: null,
            buyerPhone: null,
            buyerAddress: null,
            canteenId: null,
            maxAmount: null,
            minAmount: null,
            orderStatus: null,
            payStatus: null,
            pageNum: pageNum,
            pageSize: 2
        };
        $.ajax({
            url:'/seller/order/findByCaseAjax',
            type: "post",
            data:JSON.stringify(searchOrderDto),
            contentType:'application/json',
            dataType: "json",
            success:function (data) {
                console.log(data);
            },
            error: function(msg) {
                console.log(msg)
            }
        })

    }
</script>
</body>
</html>