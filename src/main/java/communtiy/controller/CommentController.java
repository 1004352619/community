package communtiy.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import community.exception.CustomizeErrorCode;
import dto.CommentDTO;
import dto.ResultDTO;
import mapper.CommentMapper;
import model.Comment;
import model.User;
import service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping(value = "/comment",method = RequestMethod.POST)
	public Object post(@RequestBody CommentDTO commentDTO,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			return ResultDTO.errofOf(CustomizeErrorCode.NO_LOGIN);
		}
		
		Comment comment = new Comment();
		comment.setParentId(commentDTO.getParentId());
		comment.setContent(commentDTO.getContent());
		comment.setType(commentDTO.getType());
		comment.setGmtCreate(System.currentTimeMillis());
		comment.setGmtModified(System.currentTimeMillis());
		comment.setLikeCount(0L);
		comment.setCommentator(user.getId());
		commentService.insert(comment);
		return ResultDTO.okOf();
	}
}
