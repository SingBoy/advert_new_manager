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
			<li><a href="${pageContext.request.contextPath}/advertisers/list">广告主管理</a></li>
			<li class="active"> ${advertisers eq null ? "新增" : "编辑" }广告主</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${advertisers eq null ? "新增" : "编辑" }广告主</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="advertisers_form" class="form-horizontal" action="${pageContext.request.contextPath}/advertisers/${advertisers eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${advertisers.id}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">名称</label>
								<div class="col-sm-4">
									<input type="text" disabled="disabled" class="form-control" id="name" name="name" value="${advertisers.name}" placeholder="名称">
									<label class="error" for="name" style="display:none;">请填写名称</label>
									<label class="error" for="name" style="display:none;">名称已存在</label>
								</div>
							</div>
<%-- 							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">代码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="code" name="code" value="${advertisers.code}" placeholder="代码">
									<label class="error" for="code" style="display:none;">请填写代码</label>
									<label class="error" for="code" style="display:none;">代码已存在</label>
								</div>
							</div> --%>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">描述</label>
								<div class="col-sm-4">
									<textarea name="description" id="description" class="form-control" rows="5" placeholder="（限制150字以内）">${advertisers.description}</textarea>
									<label class="error" for="description" style="display:none;">限制150字以内</label>
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
		$("#submitInput").click(function(){
			var name = $.trim($("#name").val());
			var code = $.trim($("#code").val());
			var description = $.trim($("#description").val());
			var num = 0;
			
			if(name == ""){
				$("#name").next().show();
				$("#name").next().next().hide();
			}else{
				$("#name").next().hide();
				$.ajax({
					url: "${pageContext.request.contextPath}/advertisers/doBean",
		            type :"GET",
		            data:{id:$("#id").val(),name:$("#name").val()},
		            async: false,
		            dataType : "text",
					success: function(date){
						if(date == "true"){
							$("#name").next().next().hide();
							num = num + 1;
						}else{
							$("#name").next().next().show();
						}
			      	}
		    	});
			}
			
/* 			if(code == ""){
				$("#code").next().show();
				$("#code").next().next().hide();
			}else{
				$("#code").next().hide();
				$.ajax({
					url: "${pageContext.request.contextPath}/advertisers/doBean",
		            type :"GET",
		            data:{id:$("#id").val(),code:$("#code").val()},
		            async: false,
		            dataType : "text",
					success: function(date){
						if(date == "true"){
							$("#code").next().next().hide();
							num = num + 1;
						}else{
							$("#code").next().next().show();
						}
			      	}
		    	});
			} */
			
			if(description.length > 150){
				$("#description").next().show();
			}else{
				num = num + 1;
				$("#description").next().hide();
			}
			
			if(num == 2){
				$("#submitInput").attr("disabled","true");
				$("#advertisers_form").submit();
			}
		});
	</script>
</body>
</html>