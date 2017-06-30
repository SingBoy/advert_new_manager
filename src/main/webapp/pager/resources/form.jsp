<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/bootstrap-multiselect/bootstrap-multiselect.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-multiselect/bootstrap-multiselect-collapsible-groups.js"></script>
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
			<li class="active"><a href="${pageContext.request.contextPath}/resources/list">广告管理</a></li>
			<li class="active"> ${resources eq null ? "新增" : "编辑" }广告</li>
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
						<span class="widget-caption"><i class="fa  fa-table"> ${resources eq null ? "新增" : "编辑" }广告</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="resources_form" class="form-horizontal" action="${pageContext.request.contextPath}/resources/${resources eq null ? 'save' : 'update' }" method="post" role="form" enctype="multipart/form-data">
							<input type="hidden" name="id" id="id" value="${resources.id}">
							<input type="hidden" name="imageUrl" id="imageUrl" value="${resources.imageUrl}">
							<input type="hidden" name="keyword" id="keyword" value="${queryBean.keyword}">
							<input type="hidden" name="countryBean" id="countryBean" value="${queryBean.country}">
							<input type="hidden" name="operatorBean" id="operatorBean" value="${queryBean.operator}">
							<input type="hidden" name="adsIdBean" id="adsIdBean" value="${queryBean.adsId}">
							<input type="hidden" name="statusBean" id="statusBean" value="${queryBean.status}">
							<input type="hidden" name="currentPage" id="currentPage" value="${queryBean.currentPage}">
							<input type="hidden" name="pageSize" id="pageSize" value="${queryBean.pageSize}">
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">资源名称</label>
								<div class="col-sm-4">
									<input type="text" disabled="disabled" class="form-control" id="" name="" value="${resources.name}" placeholder="资源名称">
									<label class="error" for="name" style="display:none;">请填写资源名称</label>
									<label class="error" for="name" style="display:none;">资源名称已存在</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name"  class="col-sm-4 control-label no-padding-right">广告主</label>
								<div class="col-sm-4">
									<c:forEach items="${listAdvertisers}" var="item">
												<c:if test="${resources.adsId== item.id}">
													<input type="text" disabled="disabled" class="form-control" id="name" name="name" value="${item.name }">
												</c:if>
									</c:forEach>
									<%--<select id="adsId" name="adsId"  disabled="disabled" style="width:250px;" class="multiselect1">
										<option value="">请选择</option>
										<c:forEach items="${listAdvertisers}" var="item">
											<option value="${item.id }" <c:if test="${resources.adsId== item.id}">selected="selected"</c:if> >
												<c:if test="${item.name != null}">
													${item.name } 
												</c:if>
											</option>
										</c:forEach>
									</select>
									<label class="error" for="adsId" style="display:none;">请选择广告主&nbsp;&nbsp;&nbsp;</label>--%>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">国家</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
										<input type="text" class="form-control" value="${resources.mccGroup}" placeholder="国家" disabled="disabled">
									</div>
									<%--<div style="float:left" class="col-sm-3">
										<a type="button" id="lookOver" class="btn btn-primary" onclick="ModalDialog()"><i class="fa fa-plus"></i> 国家和运营商</a>
									</div>--%>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">资源别名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="nameAlias" name="nameAlias" value="${resources.nameAlias}" placeholder="资源别名">
									<label class="error" for="name" style="display:none;">请填写资源别名</label>
									<%--<label class="error" for="name" style="display:none;">资源别名已存在</label>--%>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">价格</label>
								<div class="col-sm-4">
									<input type="text" disabled="disabled" class="form-control" id="price" name="price" value="${resources.price}" placeholder="价格">
									<label class="error" for="price" style="display:none;">请填写价格</label>
									<label class="error" for="price" style="display:none;">只能正整数和小数</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">活动时间</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-2">
										<input type="text" name="activityStart" id="activityStart" value="<fmt:formatDate value='${resources.activityStart}' pattern='yyyy-MM-dd HH:mm:ss' />" style="height: 32px;" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="开始时间">
										<label class="error" for="activityStart" style="display:none;">请填写开始时间</label>
										<label class="error" for="activityStart" style="display:none;">不能大于结束时间</label>
									</div>
									<div style="float:left" class="col-sm-2">
										<input type="text" name="activityEnd" id="activityEnd" value="<fmt:formatDate value='${resources.activityEnd}' pattern='yyyy-MM-dd HH:mm:ss' />" style="height: 32px;" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="结束时间">
										<label class="error" for="activityEnd" style="display:none;">请填写结束时间</label>
										<label class="error" for="activityEnd" style="display:none;">不能小于当前时间</label>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="name"  class="col-sm-4 control-label no-padding-right">原始链接(<nobr style="font-size:14px;color:red;">http://打头</nobr>)</label>
								<div style = "display:inline;">
									<div style="float:left" class="col-sm-3">
										<input type="text" disabled="disabled" class="form-control" id="targetLink" name="targetLink" value="${resources.targetLink}" placeholder="原始链接">
										<label class="error" for="targetLink" style="display:none;">请填写原始链接</label>
										<label class="error" for="targetLink" style="display:none;">原始链接格式不对</label>
									</div>
									<%--<div style="float:left;padding-top:5px;" class="col-sm-2">
										<label><input type="checkbox" id="isSupportParam" name="isSupportParam" value="0" <c:if test="${resources.isSupportParam== 0}">checked="checked"</c:if>><span class="text">不支持带参数</span></label>
									</div>--%>
								</div>
							</div>
							<%--<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">c1参数名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="c1Key" name="c1Key" value="${resources.c1Key}" placeholder="c1参数名">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">c2参数名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="c2Key" name="c2Key" value="${resources.c2Key}" placeholder="c2参数名">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">type参数名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="typeKey" name="typeKey" value="${resources.typeKey}" placeholder="type参数名">
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">c3参数名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="c3Key" name="c3Key" value="${resources.c3Key}" placeholder="c3参数名">
								</div>
							</div>--%>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">日限量</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="dailyLimit" name="dailyLimit" value="${resources.dailyLimit}" placeholder="日限量">
									<label class="error" for="dailyLimit" style="display:none;">请填写日限量</label>
									<label class="error" for="dailyLimit" style="display:none;">只能正整数</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">是否支持回调</label>
								<div class="col-sm-4">
									<select id="callbackStatus" name="callbackStatus" style="width:250px;" class="multiselect1">
										<option value="1" <c:if test="${resources.callbackStatus== 1}">selected="selected"</c:if>>支持</option>
										<option value="0" <c:if test="${resources.callbackStatus== 0}">selected="selected"</c:if>>不支持</option>
									</select>
									<label class="error" for="callbackStatus" style="display:none;">请选择是否支持回调</label>
								</div>
							</div>
							<%--<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">启用状态</label>
								<div class="col-sm-4">
									<select id="status" name="status" style="width:250px;" class="multiselect1">
										<option value="">请选择</option>
										<option value="1" <c:if test="${resources.status== 1}">selected="selected"</c:if>>开启</option>
										<option value="0" <c:if test="${resources.status== 0}">selected="selected"</c:if>>关闭</option>
									</select>
									<label class="error" for="status" style="display:none;">请选择启用状态</label>
								</div>
							</div>--%>
							<%--<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">上传图片</label>
								<div class="col-sm-4" <c:if test="${fn:length(resources.imageUrl) > 0 }">style="display:none;" </c:if>>
									<input type="file"  name="flie" id="flie" style="width:190px;"/>
									<label class="error" for="flie" style="display:none;">请上传图片</label>
								</div>
								<div class="col-sm-4" <c:if test="${fn:length(resources.imageUrl) == 0 }">style="display:none;"</c:if>>
									 <a class="btn" href="javascript:delImg();" id="imageA"><i style="color:red;" class="fa fa-times"></i>${resources.imageUrl}</a>
								</div>
							</div>--%>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label no-padding-right">描述</label>
								<div class="col-sm-4">
									<textarea name="description" id="description" class="form-control" rows="5" placeholder="（限制150字以内）">${resources.description}</textarea>
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
	
<!--国家和运营商弹框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">  
    <div class="modal-dialog" style="width:90%;" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span  
                        aria-hidden="true">×</span></button>  
                <h4 class="modal-title" id="exampleModalLabel">国家和运营商</h4>
            </div>
             <div class="modal-search">
				<div class="row">
                	<div class="col-lg-6 col-xs-6 col-sm-6">
						<div class="col-lg-6 col-xs-6 col-sm-6">
							<div class="form-group">
								<div class="checkbox">
									<label><input type="checkbox" class="colored-success" name="checkAll"><span class='text'>全选/全不选</span></label>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-xs-6 col-sm-6">
							<div class="form-group">
								<div class="checkbox">
									<label><input type="checkbox" class="colored-success" name="checkOpposite"><span class='text'>反选</span></label>
								</div>
							</div>
						</div>
                	</div>
            	</div>
            </div>
            <div class="modal-body">
            	<c:forEach items="${listMcc}" var="mcc">
            		<div class="row">
						<div class="col-lg-2 col-xs-2 col-sm-2">
								<span class="text" style="color:red;">${mcc.country}</span>
<%-- 							<div class='checkbox'>
								<label><input type="checkbox" class="colored-success" id="operatorDialog" name="operatorDialog">
								<span class="text" style="color:red;">${mcc.country}</span></label>
							</div> --%>
						</div>
					</div>
					<div class="row">
					<c:forEach items="${mcc.listMnc}" var="mnc">
						<div class="col-lg-2 col-xs-2 col-sm-2">
							<div class='checkbox'>
								<c:set var="countryOperator" value="${mcc.country}_${mnc.operator}"></c:set>
								<label><input type="checkbox" class="colored-success" id="operatorDialog" name="operatorDialog" value="${mcc.country}_${mnc.operator}" 
								<c:if test="${fn:contains(resources.mccGroup,countryOperator)}">checked="checked"</c:if>>
								<span class="text">${mnc.operator}</span></label>
							</div>
						</div>
					</c:forEach> 
					</div>
				<hr>
				</c:forEach>
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
                <button type="button" class="btn btn-primary" onclick="saveOperator()">保存</button>  
            </div>  
        </div>  
    </div>  
</div>
<!-- -----   -->

	<script type="text/javascript">	
		$(document).ready(function() {
			$(".multiselect1").select2();
			
		    $('.multiselect2').multiselect({
				selectAllText : "全选",
				nonSelectedText : "请选择运营商组合",
				nSelectedText : " 运营商组合",
				includeSelectAllOption : true,
				enableFiltering : true,
				allSelectedText : '全部运营商组合',
				enableClickableOptGroups: true,//同时取组或者all  
				//enableCollapsibleOptGroups: true,//组可折叠  
				autoScroll:true,
				buttonWidth : '250px',
				maxHeight : 180
			});
		    
		});
		
		
		function delImg(){
			var id = $("#id").val();
			if (confirm("你确认要删除吗？")){
				$.ajax({
					url: "${pageContext.request.contextPath}/resources/deleImg",
		            type :"GET",
		            data:{id:$("#id").val()},
		            async: false,
		            dataType : "text",
					success: function(){
						$("#imageUrl").val("");
						$("#imageA").parent().hide();
						$("#flie").parent().show();
			      	}
		    	});
			}
		}
		
		$("#submitInput").click(function(){
			var description = $.trim($("#description").val());
			var dailyLimit = $.trim($("#dailyLimit").val());
			var num = 0;

			
			var str = /^[0-9]*[1-9][0-9]*$/;
			if(dailyLimit == ""){
				num = num + 1;
			}else{
				$("#dailyLimit").next().hide();
				if(!str.test(dailyLimit)){
					$("#dailyLimit").next().next().show();
				}else{
					num = num + 1;
					$("#dailyLimit").next().next().hide();
				}
			}
			
			if(num ==1){
                $("#submitInput").attr("disabled","true");
                $("#resources_form").submit();
			}


		});
		
		function ModalDialog(){
			$('#myModal').modal('show').css({
                width: '100%',
            });
			$('.modal').css({'overflow-x':'scroll'});
		}
		
		$("input[name='checkAll']").change(function(){
			$("input[name='checkOpposite']").prop("checked",false);
			if($(this).is(':checked')) {
				$("input[name='operatorDialog']").each(function(){
					$(this).prop("checked",true);
				});
				
			} else {
				$("input[name='operatorDialog']").each(function(){
					$(this).prop("checked",false);
				});
			}
		});
		
		$("input[name='checkOpposite']").change(function(){
			$("input[name='checkAll']").prop("checked",false);
			$("input[name='operatorDialog']").each(function(){
				if($(this).is(':checked')){
					$(this).prop("checked",false);
				} else {
					$(this).prop("checked",true);
				}
			});
		});
		
		function saveOperator(){
			var aArray = new Array();
			$('input[name="operatorDialog"]:checked').each(function(){ 
				aArray.push($(this).val()); 
			});
			$('#myModal').modal('hide');
			$("#mccMnc").val(aArray.join(','));
			$("#mccMnc1").val(aArray.join(','));
		}

	</script>
</body>
</html>