package communtiy.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import dto.CommentDTO;
import mapper.CommentMapper;
import model.Comment;

@Controller
public class CommentController {

	@Autowired
	private CommentMapper commentMapper;
	
	@ResponseBody
	@RequestMapping(value = "/comment",method= RequestMethod.POST)
	public Object post(@RequestBody CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setParentId(commentDTO.getParentId());
		comment.setContent(commentDTO.getContent());
		comment.setType(commentDTO.getType());
		comment.setGmtCreate(System.currentTimeMillis());
		comment.setGmtModified(System.currentTimeMillis());
		comment.setLikeCount(0L);
		comment.setCommentator(1);
		commentMapper.insert(comment);
		
		HashMap<Object, Object> objectHashMap = new HashMap<>();
		objectHashMap.put("message", "成功hah ");
		return objectHashMap;
	}
}
