package communtiy.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.PaginationDTO;
import dto.QuestionDTO;
import mapper.QuestionMapper;
import mapper.UserMapper;
import model.Question;
import model.User;
import service.QuestionService;

@Controller
public class IndexController {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/")
	public String index(Model model,
						@RequestParam(name = "page", defaultValue = "1") Integer page,
						@RequestParam(name =  "size", defaultValue = "5") Integer size) {
		PaginationDTO pagination = questionService.list(page,size);
		model.addAttribute("pagination", pagination);
		
		return "index";
	}

}
