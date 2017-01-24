package com.myweb.springmvc.service.common.exception;

/**
 * ���� ���� ���� Ŭ����.
 * @author bacsumu
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException  {
	/**
	 * ���� �޽���
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
