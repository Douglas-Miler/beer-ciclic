package br.com.ciclic.beer_webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ciclic.beer_webservice.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>{

}
