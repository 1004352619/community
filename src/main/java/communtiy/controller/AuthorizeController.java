package communtiy.controller;


import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.accessTokenDTO;
import dto.githubUser;
import mapper.UserMapper;
import model.User;
import provider.GithubProvider;
import service.UserService;

@Controller
public class AuthorizeController {

	@Autowired
	private GithubProvider githubProvider;
	
	@Value("${github.client.id}")
	private String clientId;
	
	@Value("${github.client.secret}")
	private String clientSecret;
	
	@Value("${github.redirect.uri}")
	private String redirectUri;
		
	@Autowired 
	private UserService userService;
	
	@RequestMapping("/callback")
	public String callback(@RequestParam(name="code")String code,
							@RequestParam(name="state")String state,
							HttpServletRequest request,
							HttpServletResponse response) {
		accessTokenDTO accessTokenDTO = new accessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		githubUser githubuser = githubProvider.getUser(accessToken);
		
		if(githubuser!=null && githubuser.getId()!=null) {
			User user = new User();
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubuser.getName());
			user.setAccountId(String.valueOf(githubuser.getId()));
			user.setAvatarUrl(githubuser.getAvatar_url());
			userService.createOrUpdata(user);
			response.addCookie(new Cookie("token", token));
			
			//登录成功，写cookie 和cession
			/*request.getSession().setAttribute("user",githubuser);
			System.out.println("成功");*/
			return "redirect:/";
		}else {
			//登录失败，重新登录
			System.out.println("失败");
			return "redirect:/";
		}
	}
	
	
	/*
	 * 退出登录
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,
						HttpServletResponse response) {
		request.getSession().removeAttribute("user");	
		Cookie cookie = new Cookie("token",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
}
