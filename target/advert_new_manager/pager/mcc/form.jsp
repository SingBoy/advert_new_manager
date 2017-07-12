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
			<li><a href="${pageContext.request.contextPath}/mnc/list">国家运营商</a></li>
			<li class="active"> ${mcc eq null ? "新增" : "编辑" }国家运营商</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${mcc eq null ? "新增" : "编辑" }国家运营商</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="mcc_form" class="form-horizontal" action="${pageContext.request.contextPath}/mcc/${mcc eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${mcc.id}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<div class="form-group">
								<label for="country" class="col-sm-2 control-label no-padding-right">国家</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control"  id="country" name="country" value="${mcc.country}" placeholder="名称">
										<label class="error" for="country" style="display:none;">请填写国家名称</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" style="float:left" id="countryCode" name="countryCode" value="${mcc.code}" placeholder="MCC代码">
										<label class="error" for="countryCode" style="display:none;">请填写MCC代码</label>
										<label class="error" for="countryCode" style="display:none;">MCC代码长度为3</label>
										<label class="error" for="countryCode" style="display:none;">MCC代码已存在</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control"  id="iso" name="iso" value="${mcc.iso}" placeholder="国家代码">
										<label class="error" for="country" style="display:none;">请填写国家代码</label>
										<label class="error" for="country" style="display:none;">国家代码长度为2</label>
									</div>
								</div>
							</div>
							<c:if test="${listMnc eq null}">
							<div class="form-group">
								<label for="operator" class="col-sm-2 control-label no-padding-right">运营商</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control"  id="operator" name="operator" value="" placeholder="名称">
										<label class="error" for="operator" style="display:none;">请填写运营商名称</label>
										<label class="error" for="operator" style="display:none;">运营商名称不能相同</label>
										<label class="error" for="operator" style="display:none;">运营商名称已存在</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" style="float:left" id="operatorCode" name="operatorCode" value="" placeholder="MNC代码">
										<label class="error" for="operatorCode" style="display:none;">请填写MNC代码</label>
										<label class="error" for="countryCode" style="display:none;">MNC代码长度为3</label>
										<label class="error" for="operatorCode" style="display:none;">MNC代码不能相同</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" style="float:left" id="description" name="description" value="${item.description}" placeholder="描述">
									</div>
									<div class="rows" style="float:left;padding-top:5px;">
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="addMnc(this);">追加</a>
										&nbsp;
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="delMnc(this);">删除</a>
									</div>
								</div>
							</div>
							</c:if>
							<c:if test="${listMnc ne null}">
							<c:forEach items="${listMnc}" var="item" varStatus="status">
							<div class="form-group">
								<label for="operator" class="col-sm-2 control-label no-padding-right"><c:if test="${status.index eq 0}">MNC</c:if></label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control"  id="operator" name="operator" value="${item.operator}" placeholder="名称">
										<label class="error" for="operator" style="display:none;">请填写MNC名称</label>
										<label class="error" for="operator" style="display:none;">MNC名称不能相同</label>
										<label class="error" for="operator" style="display:none;">MNC名称已存在</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" style="float:left" id="operatorCode" name="operatorCode" value="${item.code}" placeholder="代码">
										<label class="error" for="operatorCode" style="display:none;">请填写MNC代码</label>
										<label class="error" for="countryCode" style="display:none;">MCC代码长度为3</label>
										<label class="error" for="operatorCode" style="display:none;">MNC代码不能相同</label>
									</div>
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" style="float:left" id="description" name="description" value="${item.description}" placeholder="描述">
									</div>
									<div class="rows" style="float:left;padding-top:5px;">
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="addMnc(this);">追加</a>
										&nbsp;
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="delMnc(this);">删除</a>
									</div>
								</div>
							</div>
							</c:forEach>
							</c:if>
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
			
		});
		
		function addMnc(mnc){
			var str = "<div class=\"form-group\"><label for=\"operator\""+ 
			"class=\"col-sm-2 control-label no-padding-right\"></label><div style = \"display:inline;\">"+
			"<div style=\"float:left\" class=\"col-sm-3\">"+
			"<input type=\"text\" class=\"form-control\"  id=\"operator\" name=\"operator\" value=\"\" placeholder=\"名称\">"+
			"<label class=\"error\" for=\"operator\" style=\"display:none;\">请填写MNC名称</label>"+
			"<label class=\"error\" for=\"operator\" style=\"display:none;\">MNC名称不能相同</label>"+
			"<label class=\"error\" for=\"operator\" style=\"display:none;\">MNC名称已存在</label></div>"+
			"<div style=\"float:left\" class=\"col-sm-3\">"+
			"<input type=\"text\" class=\"form-control\" style=\"float:left\" id=\"operatorCode\" name=\"operatorCode\" value=\"\" placeholder=\"代码\">"+
			"<label class=\"error\" for=\"operatorCode\" style=\"display:none;\">请填写MNC代码</label>"+
			"<label class=\"error\" for=\"operatorCode\" style=\"display:none;\">MNC代码不能相同</label>"+
			"<label class=\"error\" for=\"operatorCode\" style=\"display:none;\">MNC代码已存在</label></div>"+
			"<div style=\"float:left\" class=\"col-sm-3\">"+
			"<input type=\"text\" class=\"form-control\" style=\"float:left\" id=\"description\" name=\"description\" "+ 
			"value=\"\" placeholder=\"描述\"></div>"+
			"<div class=\"rows\" style=\"float:left;padding-top:5px;\">"+
			"<a href=\"javascript:void(0);\" style=\"color:red;text-decoration:underline;\" onclick=\"addMnc(this);\">追加</a>"+
			"&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" style=\"color:red;text-decoration:underline;\" onclick=\"delMnc(this);\">删除</a>"+
			"</div></div></div>";
			$(mnc).parent().parent().parent().after(str);
		}
	
		function delMnc(mnc){
			var num = $("input[id='operator']").length;
			if(num > 1){
				$(mnc).parent().parent().parent().remove();
			}
		}
		
		$("#submitInput").click(function(){
			var country = $.trim($("#country").val());
			var countryCode = $.trim($("#countryCode").val());
			var iso = $.trim($("#iso").val());
			var num = 0;
			
			if(country == ""){
				num = num - 1;
				$("#country").next().show();
			}else{
				$("#country").next().hide();
/* 				$.ajax({
					url: "${pageContext.request.contextPath}/mcc/doBean",
	            	type :"GET",
	            	data:{id:$("#id").val(),country:country},
	            	async: false,
	            	dataType : "text",
					success: function(date){
						if(date == "true"){
							$("#country").next().next().hide();
						}else{
							$("#country").next().next().show();
							num = num - 1;
						}
		      		}
				}); */
			}

			if(countryCode == ""){
				num = num - 1;
				$("#countryCode").next().show();
			}else{
				$("#countryCode").next().hide();
				if(countryCode.length > 3){
					num = num - 1;
					$("#countryCode").next().next().show();
				}else{
					$("#countryCode").next().next().hide();
 					$.ajax({
						url: "${pageContext.request.contextPath}/mcc/doBean",
		            	type :"GET",
		            	data:{id:$("#id").val(),code:countryCode},
		            	async: false,
		            	dataType : "text",
						success: function(date){
							if(date == "true"){
								$("#countryCode").next().next().next().hide();
							}else{
								$("#countryCode").next().next().next().show();
								num = num - 1;
							}
			      		}
					});
				}

			}
			
			if(iso == ""){
				num = num - 1;
				$("#iso").next().show();
				$("#iso").next().next().hide();
			}else{
				$("#iso").next().hide();
				if(iso.length > 2){
					num = num - 1;
					$("#iso").next().next().show();
				}else{
					$("#iso").next().next().hide();
				}
			}
			
			var operators = "";
			$("input[id='operator']").each(function(){
				var operator = $(this).val();
				if(operator != ""){
					operators = operators + operator + ",";
				}
		    });
			
			var operatorCodes = "";
			$("input[id='operatorCode']").each(function(){
				var operatorCode = $(this).val();
				if(operatorCode != ""){
					operatorCodes = operatorCodes + operatorCode + ",";
				}
		    });
			
 			$("input[id='operator']").each(function(){
				var operator = $(this).val();
				if(operator == "") {
					num = num - 1;
					$(this).next().show();
		 		}else{
		 			$(this).next().hide();
/* 		 			var newOperators = operators.replace(operator,"");
		 			if(newOperators.indexOf(operator) > -1){
		 				num = num - 1;
		 				$(this).next().next().show();
		 			}else{
		 				$(this).next().next().hide();
		 			} */
		 		}
		    }); 

			$("input[id='operatorCode']").each(function(){
				var operatorCode = $(this).val();
				if(operatorCode == "") {
					num = num - 1;
					$(this).next().show();
		 		}else{
		 			$(this).next().hide();
		 			if(operatorCode.length > 3){
		 				num = num - 1;
		 				$(this).next().next().show();
		 			}else{
		 				$(this).next().next().hide();
			 			var newOperatorCodes = operatorCodes.replace(operatorCode+",","");
			 			if(newOperatorCodes.indexOf(operatorCode+",") > -1){
			 				num = num - 1;
			 				$(this).next().next().next().show();
			 			}else{
			 				$(this).next().next().next().hide();
			 			}
		 			}
		 		}
		    });
			
			if(num == 0){
				$(submitInput).attr("disabled","true");
				$("#mcc_form").submit();
			}
		});
	</script>
</body>
</html>