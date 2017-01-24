package com.myweb.springmvc.service.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider  implements ApplicationContextAware{
	private static ApplicationContext context;
	
	/**
	 * Spring xml���� bean ���� �� context�� ���� ����
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
