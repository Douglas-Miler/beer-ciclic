package br.com.ciclic.beer_webservice.model;

public class BeerDto {

	private String type;
	
	private int minTemperature;
	
	private int maxTemperature;

	public BeerDto() {

	}
	
	public BeerDto(String type, int minTemperature, int maxTemperature) {
		this.type = type;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
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

}
