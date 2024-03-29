package dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.bytebuddy.asm.Advice.This;

@Data
public class PaginationDTO {
	private List<QuestionDTO> questions;
	private boolean showPrevious;
	private boolean showFirstPage;
	private boolean showNext;
	private boolean showEndPage;
	private Integer page;
	private Integer totalPage;
	private List<Integer> pages = new ArrayList<>();
	
	public void setPagination(Integer totalCount, Integer page, Integer size) {
		
		
		if(totalCount % size == 0) {
			totalPage = totalCount / size;
		}else {
			totalPage = totalCount / size +1;
		}
		if(page > totalPage) {
			page = totalPage;
		}else if(page <1) {
			page=1;
		}
/*		Integer offset = size*(page-1);
		if(page==0) {
			offset=0;
		}*/
		this.page=page;
		pages.add(page);
		for(int i=1;i<=3;i++) {
			if(page-i>0) {
				pages.add(0,page-i);
			}
			
			if(page + i <= totalPage) {
				pages.add(page+i);
			}
		}
		
		//是否显示上一页
		if(page == 1 || totalCount ==0) {
			showPrevious=false;
		}else {
			showPrevious=true;
		}
		//是否显示下一页
		if(page == totalPage || totalCount ==0) {
			showNext=false;
		}else {
			showNext=true;
		}
		
		//是否显示第一页
		if(pages.contains(1) || totalCount ==0) {
			showFirstPage=false;
		}else {
			showFirstPage=true;
		}
		
		//是否显示最后一页
		if(pages.contains(totalPage)|| totalCount ==0) {
			showEndPage=false;
		}else {
			showEndPage=true;
		}
		
		
	}
}
