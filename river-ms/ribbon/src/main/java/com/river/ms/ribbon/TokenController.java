package com.river.ms.ribbon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
	@Autowired
	private TokenService computeService;

	// RestTemplate restTemplate;
	@RequestMapping(value = "/accessToken", method = RequestMethod.GET)
	public JsonResult getAccessToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token=(String) session.getAttribute("accessToken");
		if (session.isNew() || token==null || token.isEmpty()) {
			token = computeService.getAccessToken();
			session.setAttribute("accessToken", token);
		}
		return Success(token) ;
	}
	
	public JsonResult Success(Object data){
		return new JsonResult(0,"OK",data);
	}
}