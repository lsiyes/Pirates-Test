package com.pirates.test.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("shop")
public class ShopDto {

	private int id;
	private String name;
	private String owner;
	private String description;
	private int level;
	private String address;
	private String phone;
	private List<TimeDto> businessTimes;

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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<TimeDto> getBusinessTimes() {
		return businessTimes;
	}
	public void setBusinessTimes(List<TimeDto> businessTimes) {
		this.businessTimes = businessTimes;
	}
	@Override
	public String toString() {
		return "ShopDto [id=" + id + ", name=" + name + ", owner=" + owner + ", description=" + description + ", level="
				+ level + ", address=" + address + ", phone=" + phone + ", businessTimes=" + businessTimes + "]";
	}
	
}
