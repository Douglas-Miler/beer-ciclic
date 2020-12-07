package br.com.ciclic.beer_webservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.ciclic.beer_webservice.model.ResponseInformation;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ResponseInformation> handleInternalServerErrorException(InternalServerErrorException exception) {
		LOGGER.error(exception.getMessage());
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoPlaylistsFoundException.class)
	public ResponseEntity<ResponseInformation> handleNoPlaylistsFoundException() {
		String exceptionCause = "There are no playlists for this user id";
		LOGGER.error(exceptionCause);
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.NOT_FOUND, exceptionCause),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BeerStyleNotMatchedAnyPlaylistException.class)
	public ResponseEntity<ResponseInformation> handleBeerStyleNotMatchedAnyPlaylistException() {
		String exceptionCause = "The beer style didn't match any playlist name";
		LOGGER.error(exceptionCause);
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.NOT_FOUND, exceptionCause),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseInformation> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		LOGGER.error(exception.getMessage());
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.BAD_REQUEST, exception.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ResponseInformation> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
		LOGGER.error(exception.getMessage());
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.BAD_REQUEST, exception.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}
