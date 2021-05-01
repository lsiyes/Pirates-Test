package com.pirates.test.dto;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Alias("shopList")
public class ShopListDto {

	@JsonIgnore
	private int id;
	private String name;
	private String description;
	private int level;
	private String businessStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	@Override
	public String toString() {
		return "ShopListDto [id=" + id + ", name=" + name + ", description=" + description + ", level=" + level
				+ ", businessStatus=" + businessStatus + "]";
	}
	
}
