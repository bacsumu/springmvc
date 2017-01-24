package com.myweb.springmvc.service.auth;

import org.springframework.stereotype.Service;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.common.exception.ServiceException;

@Service
public class AuthServiceImpl implements AuthService{

	@Override
	public AuthorityUser login(User user) throws ServiceException {
		if("test".equals(user.getLoginid()) && "1234".equals(user.getPassword()))
			return user;
		else 
			throw new ServiceException("잘못된 사용자 입니다.");
	}

	@Override
	public void logout(AuthorityUser user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
