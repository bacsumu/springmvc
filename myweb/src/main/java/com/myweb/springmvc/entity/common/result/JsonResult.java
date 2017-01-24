package com.myweb.springmvc.entity.common.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Json 결과 공통 값
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 1436548017458911106L;

	public enum RESULT {
		SUCCESS /* 성공 */, FAIL /* 업무 처리 실패 */, ERROR /* 시스템 오류 */
	};

	private RESULT result = RESULT.SUCCESS;
	private String message = "";
	private int code;
	private Map<String, Object> data = new HashMap<String, Object>();

	public JsonResult() {
	}

	public JsonResult(RESULT result) {
		this.result = result;
	}

	public JsonResult(RESULT result, String message) {
		this.result = result;
		setMessage(message);
	}

	public JsonResult(RESULT result, String message, Map<String, Object> data) {
		this.result = result;
		setMessage(message);
		this.data = data;
	}

	public JsonResult(RESULT result, int code, String message, Map<String, Object> data) {
		this.result = result;
		this.code = code;
		setMessage(message);
		this.data = data;
	}

	public RESULT getResult() {
		return result;
	}

	public void setResult(RESULT result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		setMessage(message, null);
	}

	public void setMessage(String message, Object[] args) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(String key, Object value) {
		this.data.put(key, value);
	}
}
