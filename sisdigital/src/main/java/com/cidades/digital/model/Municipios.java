package com.cidades.digital.model;

import org.bson.types.ObjectId;

public class Municipios {
	
	private ObjectId  _id;
	
	private Integer cod_ibge;
	
	private String cidade;
	
	private String uf;
	
	private Float latitude;
	
	private Float longetude;
	
	
	public Float getLongetude() {
		return longetude;
	}
	public void setLongetude(Float longetude) {
		this.longetude = longetude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getCod_ibge() {
		return cod_ibge;
	}
	public void setCod_ibge(Integer cod_ibge) {
		this.cod_ibge = cod_ibge;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}

}
