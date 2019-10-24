package community.exception;

public class CustomizeException extends RuntimeException{
	private String message;
	private Integer code;

	public CustomizeException(ICustomizeErrorCode code) {
		this.code = code.getCode();
		this.message = code.getMessage();
	}
	
/*	public CustomizeException(String message) {
		this.message = message;
	}*/
	@Override
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	
	

}
