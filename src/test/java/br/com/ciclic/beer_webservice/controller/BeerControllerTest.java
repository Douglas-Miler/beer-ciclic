package br.com.ciclic.beer_webservice.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ciclic.beer_webservice.exception.BeerStyleNotMatchedAnyPlaylistException;
import br.com.ciclic.beer_webservice.model.BeerDto;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private static final String DEFAULT_URI = "/v1/beer/";
	
	@Test
	public void getAllBeersTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("all")))
			.andExpect(status().isOk());
	}
	
	@Test
	public void getPlaylistAndBeerStyleTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("spotify/temperature/6")))
			.andExpect(status().isOk());
	}
	
	@Test
	public void getPlaylistAndBeerStyleWithStringTemperatureFailedTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("spotify/temperature/a")))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
	}
	
	@Test
	public void getPlaylistAndBeerStyleNoPlaylistMatchFailedTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("spotify/temperature/1")))
			.andExpect(status().isNotFound())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof BeerStyleNotMatchedAnyPlaylistException));
	}
	
	@Test
	public void getBeerByTemperatureAverageTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("temperature/6")))
			.andExpect(status().isOk());
	}
	
	@Test
	public void getBeerByIdTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("id/2")))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getBeerByIdFailedTest() throws Exception {
		this.mockMvc.perform(
				get(DEFAULT_URI.concat("id/-1")))
		.andExpect(status().isBadRequest())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof EmptyResultDataAccessException));
	}
	
	@Test
	public void createBeerTest() throws Exception {
		BeerDto beerDto = new BeerDto("Cold", -10, 2);
		
		String contentBody = this.mapper.writeValueAsString(beerDto);
		
		this.mockMvc.perform(
				post(DEFAULT_URI.concat("create"))
				.contentType("application/json")
				.content(contentBody))
			.andExpect(status().isOk());
	}
	
	@Test
	public void createBeerDataViolationFailedTest() throws Exception {
		BeerDto beerDto = new BeerDto();
		
		String contentBody = this.mapper.writeValueAsString(beerDto);
		
		this.mockMvc.perform(
				post(DEFAULT_URI.concat("create"))
				.contentType("application/json")
				.content(contentBody))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof DataIntegrityViolationException));
	}
	
	@Test
	public void createBeerWithoutRequestBodyFailedTest() throws Exception {
		this.mockMvc.perform(
				post(DEFAULT_URI.concat("create"))
				.contentType("application/json"))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
	}
	
	@Test
	public void deleteFailedTest() throws Exception {
		this.mockMvc.perform(
				delete(DEFAULT_URI.concat("-1")))
		.andExpect(status().isBadRequest())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof EmptyResultDataAccessException));
	}
	
	@Test
	public void deleteTest() throws Exception {
		this.mockMvc.perform(
				delete(DEFAULT_URI.concat("3")))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateTest() throws Exception {
		BeerDto beerDto = new BeerDto("Cold", -10, 2);
		
		String contentBody = this.mapper.writeValueAsString(beerDto); 
		
		this.mockMvc.perform(
				put(DEFAULT_URI.concat("2"))
				.contentType("application/json")
				.content(contentBody))
		.andExpect(status().isOk());
	}
	
}
