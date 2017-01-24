package com.myweb.springmvc.service.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 소스코드 상에서 현재 bean이 적용된 spring context를 반환함.
 * <p><bean id="applicationContextProvider" class="com.myweb.springmvc.service.common.context.ApplicationContextProvider"/>
 */
public class ApplicationContextProvider  implements ApplicationContextAware{
	private static ApplicationContext context;
	
	/**
	 * Spring xml에서 bean 선언 시 context를 주입 해줌
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ApplicationContextProvider.context=arg0;
	}
	
	public static ApplicationContext getContext() {
		return ApplicationContextProvider.context;
	}
	
	public static String getProperty(String propertyKey){
		return getContext().getEnvironment().getProperty(propertyKey);
	}
	
}
