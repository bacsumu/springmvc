package com.myweb.springmvc.service.common.exception;

/**
 * 서비스 공통 예외 클래스.
 * @author bacsumu
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException  {
	/**
	 * 에러 메시지
	 */
	private String message;
	
	public ServiceException(){
		
	}
	public ServiceException(String message){
		setMessage(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
}
