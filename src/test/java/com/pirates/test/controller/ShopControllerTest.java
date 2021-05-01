package com.pirates.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pirates.test.dto.HolidaysDto;
import com.pirates.test.dto.ShopDto;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("해적수산 점포 추가 API")
	@Order(1)
	void addShopTest1() throws Exception {
		ClassPathResource resource = new ClassPathResource("shopLevel1.json");
		ShopDto storeDto = objectMapper.readValue(resource.getFile(), ShopDto.class);
		String jsonStr = objectMapper.writeValueAsString(storeDto);

		mockMvc.perform(put("/api/shop")
						.content(jsonStr)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
		
	}
	
	@Test
	@DisplayName("인어수산 점포 추가 API")
	@Order(2)
	void addShopTest2() throws Exception {
		ClassPathResource resource = new ClassPathResource("shopLevel2.json");
		ShopDto storeDto = objectMapper.readValue(resource.getFile(), ShopDto.class);
		String jsonStr = objectMapper.writeValueAsString(storeDto);

		mockMvc.perform(put("/api/shop")
						.content(jsonStr)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
		
	}
	
	@Test
	@DisplayName("점포 휴무일 등록 API")
	@Order(3)
	void addShopHolidaysTest() throws Exception {
		ClassPathResource resource = new ClassPathResource("holidays.json");
		HolidaysDto holidaysDto = objectMapper.readValue(resource.getFile(), HolidaysDto.class);
		String jsonStr = objectMapper.writeValueAsString(holidaysDto);

		mockMvc.perform(put("/api/holidays")
						.content(jsonStr)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
	}
	
}
