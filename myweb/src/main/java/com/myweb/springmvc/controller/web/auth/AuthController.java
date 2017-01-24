package com.myweb.springmvc.controller.web.auth;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/auth")
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 로그후 페이지 [GET]
	@Value("#{commonProperties['security.http.form-login.default-target-url']}")
	private String httpFormLoginDefaultTargetUrl;
	// 로그아웃 성공 후 URL [GET]
	@Value("#{commonProperties['security.http.logout.logout-success-url']}")
	private String httpLogoutLogoutSuccessUrl;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	SessionManager	sessionManager;
	
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/auth/login";
	}

	/**
	 * 로그인 페이지 요청
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "auth/login";
	}
	/**
	 * 로그인 처리
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginProc", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JsonResult loginProc(HttpServletRequest request, @ModelAttribute User user, Model model, BindingResult result) {
		try{
			// 1. 로그인 서비스 호출
			AuthorityUser findedUser = authService.login(user);
						
			// 2. 세션 생성
			sessionManager.setAuthUser(findedUser);
			
			// 3. json 결과 반환
			JsonResult jsonResult = new JsonResult(JsonResult.RESULT.SUCCESS, "로그인 성공");
			jsonResult.setData("targetUrl", httpFormLoginDefaultTargetUrl);
			return jsonResult;
		}catch(ServiceException e){
			return new JsonResult(JsonResult.RESULT.FAIL, 0, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. logout service 호출
		AuthorityUser authUser = sessionManager.getAuthUser();
		if(authUser != null){
			authService.logout(authUser);
		}
		
		// 2. 인증 사용자 제거
		sessionManager.setAuthUser(null);
		return "redirect:"+httpLogoutLogoutSuccessUrl;
	}
}
