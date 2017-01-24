package com.myweb.springmvc.entity.common.authority;

import java.util.List;

/**
 * 시스템 공통 권한 사용자 인터페이스
 */
public interface AuthorityUser {
	/**
	 * 사용자 키값
	 * @return
	 */
	public int getUserKey();
	/**
	 * 로그인 ID
	 * @return
	 */
	public String getLoginid();
	/**
	 * 비밀번호
	 * @return
	 */
	public String getPassword();
	/**
	 * 사용자 명
	 * @return
	 */
	public String getUserName();
	/**
	 * email
	 * @return
	 */
	public String getEmail();
	/**
	 * 시스템 권한
	 * @return
	 */
	public String getSysRoll();
	/**
	 * 권한 리스트 반환
	 * <p>controller에서 AOP 권한 검사 시에 사용
	 * @return
	 */
	public List<String> getAuthorities();
}
