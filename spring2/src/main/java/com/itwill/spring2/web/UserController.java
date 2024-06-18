package com.itwill.spring2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.UserSigninDto;
import com.itwill.spring2.dto.UserSignupDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup") // GET 방식의 /user/signup 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()");
	}

	@PostMapping("/signup")
	public String signup(UserSignupDto dto) {
		log.debug("POST: signup(dto = {})", dto);

		userService.signup(dto);
		return "redirect:/";
	}
	
	@GetMapping("/signin")
	public void signin() {
		log.debug("GET: signin()");
	}
	
	@PostMapping("/signin")
	public String signin(UserSigninDto dto, HttpSession session, @RequestParam(name = "target") String target)
			throws UnsupportedEncodingException {
		log.debug("POST: signin(dto = {})", dto);
		
		User user = userService.signin(dto);
		if (user != null) {
			session.setAttribute("signedInUser", user.getUserid());
			
			if (target == null || target.equals("")) {
				return "redirect:/";
			} else {
				return "redirect" + target;
			}
		} else {
			return "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
						
		}
			
	}
	
	@GetMapping("/profile")
	public void profile(@RequestParam(name = "userid") String userid, Model model) {
		log.debug("profile(userid = {})", userid);
		
		User user = userService.read(userid);
		model.addAttribute("user", user);
	}
	
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		log.debug("GET signout()");
		
		session.removeAttribute("signedInUser");
		session.invalidate();
		return "redirect:/user/signin";
	}
	
    // TODO: 사용자 아이디 중복체크 REST 컨트롤러
//    @GetMapping("/")
//    @ResponseBody


}

