package br.com.ciclic.beer_webservice.model;

public class PlaylistAndBeerStyle {

	private String beerStyle;
	private ListOfTracks playlist;

	public PlaylistAndBeerStyle() {

	}

	public PlaylistAndBeerStyle(String beerStyle, ListOfTracks playlist) {
		this.beerStyle = beerStyle;
		this.playlist = playlist;
	}

	public String getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}

	public ListOfTracks getPlaylist() {
		return playlist;
	}

	public void setPlaylist(ListOfTracks playlist) {
		this.playlist = playlist;
	}

}
