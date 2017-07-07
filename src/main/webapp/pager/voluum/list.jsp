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
			<li><i class="fa fa-home"></i>数据查询</li>
			<li class="active"><a href="${pageContext.request.contextPath}/voluumList/list">数据查询</a></li>
		</ul>
	</div>
	<!-- Page Header -->
	<div class="page-header position-relative">
		<div class="header-buttons">
			<a class="sidebar-toggler" href="javascript:void(0);"> <i class="fa fa-arrows-h"></i>
			</a> <a class="refresh" id="refresh-toggler" href="${pageContext.request.contextPath}/voluumList/list"> <i class="glyphicon glyphicon-refresh"></i>
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
						<form action="${pageContext.request.contextPath}/voluumList/list" method="post" class="form-inline" role="form">
							<div class="row">
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword" value="${queryBean.keyword }" placeholder="资源名称">
									</div>
 									<%--<div class="form-group">
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
									</div>--%>
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search"></i> 查询
									</button>
								</div>
							</div>
						</form>
						<br />
						<div class="flip-scroll">
							<table class="table table-hover table-bordered table-striped table-condensed flip-content">
								<thead class="flip-content bordered-palegreen">
									<tr>
										<th class="text-center" style="width:300px;">资源名称</th>
										<th class="text-center" style="width:250px;">广告主名称</th>
										<th class="text-center" style="width:250px;">渠道名称</th>
										<th class="text-center" style="width:150px;">国家</th>
										<th class="text-center" style="width:50px;">价格</th>
										<th class="text-center" style="width:140px;">创建时间</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDataList.pageRecords}" var="item">
										<tr>

											<td class="text-center" style="word-break:break-all;">${item.offerName }</td>
											<td class="text-center" style="word-break:break-all;">${item.affiliateNetworkName }</td>
											<td class="text-center" style="word-break:break-all;">${item.trafficSourceName }</td>
											<td class="text-center" style="word-break:break-all;">${item.country }</td>
											<td class="text-center" style="word-break:break-all;">${item.payout }</td>
											<td class="text-center">
												<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td class="text-center">
												<%--<a href="${pageContext.request.contextPath}/voluum/doUpdate/${item.id}?keyword=${queryBean.keyword}&country=${queryBean.country}&operator=${queryBean.operator}&adsId=${queryBean.adsId}&status=${queryBean.status}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}" class="btn btn-azure btn-xs shiny"><i class="fa fa-edit"></i> 编辑</a>--%>
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
							<c:url var="pageUrl" value="/voluumList/list">
								<c:param name="keyword" value="${queryBean.keyword}" />
								<%--<c:param name="country" value="${queryBean.country}" />--%>
								<%--<c:param name="adsId" value="${queryBean.adsId}" />--%>
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
				window.location = "${pageContext.request.contextPath}/voluumList/delete/"+id +
				"?keyword="+$("#keyword").val()+"&currentPage="+${queryBean.currentPage}+
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
		
	</script>
</body>
</html>