package model;


import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@ExcelTarget("EasyPOIModel")
public class EasyPOIModel {

	@Excel(name="序号",type=10,width=30)
	private String index;
	
	@Excel(name="班级")
	private String className;
	
	@ExcelEntity(name="用户信息")
	private User1 userInfo;
	
	
	
	public String getIndex() {
		return index;
	}



	public void setIndex(String index) {
		this.index = index;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public User1 getUserInfo() {
		return userInfo;
	}



	public void setUserInfo(User1 userInfo) {
		this.userInfo = userInfo;
	}

	public EasyPOIModel() {

	
	}

	
	@Override
	public String toString() {
		return "EasyPOIModel [index=" + index + ", className=" + className + ", userInfo=" + userInfo + "]";
	}



	public EasyPOIModel(String index, String className, User1 user) {
		this.index=index;
		this.className=className;
		this.userInfo=user;
	}
}
