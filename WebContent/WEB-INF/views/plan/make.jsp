<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--  
	实际要操作的是 SalesPlan. 所以关于 SalesPlan 的 id 应该使用 Restful 风格在 URL 中携带
	/plan/1001
	
	而需要标记该 plan 是属于哪一个 salesChance 的. 这个 SalesChance 的 id 需要使用请求参数的方式
	来携带
	/plan/1001?chanceId=100
	
	还有一种方式: plan/100/1001
	
	显示当前页面的 URL 为 plan/list?chanceId=5870
-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
	
		
	var del = function(){
		var id = $(this).attr("id");
		id = id.split("-")[1];
		
		var url = "${ctx}/plan/chance/delete-ajax";
		var args = {"id":id,"time":new Date(),"_method":"DELETE"};
		$.post(url, args, function(data){
			 if(parseInt(data) > 0){
				$("#plan-" + id).remove();
				alert("删除成功!");
			}else{
				alert("删除失败!");
			} 
		});
		
		return false;
	};
	
	
	
	var update = function(){
		var id = $(this).attr("id");
		id = id.split("-")[1];
		var todo = $("#todo-" + id).val();
		
		var url = "${ctx}/plan/chance/make-ajax";
		var args = {"id":id, "todo":todo,"_method":"PUT"};
		$.post(url, args, function(data){
			$.post(url, args, function(data){
				if(parseInt(data) > 0){
					alert("修改成功!");
				}else{
					alert("修改失败!");
				}
			});
		});
		
		return false;
	};
	
	
	
		
		$(function(){
			
			$("#create").click(function(){
				//1. 向服务端传递什么数据 ? 日期和计划项
				var url = "${ctx }/plan/chance/save";		
				var date = $("#date").val();
				var todo = $("#todo").val();
				var chanceId = "${param.chanceId}";
				//alert(chanceId);
				
				var args = {"date":date,"todo":todo,"chance.id":chanceId,"time":new Date(),"_method":"POST"};
				$.post(url, args, function(data){
					if(parseInt(data) > 0){
						alert("添加成功");
						
						$("#date").val("");
						$("#todo").val("");
						
						var $tr = $("<tr id='plan-" + data + "'></tr>");
						var $dateTd = $("<td class='list_data_text'>" + date + "&nbsp;</td>");
						
						var $todoTd = $("<td class='list_data_ltext'></td>");
						
						var $todoInput = $("<input type='text' size='50' value='" + todo + "' id='todo-" + data + "'/>");
						var $saveButton = $("<button class='common_button' id='save-" + data + "'>保存</button>");
						$saveButton.click(update);
						
						var $deleteButton = $("<button class='common_button' id='delete-" + data + "'>删除</button>");
						$deleteButton.click(del);
						
						$todoTd.append($todoInput).append($saveButton).append($deleteButton);
						$tr.append($dateTd).append($todoTd);
						
						$("#planTBody").append($tr);
					}
				});
				
				return false;
			});
			
			$("button[id^='save']").click(function(){
				var id = $(this).attr("id");
				id = id.split("-")[1];
				var todo = $("#todo-" + id).val();
				
				var url = "${ctx}/plan/chance/make-ajax";
				var args = {"id":id, "todo":todo,"_method":"PUT"};
				$.post(url, args, function(data){
					$.post(url, args, function(data){
						if(parseInt(data) > 0){
							alert("修改成功!");
						}else{
							alert("修改失败!");
						}
					});
				}); 
				
				return false;
			});	 
			
			 $("button[id^='delete']").click(function(){
				var id = $(this).attr("id");
				id = id.split("-")[1];
				
				var url = "${ctx}/plan/chance/delete-ajax";
				var args = {"id":id,"time":new Date(),"_method":"DELETE"};
				$.post(url, args, function(data){
					 if(parseInt(data) > 0){
						$("#plan-" + id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败!");
					} 
				});
				
				return false;
			});	
			 
			/* $("button[id^='save']").click(saveClick);		
				
			$("button[id^='delete']").click(DeleteClick); */
			
			
			$("#execute").click(function(){
				var id = $(":hidden[name='chance.id']").val();
				window.location.href = "${ctx}/plan/chance/execution?id=" + id;
				return false;
			});
			
		})
	</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
		<form action="${ctx }/plan/chance/make" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id}
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source}
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName}
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate}
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title}
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact}
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel}
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description}
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name}
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name}
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tbody id="planTBody">
				<tr>
					<th width="200px">
						日期
					</th>
					<th>
						计划项
					</th>
				</tr>
				<c:if test="${not empty chance.salesPlans }">
					<c:forEach items="${chance.salesPlans }" var="plan">
						<tr id="plan-${plan.id}">
							<td class="list_data_text">
								<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>
								&nbsp;
							</td>
							<td class="list_data_ltext">
								<c:if test="${plan.result == null }">
									<input type="text" size="50"
										value="${plan.todo}" id="todo-${plan.id}"/>
									<button class="common_button" id="save-${plan.id}">
										保存
									</button>
									<button class="common_button" id="delete-${plan.id}">
										删除
									</button>
								</c:if>
								<c:if test="${plan.result != null }">
									<input type="text" size="50"
										value="${plan.todo}" readonly="readonly"/>
									<input type="text" size="50"
										value="${plan.result}" readonly="readonly"/>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="button_bar">
			<button class="common_button" id="create">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id}" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
