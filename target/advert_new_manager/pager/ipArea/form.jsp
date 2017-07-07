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
			<li><a href="${pageContext.request.contextPath}/ipArea/list">IP段管理</a></li>
			<li class="active"> ${ipArea eq null ? "新增" : "编辑" }IP段</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${ipArea eq null ? "新增" : "编辑" }IP段</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="ipArea_form" class="form-horizontal" action="${pageContext.request.contextPath}/ipArea/${ipArea eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${ipArea.id}">
							<input type="hidden" name="tempOperator" id="tempOperator" value="${ipArea.operator}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<input type="hidden" name="countryBean" id="countryBean" value="${queryBean.country}">
							<input type="hidden" name="operatorBean" id="operatorBean" value="${queryBean.operator}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">国家</label>
								<div class="col-sm-4">
									<select id="country" name="country" style="width:300px;" class="multiselect1">
										<option value="">请选择</option>
										<c:forEach items="${mccs}" var="item">
											<option value="${item.id}" <c:if test="${ipArea.country== item.country}">selected="selected"</c:if> >
													${item.country } 
											</option>
										</c:forEach>
									</select>
									<label class="error" for="country" style="display:none;">请填写国家&nbsp;&nbsp;&nbsp;</label>
								</div>
							</div>
							<c:if test="${ipArea eq null}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">运营商</label>
								<div class="col-sm-4">
									<select id="operator" name="operator" style="width:300px;" class="multiselect1">
										<option value="">请选择</option>
									</select>
									<label class="error" for="operator" style="display:none;">请填写运营商&nbsp;&nbsp;&nbsp;</label>
								</div>
							</div>
							</c:if>
							<c:if test="${ipArea ne null}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">运营商</label>
								<div class="col-sm-4">
									<select id="operator" name="operator" style="width:300px;" class="multiselect1">
										<option value="">请选择</option>
										<c:forEach items="${mncs}" var="item">
											<option value="${item}" <c:if test="${ipArea.operator== item}">selected="selected"</c:if> >
													${item } 
											</option>
										</c:forEach>
									</select>
									<label class="error" for="operator" style="display:none;">请填写运营商&nbsp;&nbsp;&nbsp;</label>
								</div>
							</div>
							</c:if>
