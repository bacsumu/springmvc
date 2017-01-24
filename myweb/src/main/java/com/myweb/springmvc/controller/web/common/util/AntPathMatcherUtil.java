package com.myweb.springmvc.controller.web.common.util;

import org.springframework.util.AntPathMatcher;

/**
 * Ant 타입 패스 매팅 유틸
 * 스프링 라이브러리 배열기능 확장
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
