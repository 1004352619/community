package community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
	QUESTION_NOT_FOUND(2001,"你找的问题不在了，换个试试!"),
	TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
	NO_LOGIN(2003,"当前操作需要登录,请登录后重试"),
	SYS_ERROR(2004,"服务器冒烟了，要不然你稍后试试"),
	TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
	COMMENT_NOT_FOUND(2006,"你操作的评论不存在,请换个试试");
	
	private Integer code;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	private CustomizeErrorCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public Integer getCode() {
		
		return code;
	}
	
	
	
	
}
