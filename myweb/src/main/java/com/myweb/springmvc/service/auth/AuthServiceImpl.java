package com.myweb.springmvc.service.auth;

import org.springframework.stereotype.Service;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.common.exception.ServiceException;

@Service
public class AuthServiceImpl implements AuthService{

	@Override
	public AuthorityUser login(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(AuthorityUser user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
