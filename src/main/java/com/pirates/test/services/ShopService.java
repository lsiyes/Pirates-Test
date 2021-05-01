package com.pirates.test.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.pirates.test.dao.ShopDao;
import com.pirates.test.dto.HolidaysDto;
import com.pirates.test.dto.ShopDto;
import com.pirates.test.dto.ShopListDto;
import com.pirates.test.dto.TimeDto;

@Transactional
@Service
public class ShopService {

	@Autowired
	private ShopDao storeDao;
	
	public void saveShop(ShopDto shopDto) throws RuntimeException {
		storeDao.saveShop(shopDto);
		
		Optional.of(shopDto.getBusinessTimes()).ifPresent(timeList -> {
			timeList.forEach(timeListItem -> {
				if (timeListItem.getOpen().equals(timeListItem.getClose())) {
					throw new RuntimeException("영업 시작 시간, 종료시간은 같을 수 없음");
				}
				timeListItem.setId(shopDto.getId());
				saveTime(timeListItem);
			});
		});
	}

	public void saveTime(TimeDto timeDto) {
		storeDao.saveTime(timeDto);
	}
	
	public void saveHolidys(HolidaysDto holidaysDto) {
		Optional.of(holidaysDto.getHolidays()).ifPresent(holidayList -> {
			holidayList.forEach(holiday -> {
				holidaysDto.setHoliday(holiday);
				storeDao.saveHolidys(holidaysDto);
			});
		});
	}
	
	public List<ShopListDto> findAllShopOrderByLevel() {
		List<ShopListDto> shopList = Optional.of(storeDao.findAllShopOrderByLevel()).get();
		List<TimeDto> allTimeList = Optional.of(storeDao.findAllShopTimes()).get();
		
		allTimeList = getWorkStatus(allTimeList);
		for (ShopListDto sl:shopList) {
			for (TimeDto td:allTimeList) {
				if (sl.getId() == td.getId()) {
					sl.setBusinessStatus(td.getStatus());
				}
			}
		}
		
		return shopList;
	}
	
	public List<TimeDto> findAllShopTimes() {
		return storeDao.findAllShopTimes();
	}
	
	public List<HolidaysDto> findAllShopHolidays() {
		return storeDao.findAllShopHolidays();
	}
	
	public List<ShopDto> findShopById(int id) {
		List<ShopDto> shopDetailList = Optional.of(storeDao.findShopById(id)).get();
		List<String> dayList = new ArrayList<String>();
		TimeDto timeDto = new TimeDto();
		LocalDate currentDate = LocalDate.now().minusDays(1); // 현재날짜 조정가능
		DayOfWeek day = currentDate.getDayOfWeek(); 
		
		timeDto.setId(id);
		for (int i=1; i<=3; i++) {
			dayList.add(StringUtils.capitalize(day.minus(i-1).toString().toLowerCase()));
		}
		timeDto.setDayList(dayList);
		
		List<TimeDto> timeList = Optional.of(findShopTimesByDays(timeDto)).get();
		timeList = getWorkStatus(timeList);
		for (ShopDto sd:shopDetailList) {
			sd.setBusinessTimes(timeList);
		}
		
		return shopDetailList;
	}
	
	public List<TimeDto> findShopTimesByDays(TimeDto timeDto) {
		return storeDao.findShopTimesByDays(timeDto);
	}
	
	public void deleteShopById(ShopDto shopDto) {
		storeDao.deleteShopById(shopDto);
	}
	
	public List<TimeDto> getWorkStatus(List<TimeDto> ltd) {
		Optional.of(storeDao.findAllShopHolidays()).ifPresent(holidays -> {
			LocalDate currentDate = LocalDate.now().minusDays(1); // 현재날짜 조정가능
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime currentTime = LocalTime.parse(LocalTime.now().format(formatter));
			LocalTime openTime;
			LocalTime closeTime;

			String today = currentDate.getDayOfWeek().toString();
			String tempTime = LocalTime.of(23, 59).format(formatter);
			String dayUpperCase = "";
			int openCompare = 0;
			int closeCompare = 0;
			
			for (TimeDto td:ltd) { // td.getStatus() == null
				dayUpperCase = td.getDay().toUpperCase();
				
				if ("SATURDAY".equals(today) || "SUNDAY".equals(today)) {
					td.setStatus("HOLIDAY");
				} else {
					if (today.equals(dayUpperCase)) {
						openTime = LocalTime.parse(td.getOpen(), formatter);
						// 24:00 -> 00:00 치환 방지
						closeTime = "24:00".equals(td.getClose())?LocalTime.parse(tempTime)
																 :LocalTime.parse(td.getClose(), formatter);
						openCompare = currentTime.compareTo(openTime);
						closeCompare = currentTime.compareTo(closeTime);
						
						if ((openCompare > 0 || openCompare == 0) && (closeCompare < 0 || closeCompare == 0)) { // open <= 현재시간 <= close
							td.setStatus("OPEN");
						} else if(currentTime.isBefore(openTime) || currentTime.isAfter(closeTime)) { // open > 현재시간 ,  close < 현재시간
							td.setStatus("CLOSE");
						}
						
						holidays.forEach(hd -> {
							if (td.getId() == hd.getId() && hd.getHoliday().equals(currentDate.toString())) { // api로 저장한 휴일
								td.setStatus("HOLIDAY");
							}
						});
					} else {
						td.setStatus("CLOSE");
						holidays.forEach(hd -> {
							String holiday = LocalDate.parse(hd.getHoliday()).getDayOfWeek().toString();
							if (td.getDay().toUpperCase().equals(holiday)) td.setStatus("HOLIDAY");
						});
					}
				}
			}
		});
		
		return ltd;
	}
	
}
