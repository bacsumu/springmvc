package com.myweb.springmvc.controller.web.common.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ���� request ���� ���� ���� Ŭ����
 */
public class RequestContextProvider {
	/**
	 * ���� ��û�� HttpServletRequest ��ȯ
	 * @return
	 */
	public static HttpServletRequest getCurrentRequest() {      
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest();    
	}
}
