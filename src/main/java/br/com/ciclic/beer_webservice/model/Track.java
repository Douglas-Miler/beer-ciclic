package br.com.ciclic.beer_webservice.model;

import java.util.List;

public class Track {

	private String name;
	private List<Artists> artists;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Artists> getArtists() {
		return artists;
	}

	public void setArtists(List<Artists> artists) {
		this.artists = artists;
	}

}
