package com.myweb.springmvc.entity.user;

import java.util.ArrayList;
import java.util.List;

import com.myweb.springmvc.entity.common.authority.AuthorityUser;

/**
 * 시스템 사용자 클래스
 */
public class User implements AuthorityUser{
	private int userKey;
	private String loginid;
	private String password;
	private String userName;
	private String email;
	private String sysRoll;
	
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSysRoll(String sysRoll) {
		this.sysRoll = sysRoll;
	}

	@Override
	public int getUserKey() {
		return this.userKey;
	}

	@Override
	public String getLoginid() {
		return this.loginid;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getSysRoll() {
		return this.sysRoll;
	}

	@Override
	public List<String> getAuthorities() {
		List<String> authorities = new ArrayList<String>();
		authorities.add(this.sysRoll);
		return authorities;
	}
}
