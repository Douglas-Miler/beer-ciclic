package br.com.ciclic.beer_webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import br.com.ciclic.beer_webservice.exception.BeerStyleNotMatchedAnyPlaylistException;
import br.com.ciclic.beer_webservice.exception.InternalServerErrorException;
import br.com.ciclic.beer_webservice.exception.NoPlaylistsFoundException;
import br.com.ciclic.beer_webservice.model.ListOfTracks;

@Service
public class TrackService {

	@Autowired
	private SpotifyService spotifyService;
	
	public ListOfTracks getListOfTracks(PlaylistSimplified playlistSimplified) throws InternalServerErrorException, NoPlaylistsFoundException, BeerStyleNotMatchedAnyPlaylistException {

		if (playlistSimplified == null)
			throw new BeerStyleNotMatchedAnyPlaylistException();
		
		return this.spotifyService.getPlaylistTracks(playlistSimplified);
	}
	
}
