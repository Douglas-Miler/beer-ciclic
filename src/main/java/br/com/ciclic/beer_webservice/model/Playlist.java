package br.com.ciclic.beer_webservice.model;

import java.util.List;

public class Playlist {

	private List<Items> items;
	private String name;

	public Playlist(List<Items> items, String name) {
		this.items = items;
		this.name = name;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