<!-- 							<div class="form-group"> -->
<!-- 								<label for="name" class="col-sm-4 control-label no-padding-right">国家</label> -->
<!-- 								<div class="col-sm-4"> -->
<%-- 									<input type="text" class="form-control" id="country" name="country" value="${ipArea.country}" placeholder="国家"> --%>
<!-- 									<label class="error" for="name" style="display:none;">请填写国家</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label for="name" class="col-sm-4 control-label no-padding-right">运营商</label> -->
<!-- 								<div class="col-sm-4"> -->
<%-- 									<input type="text" class="form-control" id="operator" name="operator" value="${ipArea.operator}" placeholder="运营商"> --%>
<!-- 									<label class="error" for="name" style="display:none;">请填写运营商</label> -->
<!-- 									<label class="error" for="name" style="display:none;">运营商已存在</label> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">IP段</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-2">
										<input type="text" class="form-control"  id="start" name="start" value="${ipArea.start}" placeholder="开始ip">
										<label class="error" for="start" style="display:none;">请填写开始ip</label>
									</div>
									<div style="float:left;padding-top:5px;font-weight:bold;">-</div>
									<div style="float:left" class="col-sm-2">
										<input type="text" class="form-control" style="float:left" id="end" name="end" value="${ipArea.end}" placeholder="结束ip">
										<label class="error" for="end" style="display:none;">请填写结束ip</label>
										<label class="error" for="end" style="display:none;">开始和结束IP不能相同</label>
										<label class="error" for="end" style="display:none;">开始和结束IP已存在</label>
									</div>
									<div class="rows" style="float:left;padding-top:5px;">
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="addIp(this);">追加</a>
										<a href="javascript:void(0);" style="color:red;text-decoration:underline;" onclick="delIp(this);">删除</a>
									</div>
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
			$("#country").select2();
			$("#operator").select2();
			if($("#country").val() != null && $("#country").val() != ''){
				$.getJSON(
						"${pageContext.request.contextPath}/mnc/listMncName/" + $("#country").val(),
						function(result){
							var temp_html = "";
							temp_html+="<option value=\"\">请选择</option>";
							$.each(result,function(i,name){
								if ($("#tempOperator").val() == name){
									temp_html+="<option value='"+name+"' selected=selected>"+name+"</option>";
								} else {
									temp_html+="<option value='"+name+"' >"+name+"</option>";
								}
			                });
							if ($("#tempOperator") == null) {
								$("#operator").html(temp_html)
							} else {
								$("#operator").html(temp_html).select2();
							}
						}
					);
			}
		});
		
		$("#country").change(function(){
			$("#operator").html("");
			var temp_html = "";
			$.getJSON(
				"${pageContext.request.contextPath}/mnc/listMncName/" + $("#country").val(),
				function(result){
					temp_html+="<option></option>";
					$.each(result,function(i,name){
						temp_html+="<option value='"+name+"'>"+name+"</option>";
	                });
					$("#operator").html(temp_html).select2();
				}
			);
		});
		
		function addIp(ip){
			var str = "<div class=\"form-group\"><label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">IP段</label>"+
			"<div style = \"display:inline;\"><div style=\"float:left\" class=\"col-sm-2\">"+
			"<input type=\"text\" class=\"form-control\"  id=\"start\" name=\"start\" value=\"\" placeholder=\"开始ip\">"+
			"<label class=\"error\" for=\"name\" style=\"display:none;\">请填写开始ip</label></div>"+
			"<div style=\"float:left;padding-top:5px;font-weight:bold;\">-</div><div style=\"float:left\" class=\"col-sm-2\">"+
			"<input type=\"text\" class=\"form-control\" style=\"float:left\" id=\"end\" name=\"end\" value=\"\" placeholder=\"结束ip\">"+
			"<label class=\"error\" for=\"end\" style=\"display:none;\">请填写结束ip</label>"+
			"<label class=\"error\" for=\"end\" style=\"display:none;\">开始和结束IP不能相同</label>"+
			"<label class=\"error\" for=\"end\" style=\"display:none;\">开始和结束IP已存在</label></div>"+
			"<div class=\"rows\" style=\"float:left;padding-top:5px;\">"+
			"<a href=\"javascript:void(0);\" style=\"color:red;text-decoration:underline;\" onclick=\"addIp(this);\">追加</a>&nbsp;"+
			"<a href=\"javascript:void(0);\" style=\"color:red;text-decoration:underline;\" onclick=\"delIp(this);\">删除</a></div></div></div>"
			$(ip).parent().parent().parent().after(str);
		}
		
		function delIp(ip){
			var num = $("input[id='start']").length;
			if(num > 1){
				$(ip).parent().parent().parent().remove();
			}
		}
		
		$("#submitInput").click(function(){
			var country = $.trim($("#country").val());
			var operator = $.trim($("#operator").val());
			var start = $.trim($("#start").val());
			var end = $.trim($("#end").val());
			var num = 0;
			
			if(country == ""){
				num = num - 1;
				$("#country").next().show();
			}else{
				$("#country").next().hide();
			}
			
			if(operator == ""){
				num = num - 1;
				$("#operator").next().show();
				$("#operator").next().next().hide();
			}else{
				$("#operator").next().hide();
/* 				if(country != ""){
					$.ajax({
						url: "${pageContext.request.contextPath}/ipArea/doBean",
			            type :"GET",
			            data:{id:$("#id").val(),country:country,operator:operator},
			            async: false,
			            dataType : "text",
						success: function(date){
							if(date == "true"){
								$("#operator").next().next().hide();
								num = num + 1;
							}else{
								$("#operator").next().next().show();
							}
				      	}
			    	});
				} */
			}
			
			$("input[id='start']").each(function(){
				var start = $(this).val();
				if(start == "") {
					num = num - 1;
					$(this).next().show();
					$(this).parent().next().next().children().eq(1).hide();
					$(this).parent().next().next().children().eq(2).hide();
					$(this).parent().next().next().children().eq(3).hide();
		 		}else{
		 			$(this).next().hide();
		 		}
		    });
			
			var startEnds = "";
			$("input[id='end']").each(function(){
				var start = $(this).parent().prev().prev().children().eq(0).val();
				var end = $(this).val();
				if(start != "" && end != ""){
					startEnds = startEnds + start +"-"+end + ",";
				}
		    });
			
			$("input[id='end']").each(function(){			
				var end = $(this).val();
				if(end == "") {
					num = num - 1;
					$(this).next().show();
		 			$(this).next().next().hide();
		 			$(this).next().next().next().hide();
		 		}else{
		 			$(this).next().hide();
		 			$(this).next().next().hide();
		 			$(this).next().next().next().hide();
		 			var start = $(this).parent().prev().prev().children().eq(0).val();
		 			if(start != ""){
		 				var str = start +"-"+end + ",";
			 			var newStartEnds = startEnds.replace(str,"");
			 			if(newStartEnds.indexOf(start +"-"+end) > -1){
			 				num = num - 1;
			 				$(this).next().next().show();
			 			}else{
			 				$(this).next().next().hide();
			 				if(country != "" && operator != ""){
			 					var isOk = "";
								$.ajax({
									url: "${pageContext.request.contextPath}/ipArea/doBean",
						            type :"GET",
						            data:{id:$("#id").val(),country:country,operator:operator,start:start,end:end},
						            async: false,
						            dataType : "text",
									success: function(date){
										isOk = date;
							      	}
						    	});
								if(isOk == "true"){
									$(this).next().next().next().hide();
								}else{
									num = num - 1;
									$(this).next().next().next().show();
								}
			 				}
			 			}
		 			}
		 		}
		    });
			
			if(num == 0){
				$("#submitInput").attr("disabled","true");
				$("#ipArea_form").submit();
			}
		});
	</script>
</body>
</html>