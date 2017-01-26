package com.myweb.springmvc.service.common.exception;

/**
 * 서비스 처리 시 업무로직 오류 발생 처리를 위한 예외 클래스
 */
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException  {
	/**
	 * RuntimeException에 message 멤버변수가 있으나 생성자로 만 접근 가능하기 때문에 
	 * 서비스에서 사용하기 위하여 message 변수 재설정
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
