/**
 * 공통 처리 코드
 */

/**
 * console 객체를 지원하지 않는 부라우저 방어코드
 */
if(!window.console){
	window.console = {};
	window.console.trace = function(){};
	window.console.log = function(){};
	window.console.error = function(){};
	window.console.dir = function(){};
}

/**
 * jquery ajax 호출 시 cache 사용하지 않도록 설정
 */
$.ajaxSetup({ cache: false });

/**
 * ajax 오류 공통 처리
 */
$(document).ajaxError(function(event, jqxhr, settings, exception){
	if(jqxhr.status === 401){		//401 Unauthorized
		// 인증이 필요한 페이지에 세션이 만료된 경우 처리
		window.location.href = "/";
	}else{
		if(jqxhr.responseJSON && jqxhr.responseJSON.message){
			alert(jqxhr.responseJSON.message);
		}else{
			alert("시스템 오류가 발생하였습니다.");
			if(exception && exception != "")
				console.error(exception);
		}
	}
});
