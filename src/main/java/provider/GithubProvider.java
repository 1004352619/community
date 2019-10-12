package provider;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import dto.accessTokenDTO;
import dto.githubUser;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.*;

@Component
public class GithubProvider {
	
	public String getAccessToken(accessTokenDTO accesstokenDTO) {
		
		MediaType mediaType= MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		  RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstokenDTO));
		  Request request = new Request.Builder()
		      .url("https://github.com/login/oauth/access_token")
		      .post(body)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
			  String string =response.body().string();
			  String token=string.split("&")[0].split("=")[1];
			  return token;	  
		  } catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public githubUser getUser(String accessToken) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url("https://api.github.com/user?access_token="+accessToken)
			      .build();
		try {
			Response response = client.newCall(request).execute();
			String string =response.body().string();
			githubUser githubUser =JSON.parseObject(string,githubUser.class);
			return githubUser;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}