package interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import mapper.UserMapper;
import model.User;


@Service
public class SessionInterceptor implements HandlerInterceptor{

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length != 0)
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("token")) {
				String token = cookie.getValue();
				User user = usermapper.findByToken(token);
				if(user != null) {
					request.getSession().setAttribute("user", user);	
				}
				break;
			}	
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
