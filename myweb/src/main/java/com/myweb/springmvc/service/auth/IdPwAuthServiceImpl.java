package com.myweb.springmvc.service.auth;

import org.springframework.stereotype.Service;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.common.exception.ServiceException;

/**
 * loginid와 password를 사용한 인증 처리
 * @author bacsumu
 *
 */
@Service("IdPwAuthService")
public class IdPwAuthServiceImpl implements AuthService{

	@Override
	public AuthorityUser login(User user) throws ServiceException {
		if("test".equals(user.getLoginid()) && "1234".equals(user.getPassword()))
			return user;
		else 
			throw new ServiceException("로그인에 실패 하였습니다.");
	}

	@Override
	public void logout(AuthorityUser user) throws ServiceException {
		
	}

}
