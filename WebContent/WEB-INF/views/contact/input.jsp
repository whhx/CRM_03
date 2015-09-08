<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>编辑联系人</title>
    <script type="text/javascript">
    	$(function(){
    		var val = $("#contactname").val();
    		if(val != null && $.trim(val) != ""){
    			$("#contactname").attr("readonly", "readonly");
    		}
    	})
    </script>
  </head>

  <body class="main">
 	<span class="page_title">编辑联系人</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button" onclick="document.forms[0].submit();">保存</button>
	</div>
	
	<form:form action="${ctx}/contact/create" method="POST" modelAttribute="contact">
  		<c:if test="${contact.id != null}">
  			<form:hidden path="id"/>
  			<input type="hidden" name="_method" value="PUT"/>
  		</c:if>
	  	<c:if test="${customerid != null}">
		  	<input type="hidden" name="customer.id" value="${customerid}">
	  	</c:if>
  		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>姓名</th>
				<td>
					<form:input path="name" id="contactname"/>
				<th>性别</th>
				<td>
					<form:select path="sex" items="${sex}"/>
					<span class="red_star">*</span> 
				</td>
			</tr>
			
			<tr>
				<th>职位</th>
				<td><form:input path="position"/><span class="red_star">*</span></td>
				<th>办公电话</th>
				<td><form:input path="tel"/><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>手机</th>
				<td><form:input path="mobile"/></td>
				<th>备注</th>
				<td><form:input path="memo"/></td>
			</tr>
		</table>
	</form:form>
  </body>
</html>
