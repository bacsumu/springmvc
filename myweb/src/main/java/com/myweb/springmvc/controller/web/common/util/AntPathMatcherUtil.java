package com.myweb.springmvc.controller.web.common.util;

import org.springframework.util.AntPathMatcher;

/**
 * Ant Path Ÿ�� �н� ���� ��ƿ
 * <p>������ ���̺귯���� �迭ó����� Ȯ��
 */
public class AntPathMatcherUtil {
	/**
	 * �������� ���� ��Ī �˻�
	 * @param patterns �˻��� Ant Path Ÿ�� ����
	 * @param path �˻��ϰ��� �ϴ� URI
	 * @return ���ϰ���ġ�Ǵ°��� �ִٸ� true, �׷��� �ʴٸ� false ��ȯ
	 */
	public static boolean matchArray(String[] patterns, String path){
		AntPathMatcher pathMatcher = new AntPathMatcher();
		
		if(patterns == null || patterns.length == 0)
			return true;
		for(String pattern : patterns){
			if(pattern != null && pattern.trim().length() > 0){
				if(pathMatcher.match(pattern, path))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * �ϳ��� URI ���� ��Ī �˻�
	 * @param pattern �˻��� Ant Path Ÿ�� ����
	 * @param path �˻��ϰ��� �ϴ� URI
	 * @return ���ϰ���ġ�Ǵ°��� �ִٸ� true, �׷��� �ʴٸ� false ��ȯ
	 */
	public static boolean match(String pattern, String path){
		AntPathMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.match(pattern, path);
	}
}
