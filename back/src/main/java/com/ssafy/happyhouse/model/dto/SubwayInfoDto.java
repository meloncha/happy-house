package com.ssafy.happyhouse.model.dto;

public class SubwayInfoDto {
	 private int subway_no;
	 private String subway_jibun;
	 private int subway_line;
	 private String subway_name;
	 private String gu_code;
	 private String dong_code;
	 
	public SubwayInfoDto(int subway_no, String subway_jibun, int subway_line, String subway_name, String gu_code,
			String dong_code) {
		this.subway_no = subway_no;
		this.subway_jibun = subway_jibun;
		this.subway_line = subway_line;
		this.subway_name = subway_name;
		this.gu_code = gu_code;
		this.dong_code = dong_code;
	}

	public int getSubway_no() {
		return subway_no;
	}

	public void setSubway_no(int subway_no) {
		this.subway_no = subway_no;
	}

	public String getSubway_jibun() {
		return subway_jibun;
	}

	public void setSubway_jibun(String subway_jibun) {
		this.subway_jibun = subway_jibun;
	}

	public int getSubway_line() {
		return subway_line;
	}

	public void setSubway_line(int subway_line) {
		this.subway_line = subway_line;
	}

	public String getSubway_name() {
		return subway_name;
	}

	public void setSubway_name(String subway_name) {
		this.subway_name = subway_name;
	}

	public String getGu_code() {
		return gu_code;
	}

	public void setGu_code(String gu_code) {
		this.gu_code = gu_code;
	}

	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}
	 
	 
}
