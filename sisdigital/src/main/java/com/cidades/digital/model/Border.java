package com.cidades.digital.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.cidades.digital.util.BorderDeserializer;

public class Border implements Serializable{
	
	private Double lat;
	
	private Double lng;

	public Border(double lat, double lng) {
		setLat(lat);
		setLng(lng);
	}
	
	public Border() {
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
