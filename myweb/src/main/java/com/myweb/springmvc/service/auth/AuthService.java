package com.myweb.springmvc.service.auth;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.common.exception.ServiceException;

/**
 * ���� ó�� �������̽�
 */
public interface AuthService {
	/**
	 * �α��� ó��
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public AuthorityUser login(User user) throws ServiceException;
	/**
	 * �α׾ƿ� ó��
	 * @param user
	 * @throws ServiceException
	 */
	public void logout(AuthorityUser user) throws ServiceException;
}
