<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper" style="margin-left: 300px" >
        <div class="container-fluid" >
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <div style="margin-top: 10px" ><h3>店铺信息</h3></div>
                    <form role="form" method="post" action="/seller/canteen/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="canteenName" type="text" class="form-control" value="${(canteenInfo.canteenName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input name="canteenName" type="text" class="form-control" value="${(canteenInfo.canteenAddress)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>人均消费</label>
                            <input name="personConsume" type="text" class="form-control" value="${(canteenInfo.personConsume)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>包间数量</label>
                            <input name="canteenRoomStock" type="number" class="form-control" value="${(canteenInfo.canteenRoomStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="canteenDescription" type="text" class="form-control" value="${(canteenInfo.canteenDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(canteenInfo.canteenIcon)!''}" alt="">
                            <input name="canteenIcon" type="text" class="form-control" value="${(canteenInfo.canteenIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (canteenInfo.categoryType)?? && canteenInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(canteenInfo.canteenId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>