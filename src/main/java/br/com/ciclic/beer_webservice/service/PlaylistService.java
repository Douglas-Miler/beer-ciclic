package br.com.ciclic.beer_webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import br.com.ciclic.beer_webservice.exception.BeerStyleNotMatchedAnyPlaylistException;
import br.com.ciclic.beer_webservice.exception.InternalServerErrorException;
import br.com.ciclic.beer_webservice.exception.NoPlaylistsFoundException;
import br.com.ciclic.beer_webservice.model.Playlist;

@Service
public class PlaylistService {

	@Autowired
	private SpotifyService spotifyService;

	@Autowired
	private TrackService trackService;

	public Playlist getPlaylist(String beerType) throws InternalServerErrorException, NoPlaylistsFoundException, BeerStyleNotMatchedAnyPlaylistException {

		String playlistName = "";
		PlaylistSimplified[] items = this.spotifyService.getUserPlaylists().getItems();

		PlaylistSimplified playlistSimplified = null;

		for (PlaylistSimplified playlistSimplifiedItem : items) {
			playlistName = playlistSimplifiedItem.getName();
			if (playlistName.toLowerCase().contains(beerType.toLowerCase())) {
				playlistSimplified = playlistSimplifiedItem;
				break;
			}
		}

		return new Playlist(this.trackService.getListOfTracks(playlistSimplified).getItems(), playlistName);
	}

}