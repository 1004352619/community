package model;

import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@ExcelTarget("user")
public class User1 {
	
	@Excel(name="姓名")
	private String name;
	
	@Excel(name="性别")
	private String sex;
	
	@Excel(name="年龄")
	private Integer age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public User1() {
	}
	public User1(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	@Override
	public String toString() {
		return "User1 [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	
	
	
}
