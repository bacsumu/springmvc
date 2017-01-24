<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	
	<c:import url="/WEB-INF/views/common/commonJsInclude.jsp"/>
</head>
<body>
	Hello Login
	<form id="loginForm">
		<table>
			<tr>
				<td>ID : </td>
				<td><input type="text" name="loginid" value=""/></td>
			</tr>
			<tr>
				<td>PW : </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
		</table>
	</form>
	<button id="requestLoginInAjax">로그인</button>
	
<c:url value="/auth/loginProc" var="loginUrl" />
<script>
var LoginHelper = {
	/* 초기화 */
	init : function(){
		var self = this;
		// Ajax 로그인 버튼
		$("#requestLoginInAjax").click(function(){
			self.callLoginProc();
		});
	}
	/* 로그인 처리 요청 */
	,callLoginProc : function(){
		$.ajax({
			type : "POST",
			url : "${loginUrl}",
			data : $("#loginForm").serialize(), 
			dataType : 'json',
			beforeSend: function(xhr) { xhr.setRequestHeader("Accept", "application/json"); },
			success: function(data) {
				if(data.result == "SUCCESS"){
					location.href = data.data.targetUrl;
				}else if(data.result == "FAIL"){
					alert(data.message);
				}
			}
		});
	}
};

$(document).ready(function(){
	LoginHelper.init();
});
</script>
</body>
</html>
