package br.com.ciclic.beer_webservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalUrls {

	@JsonProperty("spotify")
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
