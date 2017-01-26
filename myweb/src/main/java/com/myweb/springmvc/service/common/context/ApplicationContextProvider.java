package com.myweb.springmvc.service.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * bean를 생성한 spring context 접근을 위한 클래스
 * <p><bean id="applicationContextProvider" class="com.myweb.springmvc.service.common.context.ApplicationContextProvider"/>
 */
public class ApplicationContextProvider  implements ApplicationContextAware{
	private static ApplicationContext context;
	
	/**
	 * root-context.xml 에서 bean 설정 시 파라미터로 ApplicationContext를 호출 해준다.
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ApplicationContextProvider.context=arg0;
	}
	/**
	 * spring context 반환
	 * @return
	 */
	public static ApplicationContext getContext() {
		return ApplicationContextProvider.context;
	}
	/**
	 * spring context의 속성값 반환
	 * @param propertyKey
	 * @return
	 */
	public static String getProperty(String propertyKey){
		return getContext().getEnvironment().getProperty(propertyKey);
	}
	
}
