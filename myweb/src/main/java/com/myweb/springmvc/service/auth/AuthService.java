package com.myweb.springmvc.service.auth;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.common.exception.ServiceException;

/**
 * 인증 처리 인터페이스
 */
public interface AuthService {
	/**
	 * 로그인 처리
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public AuthorityUser login(User user) throws ServiceException;
	/**
	 * 로그아웃 처리
	 * @param user
	 * @throws ServiceException
	 */
	public void logout(AuthorityUser user) throws ServiceException;
}
