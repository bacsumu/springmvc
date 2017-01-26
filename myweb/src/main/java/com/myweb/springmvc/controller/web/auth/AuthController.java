package com.myweb.springmvc.controller.web.auth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.springmvc.controller.web.common.session.SessionManager;
import com.myweb.springmvc.entity.common.authority.AuthorityUser;
import com.myweb.springmvc.entity.common.result.JsonResult;
import com.myweb.springmvc.entity.user.User;
import com.myweb.springmvc.service.auth.AuthService;
import com.myweb.springmvc.service.common.exception.ServiceException;

/**
 * 사용자 인증 처리를 위한 컨트롤러
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 로그인 성공 후 이동할 페이지
	@Value("#{commonProperties['security.http.form-login.default-target-url']}")
	private String httpFormLoginDefaultTargetUrl;
	// 로그아웃 성공 후 이동할 페이지
	@Value("#{commonProperties['security.http.logout.logout-success-url']}")
	private String httpLogoutLogoutSuccessUrl;
	
	@Autowired
	@Qualifier("IdPwAuthService")
	AuthService authService;
	
	@Autowired
	SessionManager	sessionManager;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/auth/login";
	}

	/**
	 * 로그인 페이지 이동
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "auth/login";
	}
	
	/**
	 * 로그인 처리
	 */
	@RequestMapping(value = "/loginProc", method = RequestMethod.POST)
	public @ResponseBody JsonResult loginProc(@ModelAttribute User user, Model model, BindingResult result) {
		try{
			// 1. 로그인 서비스 호출
			AuthorityUser findedUser = authService.login(user);
						
			// 2. 세션에 로그인 사용자 등록
			sessionManager.setAuthUser(findedUser);
			
			// 3. json 데이터 반환
			JsonResult jsonResult = new JsonResult(JsonResult.RESULT.SUCCESS, "�α��� ����");
			jsonResult.setData("targetUrl", httpFormLoginDefaultTargetUrl);
			return jsonResult;
		}catch(ServiceException e){
			return new JsonResult(JsonResult.RESULT.FAIL, 0, e.getMessage(), null);
		}
	}

	/**
	 * 로그아웃 처리
	 */
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logoutPage() throws IOException {
		// 1. logout service 호출
		AuthorityUser authUser = sessionManager.getAuthUser();
		if(authUser != null){
			authService.logout(authUser);
		}
		
		// 2. 세션에 로그인 사용자 초기화
		sessionManager.setAuthUser(null);
		return "redirect:"+httpLogoutLogoutSuccessUrl;
	}
}
