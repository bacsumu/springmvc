package com.myweb.springmvc.controller.web.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.springmvc.controller.web.common.session.SessionManager;
import com.myweb.springmvc.controller.web.common.util.AntPathMatcherUtil;
import com.myweb.springmvc.entity.common.authority.AuthorityUser;

/**
 * Spring MVC 인터셉터 : 인증 검사 클래스
 */
public class SecurityInterceptor implements HandlerInterceptor{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 인증이 필요없는 URL 설정값 
	@Value("#{commonProperties['security.http.intercept-url.none'].split(',')}")
	private String[] httpInterceptUrlNone; 
	// 인증이 필요한 URL 설정값 
	@Value("#{commonProperties['security.http.intercept-url.isAuthenticated'].split(',')}")
	private String[] httpInterceptUrlIsAuthenticated;
	
	// 로그인 페이지 URL
	@Value("#{commonProperties['security.http.form-login.login-page']}")
	private String httpFormLoginLoginPage;
	
	@Autowired
	SessionManager	sessionManager;
	
	/**
	 * 컨트롤러 호출 전 처리
	 * @return true 반환시 컨트롤러 호출, false 반환시 중지
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		
		// 인증이 필요없는 URL 검사
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlNone, requestUri))
			return true;
		// 인증이 필요한 URL 검사
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlIsAuthenticated, requestUri)){
			// 현재 인증되어 있는 사용자 정보 요청
			AuthorityUser authUser = sessionManager.getAuthUser();
			if(authUser == null){
				if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
					// ajax 요청인 경우 HTTP 401 상태값으로 반환
					// jquery ajaxError 이벤트 핸들러에서 로그인 페이지 이동 처리
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return false;
				}else{
					// 로그인 페이지로 이동
					response.sendRedirect(httpFormLoginLoginPage);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 컨트롤러 호출 후 처리
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 클라이언트 Response 반환 후 처리
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
