package br.com.ciclic.beer_webservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ciclic.beer_webservice.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>{

	List<Beer> findByTemperatureAverage(int temperatureAverage);
	
}
