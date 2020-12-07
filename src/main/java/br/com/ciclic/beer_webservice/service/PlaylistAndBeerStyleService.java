package br.com.ciclic.beer_webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ciclic.beer_webservice.model.PlaylistAndBeerStyle;

@Service
public class PlaylistAndBeerStyleService {

	@Autowired
	private BeerService beerService;
	
	@Autowired
	private PlaylistService playlistService;
	
	public PlaylistAndBeerStyle getPlaylistAndBeerStyle(int temperature) throws Exception {
		String beerType = this.beerService.getBeerByTemperatureClosestToBeerAverageTemperature(temperature).getType();
		
		return new PlaylistAndBeerStyle(beerType, this.playlistService.getPlaylist(beerType));
	}

}
