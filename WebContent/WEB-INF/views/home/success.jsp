<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>客户关系管理系统</title>

<frameset rows="85,*,40" frameborder="NO" noresize Borders="NO"
	framespacing="0">
	
	<frame name="topFrame" frameborder="NO" scrolling="NO" noresize
		Borders="NO" src="${ctx}/header" marginwidth="value"
		marginheight="value">
	
	<frameset rows="*" cols="180,*" border="0" noresize framespacing="2">
	
		<frame name="menu" src="${ctx}/user/menu" border="0"
			scrolling="auto" marginwidth="0" leftmargin="0" marginheight="0"
			APPLICATION="yes">
	
		<frame name="content" src="${ctx}/welcome"
			frameborder="no" marginwidth="0" marginheight="0" APPLICATION="yes">
	
	</frameset>
	
	<frame src="${ctx}/footer" name="#" frameborder="NO"
		scrolling="NO" noresize marginwidth="0" marginheight="0" Borders="NO">
		
</frameset>