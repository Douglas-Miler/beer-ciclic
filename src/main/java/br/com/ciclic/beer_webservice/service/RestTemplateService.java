package br.com.ciclic.beer_webservice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ciclic.beer_webservice.model.ListOfTracks;

@Service
public class RestTemplateService {

	private RestTemplate restTemplate;
	
	public RestTemplateService() {
		this.restTemplate = new RestTemplateBuilder().build();
	}
	
	public ListOfTracks makeRequestToTrackUrlEndpoint(String url) {
		return this.restTemplate.getForObject(url, ListOfTracks.class);
	}
}
