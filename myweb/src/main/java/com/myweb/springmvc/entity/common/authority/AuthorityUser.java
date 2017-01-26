package com.myweb.springmvc.entity.common.authority;

import java.util.List;

/**
 * 인증된 사용자 인터페이스
 */
public interface AuthorityUser {
	/**
	 * 내부에서 사용할 사용자 키
	 * @return
	 */
	public int getUserKey();
	/**
	 * 로그인 아이디
	 * @return
	 */
	public String getLoginid();
	/**
	 * 로그인 패스워드
	 * @return
	 */
	public String getPassword();
	/**
	 * 사용자 이름
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
	 * 권한 검사를 위한 권한 리스트 반환
	 * <p>controller AOP 에서 호출 됨
	 * @return
	 */
	public List<String> getAuthorities();
}
