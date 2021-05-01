package com.pirates.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pirates.test.dto.HolidaysDto;
import com.pirates.test.dto.ShopDto;
import com.pirates.test.dto.ShopListDto;
import com.pirates.test.dto.TimeDto;

@Mapper
public interface ShopDao {
	
	void saveShop(ShopDto shopDto);
	void saveTime(TimeDto timeDto);
	void saveHolidys(HolidaysDto holidaysDto);
	List<ShopListDto> findAllShopOrderByLevel();
	List<TimeDto> findAllShopTimes();
	List<HolidaysDto> findAllShopHolidays();
	List<ShopDto> findShopById(int id);
	List<TimeDto> findShopTimesByDays(TimeDto timeDto);
	void deleteShopById(ShopDto shopDto);
}
