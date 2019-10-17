package dto;

import lombok.Data;
import mapper.UserMapper;
import model.User;

@Data
public class QuestionDTO {

	private Integer id;
	private String title;
	private String description;
	private String tag;
	private Long gmtCreate;
	private Long gmtModified;
	private Integer creator;
	private Integer viewCount;
	private Integer commentCount;
	private Integer likeCount;
	private User user;
	
	
}
