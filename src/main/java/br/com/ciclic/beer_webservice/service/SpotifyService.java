package br.com.ciclic.beer_webservice.service;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.PlaylistTracksInformation;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import br.com.ciclic.beer_webservice.exception.InternalServerErrorException;
import br.com.ciclic.beer_webservice.model.ListOfTracks;

@Service
public class SpotifyService {

	private static final String REQUEST_PARAMETERS_FOR_GET_TRACKS = "?fields=items(track(name%2Cartists(external_urls%2Cname)))";

	private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyService.class);
	
	@Autowired
	private RestTemplateService restTemplateService;
	
	@Value("${client.id}")
	private String clientId;

	@Value("${client.secret}")
	private String clientSecret;

	@Value("${user.id}")
	private String userId;
	
	private SpotifyApi spotifyApi;
	
	public SpotifyService() {
		this.spotifyApi = new SpotifyApi.Builder()
							.setClientId(this.clientId)
							.setClientSecret(this.clientSecret)
							.build();
	}
	
	public ListOfTracks getPlaylistTracks(PlaylistSimplified playlist) {
		PlaylistTracksInformation tracks = playlist.getTracks();
		
		ListOfTracks listOfTracks = this.restTemplateService.makeRequestToTrackUrlEndpoint(
				tracks.getHref().concat(SpotifyService.REQUEST_PARAMETERS_FOR_GET_TRACKS));
		
		SpotifyService.LOGGER.info(listOfTracks.toString());
		
		return listOfTracks;
	}

	public Paging<PlaylistSimplified> getUserPlaylists () throws InternalServerErrorException {
		
		Paging<PlaylistSimplified> playlistSimplified = null;
		
		try {
			this.setAccessToken();
		
			GetListOfUsersPlaylistsRequest listOfUsersPlaylistsRequest = this.spotifyApi
					.getListOfUsersPlaylists(this.userId).build();
		
			playlistSimplified = listOfUsersPlaylistsRequest.execute();
		} catch(ParseException | SpotifyWebApiException | IOException e) {
			throw new InternalServerErrorException(e);
		}

		return playlistSimplified;
	}

	private void setAccessToken() throws ParseException, SpotifyWebApiException, IOException {
		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		
		ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		
		spotifyApi.setAccessToken(clientCredentials.getAccessToken());
	}
}
