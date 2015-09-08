<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户流失管理</title>
</head>
<body>

	<div class="page_title">
		客户流失管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctx}/drain/list" method="post"> 
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户名称
				</th>
				<td>
					<input type="text" name="search_LIKE_customerName" />
				</td>
				<th>
					客户经理
				</th>
				<td>
					<input type="text" name="search_LIKE_managerName" />
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page.numberOfElements == 0}">
			没有客户信息
		</c:if>
		
		<c:if test="${page.numberOfElements != 0}">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>编号</th>
					<th>客户名称</th>
					<th>客户经理</th>
					<th>上次下单时间</th>
					<th>确认流失时间</th>
					<th>流失原因</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.content }" var="cus">
					<tr>
						<td class="list_data_number">
							${cus.id }
						</td>
						<td class="list_data_ltext">
							${cus.customer.name }
						</td>
						<td class="list_data_text">
							${cus.customer.manager.name }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${cus.lastOrderDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${cus.drainDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${cus.reason }
						</td>
						<td class="list_data_text">
							${cus.status }
						</td>
						<td class="list_data_op">
							<c:if test="#{cus.status == '流失预警' }">
								<img onclick="window.location.href=''" 
									title="确认流失" src="${ctx}/static/images/bt_confirm.gif" class="op_button" />
								<img onclick="window.location.href='${ctx}/drain/delay?id=207'" 
									title="暂缓流失" src="${ctx}/static/images/bt_relay.gif" class="op_button" />
								
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>	








<div style="text-align: right; padding: 6px 6px 0 0;">
			共 ${page.totalElements} 条记录 
			&nbsp;&nbsp;
			
			当前第 ${page.number} 页/共 ${page.totalPages} 页
			&nbsp;&nbsp;
			
			<c:if test="${page.number != 1}">
				<a href="?page=1&${queryString}">首页</a>
				&nbsp;&nbsp;
				<a href="?page=${page.number - 1}&${queryString}">上一页</a>
				&nbsp;&nbsp;
			</c:if>
			<c:if test="${page.number != page.totalPages}">
				<a href='?page=${page.number + 1}&${queryString}'>下一页</a>
				&nbsp;&nbsp;
				<a href='?page=${page.totalPages}&${queryString}'>末页</a>
				&nbsp;&nbsp;
			</c:if>
			
			
			转到 <input id="pageNo" size='1'/> 页
			&nbsp;&nbsp;

</div>

<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#pageNo").change(function(){
			
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(1>pageNo2 || pageNo2 > parseInt("1")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&sortType=&&";
			
		});
	})
</script>
	</form>
</body>
</html>
