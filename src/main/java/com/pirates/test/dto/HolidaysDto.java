package com.pirates.test.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("holiday")
public class HolidaysDto {

	private int id;
	private List<String> holidays;
	private String holiday;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getHolidays() {
		return holidays;
	}
	public void setHolidays(List<String> holidays) {
		this.holidays = holidays;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	@Override
	public String toString() {
		return "HolidaysDto [id=" + id + ", holidays=" + holidays + ", holiday=" + holiday + "]";
	}
	
}
