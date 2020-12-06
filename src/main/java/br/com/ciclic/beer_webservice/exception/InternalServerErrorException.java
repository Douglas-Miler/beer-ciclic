package br.com.ciclic.beer_webservice.exception;

@SuppressWarnings("serial")
public class InternalServerErrorException extends Exception {

	public InternalServerErrorException(Exception e) {
		super(e);
	}

}
