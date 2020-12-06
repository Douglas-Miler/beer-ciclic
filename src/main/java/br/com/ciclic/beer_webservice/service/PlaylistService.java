package br.com.ciclic.beer_webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import br.com.ciclic.beer_webservice.exception.InternalServerErrorException;
import br.com.ciclic.beer_webservice.model.ListOfTracks;

@Service
public class PlaylistService {

	@Autowired
	private SpotifyService spotifyService;
	
	public ListOfTracks getListOfTracks(String beerType) throws InternalServerErrorException {

		Paging<PlaylistSimplified> playlists = this.spotifyService.getUserPlaylists();
		
		PlaylistSimplified[] items = playlists.getItems();
		
		PlaylistSimplified playlistSimplified = null;
		
		for (PlaylistSimplified playlistSimplifiedItem : items) {
			if(playlistSimplifiedItem.getName().contains(beerType)) {
				playlistSimplified = playlistSimplifiedItem;
				break;
			}
			
		}
		
		return this.spotifyService.getPlaylistTracks(playlistSimplified);
	}

}