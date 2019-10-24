package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import community.exception.CustomizeErrorCode;
import community.exception.CustomizeException;
import dto.PaginationDTO;
import dto.QuestionDTO;
import mapper.QuestionExMapper;
import mapper.QuestionMapper;
import mapper.UserMapper;
import model.Question;
import model.QuestionExample;
import model.User;

@Service
public class QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuestionExMapper questionExMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	
	public PaginationDTO list(Integer page, Integer size) {
		PaginationDTO paginationDTO = new PaginationDTO();
		Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
		paginationDTO.setPagination(totalCount,page,size);
		
		if(page<1) {
			page=1;
		}
		if(page>paginationDTO.getTotalPage()) {
			page=paginationDTO.getTotalPage();
		}
		
		//size*(page-1)
		Integer offset = size*(page-1);
		if(page==0) {
			offset=0;
		}
		List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset,size));
		List<QuestionDTO> questionDTOList = new ArrayList<>();
		

		for (Question question : questions) {
			User user = userMapper.selectByPrimaryKey(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);

		
		return paginationDTO;
	
	}


	public PaginationDTO list(Long userId, Integer page, Integer size) {
		PaginationDTO paginationDTO = new PaginationDTO();
		
		QuestionExample questionExample = new QuestionExample();
		questionExample.createCriteria().andCreatorEqualTo(userId);
		Integer totalCount = (int) questionMapper.countByExample(questionExample);

		paginationDTO.setPagination(totalCount,page,size);
		
		if(page<1) {
			page=1;
		}
		if(page>paginationDTO.getTotalPage()) {
			page=paginationDTO.getTotalPage();
		}
		
		//size*(page-1)
		Integer offset = size*(page-1);
		if(page==0) {
			offset=0;
		}
		
		QuestionExample example = new QuestionExample();
		example.createCriteria().andCreatorEqualTo(userId);
		List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset,size));

		List<QuestionDTO> questionDTOList = new ArrayList<>();
		

		for (Question question : questions) {
			User user = userMapper.selectByPrimaryKey(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);

		
		return paginationDTO;
	}


	public QuestionDTO getById(Long id) {
		Question question = questionMapper.selectByPrimaryKey(id);
		if(question == null) {
			throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}
		QuestionDTO questionDTO = new QuestionDTO();
		BeanUtils.copyProperties(question, questionDTO);
		User user = userMapper.selectByPrimaryKey(question.getCreator());
		questionDTO.setUser(user);
		return questionDTO;
	}


	public void createOrUpdate(Question question) {
		if(question.getId() == null) {
			//创建
			question.setGmtCreate(System.currentTimeMillis());
			question.setGmtModified(question.getGmtCreate());
			question.setViewCount(0);
			question.setLikeCount(0);
			question.setCommentCount(0);
			questionMapper.insert(question);
		}else {
			//更新
			Question updateQuestion = new Question();
			updateQuestion.setGmtModified(System.currentTimeMillis());
			updateQuestion.setTitle(question.getTitle());
			updateQuestion.setDescription(question.getDescription());
			updateQuestion.setTag(question.getTag());
			QuestionExample example = new QuestionExample();
			example.createCriteria().andIdEqualTo(question.getId());
			int updated = questionMapper.updateByExampleSelective(updateQuestion,example);
			if(updated != 1) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);

			}
		}
		
	}


	public void incView(Long id) {
		Question question = new Question();
		question.setId(id);
		question.setViewCount(1);
		questionExMapper.incView(question);		
	}

}
