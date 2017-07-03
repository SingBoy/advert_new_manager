<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="page" uri="http://www.ibingo.net.cn/tags/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i>广告资源管理</li>
			<li class="active"><a href="${pageContext.request.contextPath}/resources/list">广告管理</a></li>
		</ul>
	</div>
	<!-- Page Header -->
	<div class="page-header position-relative">
<!-- 		<div class="header-title"> -->
<!-- 			<h1> -->
<!-- 				用户管理 <small> <i class="fa fa-angle-right"></i> 用户列表 -->
<!-- 				</small> -->
<!-- 			</h1> -->
<!-- 		</div> -->
		<!--Header Buttons-->
		<div class="header-buttons">
			<a class="sidebar-toggler" href="javascript:void(0);"> <i class="fa fa-arrows-h"></i>
			</a> <a class="refresh" id="refresh-toggler" href="${pageContext.request.contextPath}/resources/list"> <i class="glyphicon glyphicon-refresh"></i>
			</a> <a class="fullscreen" id="fullscreen-toggler" href="javascript:void(0);"> <i class="glyphicon glyphicon-fullscreen"></i>
			</a>
		</div>
		<!--Header Buttons End-->
	</div>
	<!-- /Page Header -->
	<!-- Page Body -->
	<div class="page-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-header  with-footer">
						<span class="widget-caption"><i class="fa  fa-table">页面列表</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form action="${pageContext.request.contextPath}/resources/list" method="post" class="form-inline" role="form">
							<div class="row">
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword" value="${queryBean.keyword }" placeholder="资源名称">
									</div>
 									<div class="form-group">
										<select id="country" name="country" class="multiselect" style="width: 175px;">
											<option value="">国家</option>
											<c:forEach items="${listMcc}" var="item">
												<option value="${item.country }" <c:if test="${queryBean.country== item.country}">selected="selected"</c:if> >
													<c:if test="${item.country != null}">
														${item.country } 
													</c:if>
												</option>
											</c:forEach> 
										</select>
									</div>
									 <%--<div class="form-group">
										<select id="operator" name="operator" class="multiselect" style="width: 175px;">
											<option value="">运营商</option>
											<c:forEach items="${listMnc}" var="item">
												<option value="${item.operator }" <c:if test="${queryBean.operator== item.operator}">selected="selected"</c:if> >
													<c:if test="${item.operator != null}">
														${item.operator } 
													</c:if>
												</option>
											</c:forEach> 
										</select>
									</div>--%>
									<div class="form-group">
										<select id="adsId" name="adsId" class="multiselect" style="width: 175px;">
											<option value="">合作方</option>
											<c:forEach items="${listAdvertisers}" var="item">
												<option value="${item.id }" <c:if test="${queryBean.adsId== item.id}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach> 
										</select>
									</div>
									<%--<div class="form-group">
										<select id="status" name="status" style="width:175px;" class="multiselect">
											<option value="">启用状态</option>
											<option value="1" <c:if test="${queryBean.status== 1}">selected="selected"</c:if>>开启</option>
											<option value="0" <c:if test="${queryBean.status== 0}">selected="selected"</c:if>>关闭</option>
										</select>
									</div>--%>
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search"></i> 查询
									</button>
									<%--<a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/resources/doSave?keyword=${queryBean.keyword}&country=${queryBean.country}&operator=${queryBean.operator}&adsId=${queryBean.adsId}&status=${queryBean.status}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}"><i class="fa fa-plus"></i> 新增</a>--%>
									<a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/resources/syncVoluumOffer"><i class="fa fa-asterisk"></i> 同步</a>
								</div>
							</div>
						</form>
						<br />
						<div class="flip-scroll">
							<table class="table table-hover table-bordered table-striped table-condensed flip-content">
								<thead class="flip-content bordered-palegreen">
									<tr>
										<%--<th class="text-center" style="width:50px;">ID</th>--%>
										<th class="text-center" style="width:250px;">资源名称</th>
										<th class="text-center" style="width:200px;">广告主</th>
										<th class="text-center" style="width:200px;">资源别名</th>
										<th class="text-center" style="width:100px;">国家</th>
										<th class="text-center" style="width:50px;">价格</th>
										<%--<th class="text-center" style="width:70px;">原始链接</th>
										<th class="text-center" style="width:140px;">活动时间</th>--%>
										<th class="text-center" style="width:50px;">日限量</th>
										<th class="text-center" style="width:150px;">描述</th>
										<th class="text-center" style="width:70px;">是否支持回调</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDataList.pageRecords}" var="item">
										<tr>
											<%--<td class="text-center" style="word-break:break-all;">${item.id }</td>--%>
											<td class="text-center" style="word-break:break-all;">${item.name }</td>
											<td class="text-center" style="word-break:break-all;">${item.adsName }</td>
											<td class="text-center" style="word-break:break-all;">
													<%--${item.nameAlias }--%>
												<input style="width: 100%" id="${item.id}_aliasName" name="" value="${item.nameAlias }" onblur="updateAliasName(${item.id})"/>
											</td>
											<td class="text-center" style="word-break:break-all;">
												<label data-toggle="tooltip" data-placement="bottom" data-original-title="${item.mccGroup }">
													<c:if test="${fn:length(item.mccGroup) > 10}">	
														${fn:substring(item.mccGroup,0,10)} .......
													</c:if>
													<c:if test="${fn:length(item.mccGroup) <= 10}">
														${item.mccGroup}
													</c:if>
												</label>
											</td>

											<td class="text-center" style="word-break:break-all;">${item.price }</td>
											<%--<td class="text-center" style="word-break:break-all;">
												<label><button type="button" class="btn" onclick="buttonLink('${item.targetLink}');">查看</button></label>
											</td>
											<td class="text-center">
												<fmt:formatDate value="${item.activityStart}" pattern="yyyy-MM-dd HH:mm:ss" />-
												<fmt:formatDate value="${item.activityEnd}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>--%>
											<c:choose>
												<c:when test="${item.dailyLimit ne null}">
													<td class="text-center" style="word-break:break-all;">${item.dailyLimit}</td>
												</c:when>	
												<c:otherwise>
													<td class="text-center">-</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${fn:length(item.description) ne 0}">
													<td class="text-center" style="word-break:break-all;">${item.description}</td>
												</c:when>
												<c:otherwise>
													<td class="text-center">-</td>
												</c:otherwise>
											</c:choose>
											<td class="text-center" style="word-break:break-all;">
												<%--<c:if test="${item.callbackStatus== 1}">
													 <font color="green">是</font>
												</c:if>
												<c:if test="${item.callbackStatus== 0}">
													<font color="red">否</font>
												</c:if>--%>
													<select id="${item.id}_callbackStatus" name="callbackStatus" style="width:100%" class="multiselect1" onblur="updateCallStatus(${item.id})">
														<option value="1" <c:if test="${item.callbackStatus== 1}">selected="selected"</c:if>>是</option>
														<option value="0" <c:if test="${item.callbackStatus== 0}">selected="selected"</c:if>>否</option>
													</select>
											</td>
											<td class="text-center">
												<%--<a href="${pageContext.request.contextPath}/resources/doUpdate/${item.id}?keyword=${queryBean.keyword}&country=${queryBean.country}&operator=${queryBean.operator}&adsId=${queryBean.adsId}&status=${queryBean.status}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}" class="btn btn-azure btn-xs shiny"><i class="fa fa-edit"></i> 编辑</a>--%>
												<a onclick="confirmImg(${item.id})" class="btn btn-darkorange btn-xs shiny"><i class="fa fa-trash-o"></i> 删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<hr class="wide" />
						<div class="footer">
							<!-- 分页  -->
							<c:url var="pageUrl" value="/resources/list">
								<c:param name="keyword" value="${queryBean.keyword}" />
								<c:param name="country" value="${queryBean.country}" />
								<c:param name="operator" value="${queryBean.operator}" />
								<c:param name="adsId" value="${queryBean.adsId}" />
								<c:param name="status" value="${queryBean.status}" />
							</c:url>
							<page:pagerNav modelRef="pageDataList" url="${pageUrl}" type="beyond" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /Page Body -->
	<script type="text/javascript">
		$(document).ready(function() {
			$(".multiselect").select2();
		});
		
		$("#country").change(function(){
			var str = "<option value=\"\">运营商</option>";
			$.ajax({
				url: "${pageContext.request.contextPath}/mnc/listMnc",
	            type :"GET",
	            data:{country:$("#country").val()},
	            dataType : "json",
				success: function(resources){
					$.each(resources, function(index, resource){
						str += "<option value=\""+resource['operator']+"\">"+resource['operator']+"</option>";
					});
					$("#operator").html(str);
		      	}
	    	});
		});
		
		function confirmImg(id) {
			if (confirm("你确认要删除吗？")){
				window.location = "${pageContext.request.contextPath}/resources/delete/"+id +
				"?keyword="+$("#keyword").val()+"&country="+$("#country").val()+
				"&adsId="+$("#adsId").val()+"&currentPage="+${queryBean.currentPage}+
				"&pageSize="+${queryBean.pageSize};
			}
		}
		
		function buttonInput(id){
			bootbox.dialog({
		    	message: "http://u.nicegame.me/api/subscribe?c1=9999&c2=9999&type=9999&resourcesId="+id,
		        title: "推广地址示例",
		        className: "",
		    });
		}
		
		function buttonLink(targetLink){
			bootbox.dialog({
		    	message: targetLink,
		        title: "原始链接",
		        className: "",
		    });
		}
		function updateAliasName(id) {
		    var aliasName = $("#"+id+"_aliasName").val();
			if(aliasName !=null && aliasName !=""){
                $.ajax({
                    url: "${pageContext.request.contextPath}/resources/updateAliasName",
                    type :"GET",
                    data:{id:id,aliasName:aliasName},
                    dataType : "json",
                    success: function(resources){

                    }
                });
			}
        }
        function updateCallStatus(id) {
            var callStatus = $("#"+id+"_callbackStatus").val();
            if(callStatus !=null && callStatus !=""){
                $.ajax({
                    url: "${pageContext.request.contextPath}/resources/updateCallbackStatus",
                    type :"GET",
                    data:{id:id,callbackStatus:callStatus},
                    dataType : "json",
                    success: function(resources){

                    }
                });
            }
        }
		
	</script>
</body>
</html>