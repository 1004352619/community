package communtiy.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.accessTokenDTO;
import dto.githubUser;
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
	
	@RequestMapping("/callback")
	public String callback(@RequestParam(name="code")String code,
							@RequestParam(name="state")String state,
							HttpServletRequest request) {
		accessTokenDTO accessTokenDTO = new accessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		githubUser user = githubProvider.getUser(accessToken);
		
		if(user!=null) {
			//登录成功，写cookie 和cession
			request.getSession().setAttribute("user",user);
			System.out.println("成功");
			return "redirect:/";
		}else {
			//登录失败，重新登录
			System.out.println("失败");
			return "redirect:/";
		}
		
		
	}
}
