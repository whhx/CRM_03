<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户基本信息管理</title>

<script type="text/javascript">
	$(function(){
		
		$("img[id='deleteCustomer']").click(function(){
			
			var state = $(this).parent("td").prev();
			
			var url = this.name;
			
			var args = {'time': new Date()};
			
			$.post(url, args, function(data){
				if(parseInt(data) == 1){
					alert("删除成功！");
					state.text("删除");
					
				}else if(parseInt(data) == 0){
					alert("删除失败！");
				}
			});
			return false;
		});
	});
</script>

</head>
<body>

	<div class="page_title">客户基本信息管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>
	</div>
	
	<form:form action="${ctx}/customer/list" mathod="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>客户名称</th>
				<td>
					<input type="text" name="search_LIKE_name"/>
				</td>
				<th>地区</th>
				<td>
					<select name="search_EQ_region">
						<option value="">全部</option>
						<c:forEach items="${regions }" var="region">
							<option value="${region.item}">${region.item }</option>
						</c:forEach>
					</select>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="search_LIKE_managername" /></td>
				
				<th>客户等级</th>
				<td>
					<select name="search_EQ_level">
						<option value="">全部</option>
						<c:forEach items="${levels }" var="level">
							<option value="${level.item}">${level.item }</option>
						</c:forEach>
					</select>
				</td>
				
				<th>状态</th>
				<td>
					<select name="search_EQ_state">
						<option value="">全部</option>
						<option value="正常">正常</option>
						<option value="流失">流失</option>
						<option value="删除">删除</option>					
					</select>
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page == null || page.totalElements == 0}">
			没有客户信息
		</c:if>
		<c:if test="${page != null && page.totalElements > 0}">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>客户编号</th>
					<th>客户名称</th>
					<th>地区</th>
					<th>客户经理</th>
					<th>客户等级</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
				<c:forEach var="customer" items="${page.content }">
					<tr>
						<td class="list_data_text">${customer.no}&nbsp;</td>
						<td class="list_data_ltext">${customer.name}&nbsp;</td>
						<td class="list_data_text">${customer.region}&nbsp;</td>
						<td class="list_data_text">${customer.manager.name}&nbsp;</td>
						<td class="list_data_text">${customer.level}&nbsp;</td>
						<td class="list_data_text">${customer.state}&nbsp;</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctx}/customer/edit/${customer.id}'"
								title="编辑" src="${ctx}/static/images/bt_edit.gif" class="op_button" alt="" /> 
							<img onclick="window.location.href='${ctx}/contact/list?id=${customer.id}'"
								title="联系人" src="${ctx}/static/images/bt_linkman.gif" class="op_button" alt="联系人信息" /> 
							<img onclick="window.location.href='${ctx}/activity/list?customerid=${customer.id}'"
								title="交往记录" src="${ctx}/static/images/bt_acti.gif" class="op_button" alt="交往记录" /> 
							<img onclick="window.location.href='${ctx}/order/list?customerid=${customer.id}'"
								title="历史订单" src="${ctx}/static/images/bt_orders.gif" class="op_button" alt="历史订单" /> 
						
							<img id="deleteCustomer" name="${ctx}/customer/delete?id=${customer.id}" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" alt="删除" />
								
							</td>					
					</tr>
				</c:forEach>
			</table>
		</c:if>
<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements} 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.number} 页/共 ${page.totalPages} 页
	&nbsp;&nbsp;
	
	<c:if test="${page.number > 1}">
		<a href="?page=1" >首页</a>
		&nbsp;&nbsp;
		<a href="?page=${page.number - 1}" >上一页</a>
		&nbsp;&nbsp;
	</c:if>	
	<c:if test="${page.number < page.totalPages}">
		<a href="?page=${page.number + 1}" >下一页</a>
		&nbsp;&nbsp;
		<a href="?page=${page.totalPages}" >末页</a>
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
			if(1>pageNo2 || pageNo2 > parseInt("2")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "";
			
		});
	})
</script>
		
	</form:form>	
	
</body>
</html>
