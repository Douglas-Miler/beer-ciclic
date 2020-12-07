package br.com.ciclic.beer_webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/spotify/{temperature}")
	public PlaylistAndBeerStyle getPlaylistAndBeerStyle(@PathVariable("temperature") int temperature) throws Exception {
		return playlistAndBeerStyleService.getPlaylistAndBeerStyle(temperature);
	}

	@GetMapping("/{temperature}")
	public List<Beer> getBeer(@PathVariable("temperature") int temperature) throws Exception {
		return this.beerRepository.findByTemperatureAverage(temperature);
	}

	@PostMapping("/create")
	public Beer create(@RequestBody(required = true) BeerDto beerDto) {
		return this.beerRepository.save(new Beer(beerDto, this.beerService.getTemperatureAverage(beerDto)));
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseInformation> delete(@PathVariable("id") long id) {
		this.beerRepository.deleteById(id);
		return new ResponseEntity<ResponseInformation>(
				new ResponseInformation(HttpStatus.OK,
						new StringBuilder("Beer with ID ").append(id).append(" has been removed!").toString()),
				HttpStatus.OK);
	}

	// TODO: UPDATE
}
