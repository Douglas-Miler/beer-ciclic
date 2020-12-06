package br.com.ciclic.beer_webservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artists {

	private String name;
	
	@JsonProperty(value = "external_urls")
	private ExternalUrls externalUrls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExternalUrls getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(ExternalUrls externalUrls) {
		this.externalUrls = externalUrls;
	}

}
