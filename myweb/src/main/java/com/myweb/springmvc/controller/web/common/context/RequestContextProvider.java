package com.myweb.springmvc.controller.web.common.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 현재 request 정보 접근 관리 클래스
 */
public class RequestContextProvider {
	/**
	 * 현재 요청된 HttpServletRequest 반환
	 * @return
	 */
	public static HttpServletRequest getCurrentRequest() {      
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attributes.getRequest();    
	}
}
