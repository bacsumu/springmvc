<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	
	<c:import url="/WEB-INF/views/common/commonJsInclude.jsp"/>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<button id="requestLogoutInAjax">로그아웃</button>

<c:url value="/auth/logout" var="logoutUrl" />
<script>
var LogoutHelper = {
	/* 초기화 */
	init : function(){
		var self = this;
		// Ajax 로그인 버튼
		$("#requestLogoutInAjax").click(function(){
			self.callLogoutProc();
		});
	}
	/* 로그아웃 처리 요청 */
	,callLogoutProc : function(){
		document.location.href="${logoutUrl}";
	}
};

$(document).ready(function(){
	LogoutHelper.init();
});
</script>

</body>
</html>
