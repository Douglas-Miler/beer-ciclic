package br.com.ciclic.beer_webservice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ciclic.beer_webservice.model.ListOfTracks;

@Service
public class RestTemplateService {

	private RestTemplate restTemplate;
	
	public RestTemplateService() {
		this.restTemplate = new RestTemplateBuilder().build();
	}
	
	public ListOfTracks makeRequestToTrackUrlEndpoint(String url, String token) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer ".concat(token));
		
		ResponseEntity<ListOfTracks> responseEntity = this.restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<ListOfTracks>(httpHeaders), ListOfTracks.class);
		
		return responseEntity.getBody();
	}
}
