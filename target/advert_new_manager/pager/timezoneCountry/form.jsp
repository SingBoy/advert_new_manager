<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<li><i class="fa fa-home"></i>广告资源管理</li>
			<li><a href="${pageContext.request.contextPath}/timezoneCountry/list">国家时区管理</a></li>
			<li class="active"> ${timezoneCountry eq null ? "新增" : "编辑" }国家时区</li>
		</ul>
	</div>
	<!-- Page Header -->
	<div class="page-header position-relative">
		<!-- 		<div class="header-title"> -->
		<!-- 			<h1> -->
		<!-- 				用户管理 <small> <i class="fa fa-angle-right"></i> ${user eq null ? "新增" : "编辑" }用户 -->
		<!-- 				</small> -->
		<!-- 			</h1> -->
		<!-- 		</div> -->
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
	<div class="page-body">
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-header bordered-bottom bordered-palegreen">
						<span class="widget-caption"><i class="fa  fa-table"> ${timezoneCountry eq null ? "新增" : "编辑" }国家时区</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="timezoneCountry_form" class="form-horizontal" action="${pageContext.request.contextPath}/timezoneCountry/${timezoneCountry eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${timezoneCountry.id}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">时区</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="timezone" name="timezone" value="${timezoneCountry.timezone}" placeholder="名称">
									<label class="error" for="timezone" style="display:none;">请填写时区</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">国家</label>
								<div class="col-sm-4">
									<select id="countryId" name="countryId" style="width:300px;" class="multiselect">
										<option value="">请选择</option>
										<c:forEach items="${mccs}" var="item">
											<option value="${item.id}" <c:if test="${timezoneCountry.countryIso == item.iso}">selected="selected"</c:if> >
													${item.country } 
											</option>
										</c:forEach>
									</select>
									<label class="error" for="country" style="display:none;">请选择国家</label>
									<label class="error" for="country" style="display:none;">国家已存在</label>
								</div>
							</div>
							<hr class="wide" />
							<div class="buttons-preview text-align-center">
								<a type="" class="btn btn-primary" href="javascript:history.back();"> <i class="fa fa-mail-reply"></i> 返回
								</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-primary" id="submitInput">
									<i class="fa fa-save"></i> 保存
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".multiselect").select2();            
		});
	
		$("#submitInput").click(function(){
			var timezone = $.trim($("#timezone").val());
			var countryId = $.trim($("#countryId").val());
			var num = 0;
			
			if(timezone == ""){
				num = num - 1;
				$("#timezone").next().show();
			}else{
				$("#timezone").next().hide();
			}
			
			if(countryId == ""){
				num = num - 1;
				$("#countryId").next().show();
				$("#countryId").next().next().hide();
			}else{
				$("#countryId").next().hide();
				$.ajax({
					url: "${pageContext.request.contextPath}/timezoneCountry/doIso",
		            type :"GET",
		            data:{id:$("#id").val(),countryId:countryId},
		            async: false,
		            dataType : "text",
					success: function(date){
						if(date == "true"){
							$("#countryId").next().next().hide();
						}else{
							num = num - 1;
							$("#countryId").next().next().show();
						}
			      	}
		    	});
			}
			
			if(num == 0){
				$("#submitInput").attr("disabled","true");
				$("#timezoneCountry_form").submit();
			}
		});
	</script>
</body>
</html>