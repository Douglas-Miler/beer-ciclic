package br.com.ciclic.beer_webservice.model;

public class PlaylistAndBeerStyle {

	private String beerStyle;
	private Playlist playlist;

	public PlaylistAndBeerStyle() {

	}

	public PlaylistAndBeerStyle(String beerStyle, Playlist playlist) {
		this.beerStyle = beerStyle;
		this.playlist = playlist;
	}

	public String getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

}
