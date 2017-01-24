package com.myweb.springmvc.controller.web.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.myweb.springmvc.controller.web.common.context.RequestContextProvider;
import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;

/**
 * ���� ���� Ŭ����
 */
@Component
public class SessionManager {
	public static final String USER_SESSION_NAME = "authUser";
	
	/**
	 * ���� ���� ���� �ð�
	 * <p>config-common.properties ���������� �� ����
	 */
	@Value("#{commonProperties['security.http.session.maxInactiveInterval']}")
	private int maxInactiveInterval;
	
	/**
	 * ���ǿ� �Ӽ� ����
	 * @param attributeName �Ӽ� ��
	 * @param object �Ӽ� ��
	 */
	public void setAttribute(String attributeName, Object object) {
		HttpServletRequest request = RequestContextProvider.getCurrentRequest();
		HttpSession session = request.getSession(true);
		// ���� ����ð� ����
		session.setMaxInactiveInterval(maxInactiveInterval);
		session.setAttribute(attributeName, object);
	}
	
	/**
	 * ���� �Ӽ� ��ȯ
	 * @param attributeName ��ȯ�ϰ��� �ϴ� �Ӽ� ��
	 * @return �Ӽ����� �ִٸ� ���� ��ȭ�ǰ� ���ٸ� null ��ȯ
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
	 * ���� ����� ����
	 * @param user ������ ����� ����
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
	 * ���� ����� ��ȯ
	 * @return ���ǿ� ��ϵ� ������ ����� ����
	 */
	public AuthorityUser getAuthUser() {
		return getAuthUser(RequestContextProvider.getCurrentRequest());
	}
	public AuthorityUser getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session == null ? null : (User) session.getAttribute(USER_SESSION_NAME);
	}
	/**
	 * ���� ���� ó��
	 */
	public void destoryHttpSession(){
		HttpSession session = getHttpSession();
		if(session != null)
			session.invalidate();
	}
}
