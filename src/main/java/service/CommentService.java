package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import community.enmus.CommentTypeEnmu;
import community.exception.CustomizeErrorCode;
import community.exception.CustomizeException;
import mapper.CommentMapper;
import mapper.QuestionExMapper;
import mapper.QuestionMapper;
import model.Comment;
import model.Question;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuestionExMapper questionExMapper;
	
	public void insert(Comment comment) {
		if(comment.getParentId() == null || comment.getParentId() == 0) {
			throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND); 
		}
		
		if(comment.getType() == null || !CommentTypeEnmu.isExist(comment.getType())) {
			throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
		}
		
		if(comment.getType() == CommentTypeEnmu.COMMENT.getType()) {
			//回复评论
			Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
			if(dbComment == null) {
				throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
			commentMapper.insert(comment);
		}else {
			Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
			if(question == null) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			commentMapper.insert(comment);
			question.setCommentCount(1);
			questionExMapper.incCommentCount(question);
		}
		
	}
	
}
