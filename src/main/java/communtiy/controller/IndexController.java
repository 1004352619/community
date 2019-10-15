package communtiy.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mapper.UserMapper;
import model.User;

@Controller
public class IndexController {
	
	@Autowired
	private UserMapper usermapper;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("token")) {
				String token = cookie.getValue();
				User user = usermapper.findByToken(token);
				if(user != null) {
					request.getSession().setAttribute("user", user);				}
				break;
			}	
		}
		return "index";
	}

}
