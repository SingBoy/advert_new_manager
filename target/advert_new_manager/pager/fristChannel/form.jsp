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
			<li class="active"> ${fristChannel eq null ? "新增" : "编辑" }一级渠道</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${fristChannel eq null ? "新增" : "编辑" }一级渠道</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="fristChannel_form" class="form-horizontal" action="${pageContext.request.contextPath}/fristChannel/${fristChannel eq null ? 'save' : 'update' }" method="post" role="form">
							<input type="hidden" name="id" id="id" value="${fristChannel.id}">
							<input type="hidden" name="voluumTrafficSourceId" id="voluumTrafficSourceId" value="${fristChannel.voluumTrafficSourceId}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">渠道名称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="name" name="name" value="${fristChannel.name}" placeholder="渠道名称">
									<label class="error" for="name" style="display:none;">请填写渠道名称</label>
									<label class="error" for="name" style="display:none;">渠道名称已存在</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">分成比例</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
									<input type="text" class="form-control" id="dividedRate" name="dividedRate" value="${fristChannel.dividedRate}" placeholder="分成比例">
									<label class="error" for="dividedRate" style="display:none;">请填写分成比例</label>
									<label class="error" for="dividedRate" style="display:none;">不能大于1</label>
									</div>
									<div style="float:left;padding-top:5px;" class="col-sm-2">
										<nobr style="font-size:14px;color:red;">(0-1)</nobr>
									</div>
								</div>
							</div>
							<%--<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">订阅量分配比例</label>
								<div style="float:left" class="col-sm-3">
									<a type="button" id="lookOver" class="btn btn-primary" onclick="ModalDialog()"><i class="fa fa-plus"></i> 设置分配比例</a>
								</div>
							</div>--%>

							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">回调地址(<nobr style="font-size:14px;color:red;">http://打头</nobr>)</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="callbackUrl" name="callbackUrl" value="${fristChannel.callbackUrl}" placeholder="回调地址">
									<label class="error" for="callbackUrl" style="display:none;">请填写回调地址</label>
									<label class="error" for="callbackUrl" style="display:none;">回调地址格式不对</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">参数1名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="p1" name="p1" value="${fristChannel.p1}" placeholder="参数1名称">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">参数2名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="p2" name="p2" value="${fristChannel.p2}" placeholder="参数2名称">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">参数3名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="p3" name="p3" value="${fristChannel.p3}" placeholder="参数3名称">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">参数4名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="p4" name="p4" value="${fristChannel.p4}" placeholder="参数4名称">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">参数5名称</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="p5" name="p5" value="${fristChannel.p5}" placeholder="参数5名称">
								</div>
							</div>

							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">负责人</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="leader" name="leader" value="${fristChannel.leader}" placeholder="负责人">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">描述</label>
								<div class="col-sm-4">
									<textarea name="description" id="description" class="form-control" rows="5" placeholder="（限制150字以内）">${fristChannel.description}</textarea>
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


	<!--广告弹框 -->


	<script type="text/javascript">
		/*$("#callbackUrl").change(function(){
			var callbackUrl = $.trim($("#callbackUrl").val());
			
			if(callbackUrl != ''){
				$("#c1Div").show();
				$("#c2Div").show();
				$("#typeDiv").show();
				$("#c3Div").show();
			}else{
				$("#c1Div").hide();
				$("#c2Div").hide();
				$("#typeDiv").hide();
				$("#c3Div").hide();
			}
		});*/
	
	
		$("#submitInput").click(function(){
			var name = $.trim($("#name").val());
			var dividedRate = $.trim($("#dividedRate").val());
			var callbackUrl = $.trim($("#callbackUrl").val());
			var description = $.trim($("#description").val());
			var distribution= $.trim($("#distribution").val());
			
			var c1 = $.trim($("#c1").val());
			var c2 = $.trim($("#c2").val());
			var type = $.trim($("#type").val());
			//var c3 = $.trim($("#c3").val());
			
			var reg = /^[0-9]+.?[0-9]*$/;
			var num = 0;
			
			if(name == ""){
				$("#name").next().show();
				$("#name").next().next().hide();
			}else{
				$("#name").next().hide();
				$.ajax({
					url: "${pageContext.request.contextPath}/fristChannel/doBean",
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
			
			if(dividedRate == ""){
				$("#dividedRate").next().show();
			}else{
				$("#dividedRate").next().hide();
				if(!reg.test(dividedRate) || dividedRate > 1){
					$("#dividedRate").next().next().show();
				}else{
					num = num + 1;
					$("#dividedRate").next().next().hide();
				}
			}

 			var strReg=/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
 			if(callbackUrl == ""){
 				num = num + 1;	
 				$("#callbackUrl").next().next().hide();
			}else{
                if(!strReg.test(callbackUrl)) {
                    $("#callbackUrl").next().next().show();
                }else{
                    num = num + 1;
                    $("#callbackUrl").next().next().hide();
                }
            }
			
 			if(description.length > 150){
				$("#description").next().show();
			}else{
				num = num + 1;
				$("#description").next().hide();
			} 
			if(num == 4){
				$("#submitInput").attr("disabled","true");
				$("#fristChannel_form").submit();
			}
		});



	</script>
</body>
</html>