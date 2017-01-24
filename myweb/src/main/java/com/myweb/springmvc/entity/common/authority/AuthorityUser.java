package com.myweb.springmvc.entity.common.authority;

import java.util.List;

/**
 * �ý��� ���� ���� ����� �������̽�
 */
public interface AuthorityUser {
	/**
	 * ����� Ű��
	 * @return
	 */
	public int getUserKey();
	/**
	 * �α��� ID
	 * @return
	 */
	public String getLoginid();
	/**
	 * ��й�ȣ
	 * @return
	 */
	public String getPassword();
	/**
	 * ����� ��
	 * @return
	 */
	public String getUserName();
	/**
	 * email
	 * @return
	 */
	public String getEmail();
	/**
	 * �ý��� ����
	 * @return
	 */
	public String getSysRoll();
	/**
	 * ���� ����Ʈ ��ȯ
	 * <p>controller���� AOP ���� �˻� �ÿ� ���
	 * @return
	 */
	public List<String> getAuthorities();
}
