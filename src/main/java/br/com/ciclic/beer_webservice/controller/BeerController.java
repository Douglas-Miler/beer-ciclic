package br.com.ciclic.beer_webservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ciclic.beer_webservice.model.Beer;
import br.com.ciclic.beer_webservice.model.BeerDto;
import br.com.ciclic.beer_webservice.model.PlaylistAndBeerStyle;
import br.com.ciclic.beer_webservice.model.ResponseInformation;
import br.com.ciclic.beer_webservice.repository.BeerRepository;
import br.com.ciclic.beer_webservice.service.BeerService;
import br.com.ciclic.beer_webservice.service.PlaylistAndBeerStyleService;

@RestController
@RequestMapping("/v1/beer")
public class BeerController {

	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private PlaylistAndBeerStyleService playlistAndBeerStyleService;

	@Autowired
	private BeerService beerService;

	@GetMapping("/all")
	public List<Beer> getAllBeers() {
		return beerRepository.findAll();
	}

	@GetMapping("/spotify/temperature/{temperatureAverage}")
	public PlaylistAndBeerStyle getPlaylistAndBeerStyle(@PathVariable("temperatureAverage") int temperature) throws Exception {
		return playlistAndBeerStyleService.getPlaylistAndBeerStyle(temperature);
	}

	@GetMapping("/temperature/{temperatureAverage}")
	public List<Beer> getBeerByTemperatureAverage(@PathVariable("temperatureAverage") int temperature) throws Exception {
		return this.beerRepository.findByTemperatureAverage(temperature);
	}
	
	@GetMapping("/id/{id}")
	public Beer getBeerById(@PathVariable("id") long id) throws Exception {
		Optional<Beer> optionalBeer = this.beerRepository.findById(id);
		
		if(optionalBeer.isPresent())
			return optionalBeer.get();
		
		throw new EmptyResultDataAccessException(1);
	}

	@PostMapping("/create")
	public Beer create(@RequestBody(required = true) BeerDto beerDto) {
		return this.beerRepository.save(new Beer(beerDto, this.beerService.getTemperatureAverage(beerDto)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseInformation> delete(@PathVariable("id") long id) {
		this.beerRepository.deleteById(id);
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.OK,
						new StringBuilder("Beer with ID ").append(id).append(" has been removed!").toString()),
				HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public Beer update(@PathVariable("id") long id, @RequestBody(required = true) BeerDto beerDto) {
		return this.beerService.update(id, beerDto);
	}
}
