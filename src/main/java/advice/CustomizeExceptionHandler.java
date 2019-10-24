package advice;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import community.exception.CustomizeErrorCode;
import community.exception.CustomizeException;
import dto.ResultDTO;

@ControllerAdvice
public class CustomizeExceptionHandler {

	  @ExceptionHandler(Exception.class)
	  ModelAndView handle(Throwable ex,Model model,HttpServletRequest request,HttpServletResponse response) {
		  String contentType = request.getContentType();
		  System.out.println("我是"+contentType+contentType);
		  if("application/json".equals(contentType)) {
			  ResultDTO resultDTO;
			  //返回json
			  if(ex instanceof CustomizeException) {
				  	resultDTO= ResultDTO.errofOf((CustomizeException)ex);
		        }else {
		        	resultDTO= ResultDTO.errofOf((CustomizeErrorCode.SYS_ERROR));
				}
			  	try {
			  		response.setContentType("appliction/json");
			  		response.setStatus(200);
			  		response.setCharacterEncoding("utf-8");
			  		PrintWriter writer = response.getWriter();
			  		writer.write(JSON.toJSONString(resultDTO));
			  		writer.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			  	return null;
			  
		  }else {
			  //错误页面跳转
			  if(ex instanceof CustomizeException) {
		        	model.addAttribute("message",ex.getMessage());
		        }else {
		        	model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
				}
		        return new ModelAndView("error");
		}
		  
	        
	    }
}
