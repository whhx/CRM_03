<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>管理</title>
</head>
<body>
	<div class="page_title">
		基础数据管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctx}/dict/create'">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctx}/dict/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					类别
				</th>
				<td>
					<input type="text" name="search_LIKE_type" />
				</td>
				<th>
					条目
				</th>
				<td>
					<input type="text" name="search_LIKE_item" />
				</td>
				<th>
					值
				</th>
				<td>
					<input type="text" name="search_LIKE_value" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 列表数据 -->
	<br />
		
		<c:if test="${page.numberOfElements == 0 }">
		没有任何记录.
		</c:if>
		<c:if test="${page.numberOfElements > 0 }">
	
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>编号</th>
				<th>类别</th>
				<th>条目</th>
				<th>值</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.content }" var="item">
			<tr>
				<td class="list_data_number">
					${item.id }
				</td>
				<td class="list_data_text">
					${item.type }
				</td>
				<td class="list_data_text">
					${item.item }
				</td>
				<td class="list_data_text">
					${item.value }
				</td>

				<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/dict/create?id=6121'" 
							title="编辑" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/dict/delete?id=6121'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
				</td>
			</tr>
			</c:forEach>			
		</table>
		<tags:pagination2 paginationSize="5" page="${page }"></tags:pagination2>
		</c:if>
		
		

<%-- <div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements} 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.number+1} 页/共 ${page.totalPages} 页
	&nbsp;&nbsp;
	
	<c:if test="${page.number+1 > 1}">
		<a href="?page=1" >首页</a>
		&nbsp;&nbsp;
		<a href="?page=${page.number+1 - 1}" >上一页</a>
		&nbsp;&nbsp;
	</c:if>	
	<c:if test="${page.number+1 < page.totalPages}">
		<a href="?page=${page.number + 1+1}" >下一页</a>
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
			if(1>pageNo2  || pageNo2 > parseInt("5")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&sortType=${sortType}&${searchParams}&${condition}";

			
		});
	})
</script>
	
	 --%>
	
</body>
</html>