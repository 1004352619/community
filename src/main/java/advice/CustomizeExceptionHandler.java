package advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import exception.CustomizeException;

@ControllerAdvice
public class CustomizeExceptionHandler {

	  @ExceptionHandler(Exception.class)
	  ModelAndView handle(Throwable ex,Model model) {
		  System.out.println("哈哈");
	        if(ex instanceof CustomizeException) {
	        	model.addAttribute("message",ex.getMessage());
	        }else {
	        	System.out.println(123);
	        	model.addAttribute("message", "你找的问题不存在！");
			}
	        return new ModelAndView("error");
	    }
}
