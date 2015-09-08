<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>处理客户服务</title>
	<script type="text/javascript">
		$(function(){
			$("#dealDate").val(new Date().format("yyyy-MM-dd"));
		})
	</script>
</head>

<body class="main">
	
	<span class="page_title">处理客户服务</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form action="${ctx}/service/deal" method="post">
		<input type="hidden" name="id" value="${chance.id }"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<td>${chance.id }</td>
				<th>
					服务类型
				</th>
				<td>${chance.serviceType }</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">${chance.serviceTitle }</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>${chance.customer.name }</td>
				<th>
					状态
				</th>
				<td>
					${chance.serviceState }
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">${chance.serviceRequest }</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>${chance.createdby.name}</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>

				<th>
					分配人
				</th>
				<td>${chance.allotTo.name }</td>
				<th>
					分配时间
				</th>
				<td>
					<fmt:formatDate value="${chance.allotDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>
				<th>
					服务处理
				</th>
				<td colspan="3">
					<textarea name="serviceDeal" rows="6" cols="50"></textarea>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					处理人
				</th>
				<td>
					${user.name }(${user.role.name })
					<span class="red_star">*</span>
				</td>
				<th>
					处理时间
				</th>
				<td>
					<input name="dealDate" readonly="readonly" id="dealDate" />
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
