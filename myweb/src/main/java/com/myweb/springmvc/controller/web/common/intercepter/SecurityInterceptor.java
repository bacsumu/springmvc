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
 * mvc 에서 공통 인증여부 검사 처리
 */
public class SecurityInterceptor implements HandlerInterceptor{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 인증 예외 URL 패턴
	@Value("#{commonProperties['security.http.intercept-url.none'].split(',')}")
	private String[] httpInterceptUrlNone; 
	// 인증 필요 URL 패턴
	@Value("#{commonProperties['security.http.intercept-url.isAuthenticated'].split(',')}")
	private String[] httpInterceptUrlIsAuthenticated;
	
	// 로그인 페이지 URL [GET]
	@Value("#{commonProperties['security.http.form-login.login-page']}")
	private String httpFormLoginLoginPage;
	
	@Autowired
	SessionManager	sessionManager;
	
	/**
	 * 컨트롤러 @RequestMapping 함수 호출 전 
	 * @return true이면 컨트롤러의 함수 호출이 진행되고 false이면 진행 중지 됨.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		
		// 인증 예외 url 처리
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlNone, requestUri))
			return true;
		// 인증이 필요한 url 세션 검사
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlIsAuthenticated, requestUri)){
			AuthorityUser authUser = sessionManager.getAuthUser();
			if(authUser == null){
				// ajax 호출 인 경우 로그인페이지로 이동을 위한 status 값을 401로 전달
				// 처리내용은 jquery ajax 설정에서 로그인 페이지로 이동 처리
				if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
					//401 Unauthorized
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return false;
				}else{
					response.sendRedirect(httpFormLoginLoginPage);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 컨트롤러 @RequestMapping 함수 호출 후
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 컨트롤러 View 페이지 생성 후
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
