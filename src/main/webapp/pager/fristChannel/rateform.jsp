<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error {
/* 		margin-left: 10px; */
/* 		width: auto; */
/* 		display: inline; */
		color:red;
	}
</style>
</head>
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i>渠道管理</li>
			<li><a href="${pageContext.request.contextPath}/fristChannel/list">一级渠道</a></li>
			<li class="active">设置分成比例</li>
		</ul>
	</div>
	<!-- Page Header -->
	<div class="page-header position-relative">

		<!--Header Buttons-->
		<div class="header-buttons">
			<a class="sidebar-toggler" href="javascript:void(0);"> <i class="fa fa-arrows-h"></i>
			</a> <a class="refresh" id="refresh-toggler" href="javascript:void(0);"> <i class="glyphicon glyphicon-refresh"></i>
			</a> <a class="fullscreen" id="fullscreen-toggler" href="javascript:void(0);"> <i class="glyphicon glyphicon-fullscreen"></i>
			</a>
		</div>
		<!--Header Buttons End-->
	</div>
	<!-- /Page Header -->

	<!-- Page Body -->
	<!--广告弹框 -->
	<div class="">
		<input type="hidden" name="voluumTrafficSourceId" id="voluumTrafficSourceId" value="${fristChannel.voluumTrafficSourceId}">
		<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
		<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
		<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
		<div class="modal-dialog" style="width:70%;" role="document">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-6  col-sm-5">
							<label><span class='text'>广告名称</span></label>
						</div>
						<div class="col-lg-4 row">
							<label><span class='text'>分配比例</span></label>
						</div>
					</div>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-lg-6 col-sm-5">
							<span class="text" style="">全局比例</span>
						</div>
						<div class="col-lg-4 row">
							<input type="text" class="colored-success" id="allRate" name="allRate" value=""/>
							<label class="error" for="" style="display:none;">请输入正确的比例</label>
							<nobr style="font-size:14px;color:red;">(0-1)</nobr>
							<label><input type="checkbox" id="allRateBox" name="allRateBox" onclick="setAllRate()" title=""><span class="text">全局设置</span></label>
						</div>

					</div>
					<hr>
					<c:forEach items="${listResources}" var="resource">
						<div class="row">
							<div class="col-lg-6 col-sm-5">
								<span class="text" style="">${resource.name}</span>
							</div>
							<div class="col-lg-4 row">
								<input type="text" class="colored-success" id="rate_${resource.id}" name="rateName" value="${resource.subscriptionRate}"/>
								<label class="error" for="rate_${resource.id}" style="display:none;">请输入正确的比例</label>
								<input type="hidden" class="colored-success" name="resourceIds" value="${resource.id}"/>
								<nobr style="font-size:14px;color:red;">(0-1)</nobr>
							</div>
						</div>
						<hr>
					</c:forEach>

				</div>

				<div class="modal-footer">
					<a type="" class="btn btn-primary" href="javascript:history.back();"> <i class="fa fa-mail-reply"></i> 返回</a>
					<button type="button" class="btn btn-primary" onclick="saveOperator()"><i class="fa fa-save"></i> 保存	</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function setAllRate() {
            var reg = /^[0-9]+.?[0-9]*$/;
            var flgBox = $("#allRateBox").is(":checked");
            if (flgBox) {
                var subscriptionRate = $.trim($("#allRate").val());
                if (subscriptionRate != null && subscriptionRate != "") {
                    if (!reg.test(subscriptionRate) || subscriptionRate > 1) {
                        $("#allRate").next().show();
                        $("#allRateBox").attr("checked",false);
                    }else{
                        $('input[name="resourceIds"]').each(function () {
                            var resourceId = $(this).val();
                            $("#allRate").next().hide();
                            $("#rate_"+resourceId).val(subscriptionRate);
                        });
					}
                }
            }
        }
        function saveOperator(){
			var str = "";
            var flg = true;
            var reg = /^[0-9]+.?[0-9]*$/;
            var flgBox = $("#allRateBox").is(":checked");
            if(!flgBox){
				$('input[name="resourceIds"]').each(function(){
					var resourceId = $(this).val();
					var subscriptionRate = $.trim($("#rate_"+resourceId).val());
					if(resourceId != null && resourceId != "" ){
					    if(subscriptionRate != null && subscriptionRate!="" ){
                            if(!reg.test(subscriptionRate) || subscriptionRate > 1){
                                $("#rate_"+resourceId).next().show();
                                flg = false;
                            }else{
                                $("#rate_"+resourceId).next().hide();
                                str += "{\"id\":\"" + resourceId + "\",\"rate\":\"" + subscriptionRate + "\"}&";
                            }
						}else{
                            $("#rate_"+resourceId).next().hide();
                            str += "{\"id\":\"" + resourceId + "\",\"rate\":\"" + 1 + "\"}&";
						}
					}
				});
            }else{
				var subscriptionRate = $.trim($("#allRate").val())!=""?$.trim($("#allRate").val()):"1";
				if (subscriptionRate != null && subscriptionRate != "") {
					if (confirm("当前渠道所有广告的分成比例将被修改，你确定修改吗？")) {
						if (!reg.test(subscriptionRate) || subscriptionRate > 1) {
							$("#allRate").next().show();
							flg = false;
						}
						$('input[name="resourceIds"]').each(function () {
							var resourceId = $(this).val();
							$("#allRate").next().hide();
                            str += "{\"id\":\"" + resourceId + "\",\"rate\":\"" + subscriptionRate + "\"}&";
						});
					}else{
                        flg = false;
					}
				} else {
					$("#allRate").next().show();
					flg = false;
				}
			}
            if(flg){
                $.ajax({
                    url: "${pageContext.request.contextPath}/fristChannel/saveRate",
                    type :"GET",
                    data:{rate:str,trafficId:$("#voluumTrafficSourceId").val(),keyword:$("#keyword").val(),currentPage:$("#currentPage").val(),pageSize:$("#pageSize").val()},
                    async: false,
                    dataType : "text",
                    success: function(date){
                        window.location = "${pageContext.request.contextPath}/fristChannel/list/?keyword="+$("#keyword").val()+"&currentPage="+${queryBean.currentPage}+"&pageSize="+${queryBean.pageSize};
                    }
                });
            }
        }
	</script>
</body>
</html>