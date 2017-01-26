package com.myweb.springmvc.controller.web.common.util;

import org.springframework.util.AntPathMatcher;

/**
 * Ant Path 형식 URI 처리 유틸 클래스
 */
public class AntPathMatcherUtil {
	private static AntPathMatcher pathMatcher = new AntPathMatcher();
	
	/**
	 * Ant Path 배열 파라미터 검사
	 */
	public static boolean matchArray(String[] patterns, String path){
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
	 * Ant Path 검사
	 */
	public static boolean match(String pattern, String path){
		return pathMatcher.match(pattern, path);
	}
}
