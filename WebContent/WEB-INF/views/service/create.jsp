<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新建客户服务</title>
	<script type="text/javascript">
		$(function(){
			$("#createDate").val(new Date().format("yyyy-MM-dd"));
		})
	</script>
</head>

<body class="main">

	<span class="page_title">新建客户服务</span>
	
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form:form action="${ctx }/service/create" method="post" modelAttribute="customerService">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<form:select path="serviceType">
						<form:option value="">未指定</form:option>
						<form:options items="${serviceTypes }" itemLabel="item" itemValue="item" />
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					<form:input path="serviceTitle"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<form:select path="customer.id">
						<form:option value="">未指定</form:option>
						<form:options items="${customers }" itemLabel="name" itemValue="id" />
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					状态
				</th>
				<td>
					新创建 <form:input path="serviceState" type="hidden" value="新创建"/>
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">
					<form:textarea path="serviceRequest"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${user.name }(${user.role.name })
					<input type="hidden" name="createdby.id" value="${sessionScope.user.id}">
					<span class="red_star">*</span>
				</td>
				<th>
					创建时间
				</th>
				<td>
					<form:input path="createDate" readonly="readonly"/>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form:form>
</body>

</html>
