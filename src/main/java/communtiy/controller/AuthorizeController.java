package communtiy.controller;


import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.accessTokenDTO;
import dto.githubUser;
import mapper.UserMapper;
import model.User;
import provider.GithubProvider;

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
	private UserMapper userMapper;
	
	@RequestMapping("/callback")
	public String callback(@RequestParam(name="code")String code,
							@RequestParam(name="state")String state,
							HttpServletRequest request,
							HttpServletResponse response
														) {
		accessTokenDTO accessTokenDTO = new accessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		githubUser githubuser = githubProvider.getUser(accessToken);
		
		if(githubuser!=null) {
			User user = new User();
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubuser.getName());
			user.setAccountId(String.valueOf(githubuser.getId()));
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			user.setAvatarUrl(githubuser.getAvatar_url());
			userMapper.insert(user);
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
}
