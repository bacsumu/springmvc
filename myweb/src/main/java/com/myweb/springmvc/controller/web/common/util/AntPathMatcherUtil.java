package com.myweb.springmvc.controller.web.common.util;

import org.springframework.util.AntPathMatcher;

/**
 * Ant Path 타입 패스 매팅 유틸
 * <p>스프링 라이브러리의 배열처리기능 확장
 */
public class AntPathMatcherUtil {
	/**
	 * 여러개의 패턴 매칭 검사
	 * @param patterns 검사할 Ant Path 타입 패턴
	 * @param path 검사하고자 하는 URI
	 * @return 패턴과매치되는것이 있다면 true, 그렇지 않다면 false 반환
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
	 * 하나의 URI 패턴 매칭 검사
	 * @param pattern 검사할 Ant Path 타입 패턴
	 * @param path 검사하고자 하는 URI
	 * @return 패턴과매치되는것이 있다면 true, 그렇지 않다면 false 반환
	 */
	public static boolean match(String pattern, String path){
		AntPathMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.match(pattern, path);
	}
}
