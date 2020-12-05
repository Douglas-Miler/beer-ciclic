package br.com.ciclic.beer_webservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Beer {

	@Id
	private long id;
	private String type;
	private int minTemperature;
	private int maxTemperature;
	private int temperatureAverage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public int getTemperatureAverage() {
		return temperatureAverage;
	}

	public void setTemperatureAverage(int temperatureAverage) {
		this.temperatureAverage = temperatureAverage;
	}

}
