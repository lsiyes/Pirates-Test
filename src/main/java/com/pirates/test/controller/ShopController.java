package com.pirates.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pirates.test.dto.HolidaysDto;
import com.pirates.test.dto.ShopDto;
import com.pirates.test.dto.ShopListDto;
import com.pirates.test.services.ShopService;

@RestController
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	private final ShopService shopService;

	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@GetMapping("/api/shop")
	public ResponseEntity<List<ShopListDto>> findShop() {
		List<ShopListDto> shopList = shopService.findAllShopOrderByLevel();
		
		return new ResponseEntity<List<ShopListDto>>(shopList, HttpStatus.OK);
	}
	
	@PutMapping("/api/shop")
	public ResponseEntity<String> addShop(@RequestBody ShopDto shopDto) {
		try {
			shopService.saveShop(shopDto);
		} catch (RuntimeException e) {
			// TODO: handle exception
			logger.info("Transaction Rollback b/c >>>>>> ", e);
			return new ResponseEntity<String>("Putting shop data has failed", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok("Putting shop data is completed");
	}
	
	@DeleteMapping("/api/shop")
	public ResponseEntity<String> deleteShop(@RequestBody ShopDto shopDto) {
		shopService.deleteShopById(shopDto);
		
		return ResponseEntity.ok("Deleting shop data is completed");
	}
	
	@PutMapping("/api/holidays")
	public ResponseEntity<String> addShopHolidays(@RequestBody HolidaysDto holidaysDto) {
		shopService.saveHolidys(holidaysDto);
		
		return ResponseEntity.ok("Putting shop's holidays is completed");
	} 
	
	@GetMapping("/api/shopDetail")
	public ResponseEntity<List<ShopDto>> findShopDetail(@RequestParam int id) {
		List<ShopDto> shopDetailList = shopService.findShopById(id);
		
		return new ResponseEntity<List<ShopDto>>(shopDetailList, HttpStatus.OK);
	}
	

	
}
