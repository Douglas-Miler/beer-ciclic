package br.com.ciclic.beer_webservice;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BeerWebserviceApplication {

	public static void main(String[] args) throws ParseException, SpotifyWebApiException, IOException {
		SpringApplication.run(BeerWebserviceApplication.class, args);
	}

}
