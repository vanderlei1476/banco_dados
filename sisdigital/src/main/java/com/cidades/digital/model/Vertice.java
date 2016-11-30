package com.cidades.digital.model;

import java.io.Serializable;

public class Vertice implements Serializable {
	
	private Double lat;
	
	private Double lng;
	

	public Vertice(Double lat, Double lng) {
		setLat(lat);
		setLng(lng);
	}
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	


}
