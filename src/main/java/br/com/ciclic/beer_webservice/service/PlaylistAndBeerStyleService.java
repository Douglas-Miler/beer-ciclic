package br.com.ciclic.beer_webservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ciclic.beer_webservice.model.Beer;
import br.com.ciclic.beer_webservice.model.PlaylistAndBeerStyle;
import br.com.ciclic.beer_webservice.repository.BeerRepository;

@Service
public class PlaylistAndBeerStyleService {

	@Autowired
	private BeerRepository beerRepository;
	
	public PlaylistAndBeerStyle getPlaylistAndBeerStyle(int temperature) {

		Beer mainBeer = getBeerByTemperatureClosestToBeerAverageTemperature(temperature);
		
		
		
		return null;
	}

	private Beer getBeerByTemperatureClosestToBeerAverageTemperature(int temperature) {
		List<Beer> beerList = beerRepository.findAll();
		Beer mainBeer = beerList.get(0);
		int mainTemperatureDifference = getDifferenceBetweenTemperatureAndBeerAverageTemperature(temperature, mainBeer);
		
		for (int i = 1; i < beerList.size(); i++) {
			Beer newBeer = beerList.get(i);
			int newTemperatureDifference = getDifferenceBetweenTemperatureAndBeerAverageTemperature(temperature, newBeer);
			
			if(newTemperatureDifference < mainTemperatureDifference) {
				mainBeer = newBeer;
			}else if(newTemperatureDifference ==  mainTemperatureDifference) {
				mainBeer = chooseBeerByName(mainBeer, newBeer);
			}
		}
		
		return mainBeer;
	}

	private int getDifferenceBetweenTemperatureAndBeerAverageTemperature(int temperature, Beer mainBeer) {
		return temperature - mainBeer.getTemperatureAverage();
	}

	private Beer chooseBeerByName(Beer... beer) {
		List<Beer> beerList = new ArrayList<>();
		beerList.add(beer[0]);
		beerList.add(beer[1]);
		
		beerList.sort((firstBeer, secondBeer) -> firstBeer.getType().compareTo(secondBeer.getType()));
		
		return beerList.get(0);
	}

}
