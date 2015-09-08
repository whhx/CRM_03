<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>联系人管理</title>
<script type="text/javascript">

	$(function(){
		
		$("img[id='deleteContact']").click(function(){
			var url = this.name;
			$("form[id='delete']").attr("action",url).submit();
			return false;
		});
	});
</script> 
	
	
</head>

<body>

	<div class="page_title">
		联系人管理
	</div>
	<div class="button_bar">

		<button class="common_button" onclick="window.location.href='${ctx}/contact/create?customerid=${customer.id}'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
	<form action="" id="delete" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<form action="${ctx}/contact/list" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>${customer.no}</td>
				<th>
					客户名称
				</th>
				<td>${customer.name}</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page == null || page.totalElements <= 0 }">
			没有对应的管理人
		</c:if>
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						姓名
					</th>
					<th>
						性别
					</th>
					<th>
						职位
					</th>
					<th>
						办公电话
					</th>
					<th>
						手机
					</th>
					<th>
						备注
					</th>
					<th>
						操作
					</th>
				</tr>
	
				<c:forEach items="${page.content}" var="contact">
						<tr>
						<td class="list_data_text">
							${contact.name}
						</td>
						<td class="list_data_text">
							${contact.sex}
						</td>
						<td class="list_data_text">
							${contact.position}
						</td>
						<td class="list_data_text">
							${contact.tel}
						</td>
						<td class="list_data_text">
							${contact.mobile}
						</td>
						<td class="list_data_text">
							${contact.memo}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctx}/contact/create?id=${contact.id}'" 
								title="编辑" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
							<img id="deleteContact" name="${ctx}/contact/delete?id=${contact.id}&customerid=${customer.id}" 
								 title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements} 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.number} 页/共 ${page.totalPages} 页
	
	<c:if test="${page.number > 1}">
		<a href="?id=${customer.id}&page=1">首页</a>
		<a href="?id=${customer.id}&page=${page.number - 1}">上一页</a>
	</c:if>
	<c:if test="${page.number < page.totalPages}">
		<a href="?id=${customer.id}&page=${page.number + 1}">下一页</a>
		<a href="?id=${customer.id}&page=${page.totalPages}">末页</a>
	</c:if>
	
	
	&nbsp;&nbsp;
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
			if(pageNo2 < 1 || pageNo2 > parseInt("${page.totalPages}")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&id=${customer.id}";
			
		});
	})
</script>
		
		
	</form>
</body>
</html>