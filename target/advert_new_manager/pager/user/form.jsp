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
			<li><i class="fa fa-home"></i> 账户管理</li>
			<li><a href="${pageContext.request.contextPath}/user/list">账户管理</a></li>
			<li class="active"> ${user eq null ? "新增" : "编辑" }账户</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${user eq null ? "新增" : "编辑" }账户</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="user_form" class="form-horizontal" action="${pageContext.request.contextPath}/user/${user eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${user.id}">
							<input type="hidden" name="userRole2" id="userRole2" value="${user.userRole}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<c:if test="${sessionScope.current_user.userRole != 1}">
								<input type="hidden" name="userRole" id="userRole" value="4">
							</c:if>
							<div class="form-group">
								<label for="username" class="col-sm-4 control-label no-padding-right">账户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="username" name="username" value="${user.username}" placeholder="账户名">
									<label class="error" for="username" style="display:none;">请填写账户名</label>
									<label class="error" for="username" style="display:none;">账户名已存在</label>
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-4 control-label no-padding-right">密码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="password" name="password" value="${user.password}" placeholder="密码">
									<label class="error" for="password" style="display:none;">请填写密码</label>
								</div>
							</div>
							<div class="form-group">
								<label for="nickname" class="col-sm-4 control-label no-padding-right">昵称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" placeholder="昵称">
								</div>
							</div>
							<div class="form-group">
								<label for="userRole" class="col-sm-4 control-label no-padding-right">角色名称</label>
								<div style = "display:inline;">
								<c:if test="${sessionScope.current_user.userRole == 1}">
									<div style="float:left" class="col-sm-2">
										<select id="userRole" name="userRole" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
											<option value="1" <c:if test="${user.userRole== 1}">selected="selected"</c:if>>超级管理员</option>
											<option value="2" <c:if test="${user.userRole== 2}">selected="selected"</c:if>>广告主</option>
											<option value="3" <c:if test="${user.userRole== 3}">selected="selected"</c:if>>渠道账号</option>
										</select>
										<label class="error" for="userRole" style="display:none;">请选择角色名称</label>
									</div>
									</c:if>
									<div style="float:left" class="col-sm-2">
										<c:if test="${user.userRole== 2}">
										<select id="userRoleId" name="userRoleId" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
											<c:forEach items="${listAdvertisers}" var="item">
												<option value="${item.id }" <c:if test="${user.userRoleId== item.id}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach>
										</select>
										</c:if>
										<c:if test="${user.userRole== 3 && sessionScope.current_user.userRole == 3}">
										<select id="userRoleId" name="userRoleId" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
											<c:forEach items="${listTwoChannel}" var="item">
												<option value="${item.id }" <c:if test="${user.userRoleId== item.id}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach>
										</select>
										</c:if>
										<c:if test="${user.userRole== 3 && sessionScope.current_user.userRole != 3}">
										<select id="userRoleId" name="userRoleId" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
											<c:forEach items="${listFristChannel}" var="item">
												<option value="${item.id }" <c:if test="${user.userRoleId== item.id}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach>
										</select>
										</c:if>
										<c:if test="${user == null && sessionScope.current_user.userRole != 3}">
										<select id="userRoleId" name="userRoleId" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
										</select>
										</c:if>
										<c:if test="${user == null && sessionScope.current_user.userRole == 3}">
										<select id="userRoleId" name="userRoleId" style="width:170px;" class="multiselect">
											<option value="">请选择</option>
											<c:forEach items="${listTwoChannel}" var="item">
												<option value="${item.id }" <c:if test="${user.userRoleId== item.id}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach>
										</select>
										</c:if>
										<label class="error" for="userRoleId" style="display:none;">请选择数据</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-4 control-label no-padding-right">email</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="email">
								</div>
							</div>
							<div class="form-group">
								<label for="phone" class="col-sm-4 control-label no-padding-right">手机号</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" placeholder="手机号">
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-4 control-label no-padding-right">描 述</label>
								<div class="col-sm-4">
									<textarea name="description" id="description" class="form-control" rows="5" placeholder="（限制150字以内）">${user.description}</textarea>
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
		$(document).ready(function() {
			$(".multiselect").select2();
			
			var userRole = $("#userRole2").val();
			if(userRole == 1){
				$("#userRole").parent().next().hide();
			}
		});
	
		$("#userRole").change(function(){
			var userRole = $("#userRole").val();
			if(userRole == "1"){
				$("#userRole").parent().next().hide();
			}else if(userRole == "2"){
				$("#userRole").parent().next().show();
				var str = "<option value=\"\">请选择</option>";
				$.ajax({
					url: "${pageContext.request.contextPath}/advertisers/listAdvertisers",
		            type :"GET",
		            dataType : "json",
					success: function(resources){
						$.each(resources, function(index, resource){
							str += "<option value=\""+resource['id']+"\">"+resource['name']+"</option>";
						});
						//str += "</select>";
						$("#userRoleId").html(str);
			      	}
		    	});
			}else if(userRole == "3"){
				$("#userRole").parent().next().show();
				var str = "<option value=\"\">请选择</option>";
				$.ajax({
					url: "${pageContext.request.contextPath}/fristChannel/listFristChannel",
		            type :"GET",
		            dataType : "json",
					success: function(resources){
						$.each(resources, function(index, resource){
							str += "<option value=\""+resource['id']+"\">"+resource['name']+"</option>";
						});
						//str += "</select>";
						$("#userRoleId").html(str);
			      	}
		    	});
			}
		});
		
		
		$("#submitInput").click(function(){
			var username = $.trim($("#username").val());
			var password = $.trim($("#password").val());
			var userRole = $.trim($("#userRole").val());
			var userRoleId = $.trim($("#userRoleId").val());
			var description = $.trim($("#description").val());
			var num = 0;
			
			if(username == ""){
				$("#username").next().show();
				$("#username").next().next().hide();
			}else{
				$("#username").next().hide();
				$.ajax({
					url: "${pageContext.request.contextPath}/user/doUsername",
		            type :"GET",
		            data:{id:$("#id").val(),username:$("#username").val()},
		            async: false,
		            dataType : "text",
					success: function(date){
						if(date == "true"){
							$("#username").next().next().hide();
							num = num + 1;
						}else{
							$("#username").next().next().show();
						}
			      	}
		    	});
			}
			
			if(password == ""){
				$("#password").next().show();
			}else{
				num = num + 1;
				$("#password").next().hide();
			}
			
			if(userRole == ""){
				$("#userRole").next().show();
			}else{
				num = num + 1;
				$("#userRole").next().hide();
			}
			
			if(userRoleId == "" && userRole != 1){
				$("#userRoleId").next().show();
			}else{
				num = num + 1;
				$("#userRoleId").next().hide();
			}
			
 			if(description.length > 150){
				$("#description").next().show();
			}else{
				num = num + 1;
				$("#description").next().hide();
			} 
			
			if(num == 5){
				$("#submitInput").attr("disabled","true");
				$("#user_form").submit();
			}
		});
	</script>
</body>
</html>