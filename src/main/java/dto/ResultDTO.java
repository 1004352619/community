package dto;

import community.exception.CustomizeErrorCode;
import community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO {
	private Integer code;
	private String message;
	
	public static ResultDTO errofOf(Integer code,String message) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(code);
		resultDTO.setMessage(message);
		return resultDTO;
	}

	public static ResultDTO errofOf(CustomizeErrorCode errorCode) {
		
		return errofOf(errorCode.getCode(),errorCode.getMessage());
	}
	public static ResultDTO okOf() {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("请求成功");
		return resultDTO;
	}

	public static ResultDTO errofOf(CustomizeException e) {
		
		return errofOf(e.getCode(),e.getMessage());
	}
}
