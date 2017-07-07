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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/data-tables/DT_bootstrap.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/data-tables/DT_bootstrap.js"></script>
	<script type="text/javascript">
		$(function() { 
			//合并单元格
			$.fn.mergeCell = function(options) { 
				return this.each(function() { 
					var cols = options.cols; 
					for ( var i = cols.length - 1; cols[i] != undefined; i--) { 
						mergeCell($(this), cols[i]); 
					} 
					//dispose($(this)); 
				}); 
			};
		  
			$('#mccTable').mergeCell({
				cols: [0,1,2,3,7] 
			});
		});
		//合并单元格
		function mergeCell($table, colIndex) { 
			$table.data('col-content', ''); // 存放单元格内容 
			$table.data('col-rowspan', 1); // 存放计算的rowspan值 默认为1 
			$table.data('col-td', $()); // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的), 默认一个"空"的jquery对象 
			$table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数, 用于最后一行做特殊处理时进行判断之用 
			// 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan 
			$('tbody tr', $table).each(function(index) { 
				// td:eq中的colIndex即列索引 
				var $td = $('td:eq(' + colIndex + ')', this); 
				// 取出单元格的当前内容 
				var currentContent = $td.html(); 
				// 第一次时走此分支 
				if ($table.data('col-content') == '') { 
					$table.data('col-content', currentContent); 
					$table.data('col-td', $td); 
				} else { 
						// 上一行与当前行内容相同 
					if ($table.data('col-content') == currentContent) { 
						// 上一行与当前行内容相同则col-rowspan累加, 保存新值 
						var rowspan = $table.data('col-rowspan') + 1; 
						$table.data('col-rowspan', rowspan); 
						// 值得注意的是 如果用了$td.remove()就会对其他列的处理造成影响 
						$td.hide(); 
						// 最后一行的情况比较特殊一点 
						// 比如最后2行 td中的内容是一样的, 那么到最后一行就应该把此时的col-td里保存的td设置rowspan 
							if (++index == $table.data('trNum')) {
								$table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
							}	 
						} else { // 上一行与当前行内容不同 
							// col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理 
							if ($table.data('col-rowspan') != 1) { 
								$table.data('col-td').attr('rowspan', $table.data('col-rowspan')); 
							} 
							// 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan 
							$table.data('col-td', $td); 
							$table.data('col-content', $td.html()); 
							$table.data('col-rowspan', 1); 
						} 
					} 
				}); 
			}
	</script>
</head>
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i>广告资源管理</li>
			<li class="active"><a href="${pageContext.request.contextPath}/mnc/list">国家运营商</a></li>
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
			</a> <a class="refresh" id="refresh-toggler" href="${pageContext.request.contextPath}/mnc/list"> <i class="glyphicon glyphicon-refresh"></i>
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
						<form action="${pageContext.request.contextPath}/mnc/list" method="post" class="form-inline" role="form">
							<div class="row">
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword" value="${queryBean.keyword }" placeholder="MCC/MNC">
									</div>
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search"></i> 查询
									</button>
									<a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/mcc/doSave?keyword=${queryBean.keyword}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}"><i class="fa fa-plus"></i> 新增</a>
								</div>
							</div>
						</form>
						<br />
						<div class="flip-scroll">
							<table class="table table-hover table-bordered table-striped table-condensed flip-content" id="mccTable">
								<thead class="flip-content bordered-palegreen">
									<tr>
										<th class="text-center" style="width:160px;">国家</th>
										<th class="text-center" style="width:115px;">MCC</th>
										<th class="text-center" style="width:100px;">国家代码</th>
										<th class="text-center" style="width:130px;">运营商</th>
										<th class="text-center" style="width:115px;">MNC</th>
										<th class="text-center" style="width:150px;">描述</th>
										<th class="text-center" style="width:160px;">更新时间</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDataList.pageRecords}" var="item">
										<tr>
											<td class="text-center" style="word-break:break-all;">${item.country }</td>
											<td class="text-center" style="word-break:break-all;">${item.countryCode }</td>
											<td class="text-center" style="word-break:break-all;">${item.iso }</td>
											<td class="text-center" style="word-break:break-all;">${item.operator}</td>
											<td class="text-center" style="word-break:break-all;">${item.code }</td>
											<c:choose>
												<c:when test="${not empty item.description}">
													<td class="text-center" style="word-break:break-all;">${item.description}</td>
												</c:when>
												<c:otherwise>
													<td class="text-center">-</td>
												</c:otherwise>
											</c:choose>
											<%-- <td class="text-center" style="word-break:break-all;">${item.code }</td> --%>
											<%-- <td class="text-center" style="word-break:break-all;">${item.mncGroup }</td> --%>
											<td class="text-center"><fmt:formatDate value="${item.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td class="text-center">
												<a href="${pageContext.request.contextPath}/mcc/doUpdate/${item.countryId}?keyword=${queryBean.keyword}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}" class="btn btn-azure btn-xs shiny"><i class="fa fa-edit"></i> 编辑</a> 
												<a onclick="confirmImg(${item.countryId})" class="btn btn-darkorange btn-xs shiny"><i class="fa fa-trash-o"></i> 删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
 						<hr class="wide" />
						<div class="footer">
							<!-- 分页  -->
							<c:url var="pageUrl" value="/mnc/list">
								<c:param name="keyword" value="${queryBean.keyword}" />
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
		
		function confirmImg(id) {
			if (confirm("你确认要删除吗？")){
				window.location = "${pageContext.request.contextPath}/mcc/delete/"+id+
				"?keyword="+$("#keyword").val()+"&currentPage="+${queryBean.currentPage}+"&pageSize="+${queryBean.pageSize};
			}
		}
	</script>
</body>
</html>