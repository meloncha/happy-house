package com.ssafy.happyhouse.model.dto;

public class MemberDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	public MemberDto() {}
		
	public MemberDto(String id, String pwd, String name, String email, String phone, String address, int userinfo) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.userinfo = userinfo;
	}
	public int getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(int userinfo) {
		this.userinfo = userinfo;
	}
	private int userinfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", userinfo=" + userinfo + "]";
	}
	
	
}
