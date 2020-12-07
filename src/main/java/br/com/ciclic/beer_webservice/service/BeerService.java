package br.com.ciclic.beer_webservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ciclic.beer_webservice.model.Beer;
import br.com.ciclic.beer_webservice.model.BeerDto;
import br.com.ciclic.beer_webservice.repository.BeerRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;
	
	public Beer getBeerByTemperatureClosestToBeerAverageTemperature(int temperature) {
		final int firstElement = 0;

		List<Beer> beerList = this.beerRepository.findAll();
		Beer mainBeer = beerList.get(firstElement);

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
		int difference = temperature - mainBeer.getTemperatureAverage();
		
		if(difference < 0)
			return -difference;
		
		return difference;
	}

	private Beer chooseBeerByName(Beer... beer) {
		final int firstElement = 0;
		final int lastElement = 1;
		
		List<Beer> beerList = new ArrayList<>();
		beerList.add(beer[firstElement]);
		beerList.add(beer[lastElement]);
		
		beerList.sort((firstBeer, secondBeer) -> firstBeer.getType().toLowerCase().compareTo(secondBeer.getType().toLowerCase()));
		
		return beerList.get(firstElement);
	}

	public int getTemperatureAverage(BeerDto beerDto) {
		return (beerDto.getMaxTemperature() - beerDto.getMinTemperature()) / 2;
	}
	
}
