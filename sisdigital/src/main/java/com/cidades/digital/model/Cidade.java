package com.cidades.digital.model;

import java.io.Serializable;
/**
 * Olá, teste
 * @author Henrique
 *
 */
public class Cidade implements Serializable {

	private String name;
	
	private String state;

	public Cidade() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
