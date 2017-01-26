package com.myweb.springmvc.controller.web.common.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller 파라미터가 아닌 JAVA Bean에서 현재 요청된 HttpServletRequest를 반환하기 위한 클래스
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
