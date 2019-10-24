package enmus;

public enum CommentTypeEnmu {
	QUESTION(1),
	COMMENT(2);
	private Integer type;
	
	
	
	public Integer getType() {
		return type;
	}

	CommentTypeEnmu(Integer type){
		this.type = type;
	}

	public static boolean isExist(Integer type) {
		for(CommentTypeEnmu commentTypeEnmu : CommentTypeEnmu.values()){
			if(commentTypeEnmu.getType() == type) {
				return true;
			}
		}
		return false;
	}
}
