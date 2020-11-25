package com.ssafy.happyhouse.model.dto;

public class SchoolInfoDto {
	  private String school_id; 
	  private String school_name; 
	  private String school_grade; 
	  private String school_jibun; 
	  private String school_lat; 
	  private String school_lng;
	  
	public SchoolInfoDto(String school_id, String school_name, String school_grade, String school_jibun,
			String school_lat, String school_lng) {
		this.school_id = school_id;
		this.school_name = school_name;
		this.school_grade = school_grade;
		this.school_jibun = school_jibun;
		this.school_lat = school_lat;
		this.school_lng = school_lng;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getSchool_grade() {
		return school_grade;
	}

	public void setSchool_grade(String school_grade) {
		this.school_grade = school_grade;
	}

	public String getSchool_jibun() {
		return school_jibun;
	}

	public void setSchool_jibun(String school_jibun) {
		this.school_jibun = school_jibun;
	}

	public String getSchool_lat() {
		return school_lat;
	}

	public void setSchool_lat(String school_lat) {
		this.school_lat = school_lat;
	}

	public String getSchool_lng() {
		return school_lng;
	}

	public void setSchool_lng(String school_lng) {
		this.school_lng = school_lng;
	} 
	  
	  
}
