package com.myweb.springmvc.controller.web.common.util;

import org.springframework.util.AntPathMatcher;

/**
 * Ant Ÿ�� �н� ���� ��ƿ
 * ������ ���̺귯�� �迭��� Ȯ��
 */
public class AntPathMatcherUtil {
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
	
	public static boolean match(String pattern, String path){
		AntPathMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.match(pattern, path);
	}
}
