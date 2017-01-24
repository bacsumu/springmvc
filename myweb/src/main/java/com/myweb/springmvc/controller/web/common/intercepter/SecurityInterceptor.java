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
 * mvc ���� ���� �������� �˻� ó��
 */
public class SecurityInterceptor implements HandlerInterceptor{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// ���� ���� URL ����
	@Value("#{commonProperties['security.http.intercept-url.none'].split(',')}")
	private String[] httpInterceptUrlNone; 
	// ���� �ʿ� URL ����
	@Value("#{commonProperties['security.http.intercept-url.isAuthenticated'].split(',')}")
	private String[] httpInterceptUrlIsAuthenticated;
	
	// �α��� ������ [GET]
	@Value("#{commonProperties['security.http.form-login.login-page']}")
	private String httpFormLoginLoginPage;
	// �α��� ó�� url [POST]
	@Value("#{commonProperties['security.http.form-login.login-processing-url']}")
	private String httpFormLoginLoginProcessingUrl;
	// �α��� ������ [GET]
	@Value("#{commonProperties['security.http.form-login.default-target-url']}")
	private String httpFormLoginDefaultTargetUrl;
	
	// �α׾ƿ� ȣ�� URL [POST]
	@Value("#{commonProperties['security.http.logout.logout-url']}")
	private String httpLogoutLogoutUrl;
	// �α׾ƿ� ���� �� URL [GET]
	@Value("#{commonProperties['security.http.logout.logout-success-url']}")
	private String httpLogoutLogoutSuccessUrl;
	
	@Autowired
	SessionManager	sessionManager;
	
	/**
	 * ��Ʈ�ѷ� @RequestMapping �Լ� ȣ�� �� 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String method = request.getMethod();
		
		// �α��� ������ ���� ������ �ִٸ� �α��� ���� �������� �̵�
		if(method.toUpperCase().equals("GET") && httpFormLoginLoginPage.equals(requestUri)){
			AuthorityUser authUser = sessionManager.getAuthUser();
			if(authUser != null){
				response.sendRedirect(httpFormLoginDefaultTargetUrl);
				return false;
			}
		}
		
		// �α׾ƿ� ������ ȣ�� �� ������ ���ٸ� �α��� �������� �̵�
		if(httpLogoutLogoutUrl.equals(requestUri)){
			if(sessionManager.getAuthUser() == null){
				response.sendRedirect(httpLogoutLogoutSuccessUrl);
				return false;
			}
		}
		// ���� ���� url ó��
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlNone, requestUri))
			return true;
		// ������ �ʿ��� url ���� �˻�
		if(AntPathMatcherUtil.matchArray(httpInterceptUrlIsAuthenticated, requestUri)){
			AuthorityUser authUser = sessionManager.getAuthUser();
			if(authUser == null){
				// ajax ȣ�� �� ��� �α����������� �̵� status ���� 401�� ����
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
	 * ��Ʈ�ѷ� @RequestMapping �Լ� ȣ�� ��
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * ��Ʈ�ѷ� View ������ ���� ��
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
