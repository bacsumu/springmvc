package com.myweb.springmvc.controller.web.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.myweb.springmvc.controller.web.common.context.RequestContextProvider;
import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;

/**
 * 세션 관리 클래스
 */
@Component
public class SessionManager {
	public static final String USER_SESSION_NAME = "authUser";
	
	// 새션 만료시간
	@Value("#{commonProperties['security.http.session.maxInactiveInterval']}")
	private int maxInactiveInterval;
	
	/**
	 * 세션에 속성 설정
	 * @param attributeName
	 * @param object
	 */
	public void setAttribute(String attributeName, Object object) {
		HttpServletRequest request = RequestContextProvider.getCurrentRequest();
		HttpSession session = request.getSession(true);
		// 세션 만료시간 설정
		session.setMaxInactiveInterval(maxInactiveInterval);
		session.setAttribute(attributeName, object);
	}
	
	/**
	 * 세션 속성 반환
	 * @param attributeName
	 * @return
	 */
	public Object getAttribute(String attributeName){
		HttpServletRequest request = RequestContextProvider.getCurrentRequest();
		HttpSession session = request.getSession(false);
		if(session != null){
			return session.getAttribute(attributeName);
		}
		return null;
	}
	
	/**
	 * 인증 사용자 설정
	 * @param user
	 */
	public void setAuthUser(AuthorityUser user) {
		setAttribute(SessionManager.USER_SESSION_NAME, user);
		String sessionId = createSessionId();
		setAttribute(sessionId, user);
	}
	
	public HttpSession getHttpSession() {
		HttpServletRequest request = RequestContextProvider.getCurrentRequest();
		return request.getSession(false);
	}
	
	public boolean hasAuthUser(HttpServletRequest request) {
		return getAuthUser(request) != null;
	}
			
	private String createSessionId() {
		String id = getHttpSession().getId();
		setAttribute("sessionId", id);
		return id;
	}
	/**
	 * 인증 사용자 반환
	 * @return
	 */
	public AuthorityUser getAuthUser() {
		return getAuthUser(RequestContextProvider.getCurrentRequest());
	}
	public AuthorityUser getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session == null ? null : (User) session.getAttribute(USER_SESSION_NAME);
	}
	/**
	 * 제션 만료 처리
	 */
	public void destoryHttpSession(){
		HttpSession session = getHttpSession();
		if(session != null)
			session.invalidate();
	}
}
