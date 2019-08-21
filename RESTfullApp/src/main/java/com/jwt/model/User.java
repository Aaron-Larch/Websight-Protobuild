package com.jwt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
public class User {
	private String id;
	private String name;
	private int age;
	public String getId() {
		return id;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	
	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}
}
