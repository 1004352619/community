package communtiy.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dto.QuestionDTO;
import mapper.QuestionMapper;
import service.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/question/{id}")
	public String question(@PathVariable(name = "id")Long id,
							Model model) {
		QuestionDTO questionDTO = questionService.getById(id);
		//累加阅读数
		questionService.incView(id);
		model.addAttribute("question", questionDTO);
		
		return "question";
	}
}
