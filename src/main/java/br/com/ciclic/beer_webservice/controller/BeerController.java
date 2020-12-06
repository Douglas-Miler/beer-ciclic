package br.com.ciclic.beer_webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ciclic.beer_webservice.exception.BeerStyleNotMatchedAnyPlaylistException;
import br.com.ciclic.beer_webservice.exception.InternalServerErrorException;
import br.com.ciclic.beer_webservice.exception.NoPlaylistsFoundException;
import br.com.ciclic.beer_webservice.model.Beer;
import br.com.ciclic.beer_webservice.model.PlaylistAndBeerStyle;
import br.com.ciclic.beer_webservice.repository.BeerRepository;
import br.com.ciclic.beer_webservice.service.PlaylistAndBeerStyleService;

@RestController
@RequestMapping("/v1/beer")
public class BeerController {

	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private PlaylistAndBeerStyleService playlistAndBeerStyleService;
	
	@GetMapping("/all")
	public List<Beer> getAllBeers() {
		return beerRepository.findAll();
	}
	
	@GetMapping("/{temperature}")
	public PlaylistAndBeerStyle getPlaylistAndBeerStyle(@PathVariable("temperature") int temperature) throws InternalServerErrorException, NoPlaylistsFoundException, BeerStyleNotMatchedAnyPlaylistException {
		return playlistAndBeerStyleService.getPlaylistAndBeerStyle(temperature);
	}
	
}
