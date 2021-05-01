package com.pirates.test.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Alias("time")
public class TimeDto {
	
	private int id;
	private String day;
	private String open;
	private String close;
	private String status;
	@JsonIgnore
	private List<String> dayList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getDayList() {
		return dayList;
	}
	public void setDayList(List<String> dayList) {
		this.dayList = dayList;
	}
	@Override
	public String toString() {
		return "TimeDto [id=" + id + ", day=" + day + ", open=" + open + ", close=" + close + ", status=" + status
				+ ", dayList=" + dayList + "]";
	}
	
}
