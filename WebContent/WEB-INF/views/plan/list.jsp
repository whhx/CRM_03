<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户开发计划</title>
</head>

<body class="main">
	<form action="${ctx }/plan/list" method="post">
		<div class="page_title">客户开发计划</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[0].submit();">
				查询</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
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
					<th>概要</th>
					<th>联系人</th>
					<th>联系人电话</th>
					<th>创建时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.content}" var="chance">
				<tr>
					<td class="list_data_number">${chance.id}</td>
					<td class="list_data_text">${chance.custName}</td>
					<td class="list_data_text">${chance.title}</td>
					<td class="list_data_text">${chance.contact}</td>
					<td class="list_data_text">${chance.contactTel}</td>
					<td class="list_data_text">
						<fmt:formatDate value="${chance.createDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_text"><script type="text/javascript">
									switch('${chance.status}')
									{
										case '2':
											document.write('开发中');
											break;
										case '3':
											document.write('开发成功');
											break;
										case '4':
										    document.write('开发失败');
										   	break;
									}
								</script></td>
					<td class="list_data_op">
					<c:if test='${chance.status==2}'>
					<img
						onclick="window.location.href='${ctx }/plan/chance/create?chanceId=${chance.id }'" title="制定计划"
						src="${ctx }/static/images/bt_plan.gif" class="op_button" /> 
					<img
						onclick="window.location.href='${ctx }/plan/chance/execution?id=${chance.id }'"
						title="执行计划" src="${ctx }/static/images/bt_feedback.gif"
						class="op_button" /> 
					<img
						onclick="window.location.href='${ctx }/chance/finish?id=${chance.id }'"
						title="开发成功" src="${ctx }/static/images/bt_yes.gif" class="op_button" />
					</c:if>
	
					<c:if test='${chance.status!="2"}'>
						<img
							onclick="window.location.href='${ctx }/chance/detail?id=${chance.id}'"
							title="查看" src="${ctx }/static/images/bt_detail.gif" class="op_button" />
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

		<script type="text/javascript"
			src="${ctx }/static/jquery/jquery-1.9.1.min.js"></script>
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
