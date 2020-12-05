package br.com.ciclic.beer_webservice.service;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

@Service
public class SpotifyService {

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

//		PlaylistSimplified[] items = playlistSimplified.getItems();
//		
//		Map<String, String> playlists = new HashMap<>();
//		
//		for (PlaylistSimplified playlistSimplifiedItem : items) {
//			playlists.put(playlistSimplifiedItem.getId(), playlistSimplifiedItem.getName());
//		}
//		
//		
//		System.out.println(playlists);

	
	public Paging<PlaylistSimplified> getUserPlaylist () throws ParseException, SpotifyWebApiException, IOException {
		this.createConnection();
		
		GetListOfUsersPlaylistsRequest listOfUsersPlaylistsRequest = this.spotifyApi
				.getListOfUsersPlaylists(this.userId).build();

		return  listOfUsersPlaylistsRequest.execute();
	}

	private void createConnection() throws ParseException, SpotifyWebApiException, IOException {
		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		
		ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		
		spotifyApi.setAccessToken(clientCredentials.getAccessToken());
	}
}
