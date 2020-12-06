package br.com.ciclic.beer_webservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(InternalServerErrorException.class)
    public void handleInternalServerError(InternalServerErrorException exception) {
		LOGGER.error(exception.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoPlaylistsFoundException.class)
	public void handleNoPlaylistsFound() {
		LOGGER.error("There are no playlists for this user id");
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BeerStyleNotMatchedAnyPlaylistException.class)
	public void handleBeerStyleNotMatchedAnyPlaylist() {
		LOGGER.error("The beer style didn't match any playlist name");
	}
}