package com.cidades.digital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.cidades.digital.jsf2leaf.model.LatLong;

public class Estado implements Serializable{
	
	private ObjectId _id;
	
	private Cidade city;
	
	private List<List<Border>> borders;

	public Estado() {
	}
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public Cidade getCity() {
		return city;
	}

	public void setCity(Cidade city) {
		this.city = city;
	}

	public List<List<Border>> getBorders() {
		return borders;
	}

	public void setBorders(List<List<Border>> borders) {
		this.borders = borders;
	}

	public List<LatLong> getCoordenadasWrapper(){
		List<LatLong> wrapper = new ArrayList<LatLong>();
		for (List<Border> list : borders) {
			for (Border border : list) {
				if (border.getLat()== null) 
					continue;
				LatLong wrapp = new LatLong(border.getLat().toString(), border.getLng().toString());
				wrapper.add(wrapp);
			}
		}
		return wrapper;
	}
	
	
}
