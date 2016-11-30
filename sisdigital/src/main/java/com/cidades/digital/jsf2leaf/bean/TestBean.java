/*
A JSF2 Leaflet wrapper component for OpenStreetMap
Copyright (C) 2015 Leonardo Ciocari

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.*/

package com.cidades.digital.jsf2leaf.bean;

import java.io.Serializable;

import java.net.UnknownHostException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.cidades.digital.jsf2leaf.model.LatLong;
import com.cidades.digital.jsf2leaf.model.Layer;
import com.cidades.digital.jsf2leaf.model.Map;
import com.cidades.digital.jsf2leaf.model.Marker;
import com.cidades.digital.model.Cidade;
import com.cidades.digital.model.CidadesInvestimentos;
import com.cidades.digital.util.ConexaoMongoDB;
import com.cidades.digital.util.EstadoEnum;


@ManagedBean(name = "testBean")
@ViewScoped 
public class TestBean implements Serializable { // "implements Serializable" to fix WELD-000072

	private Map springfieldMap = new Map();

	private String estados;
	
	public TestBean()
	{
		//Configure Map

		//Places Layer
		Layer placesLayer = (new Layer()).setLabel("Places");
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		try {
			List<CidadesInvestimentos> cidadesInvestimentos = conexaoMongoDB.getCidadeInvestimentos(null);
			springfieldMap.setWidth("1600px").setHeight("950px").setCenter(new LatLong(cidadesInvestimentos.get(0).getCoordenate().getLatitude(), cidadesInvestimentos.get(0).getCoordenate().getLongitude())).setZoom(8);
            springfieldMap.setAttribution("Municipios | Brasileiros");
            springfieldMap.setMiniMap(true);
            springfieldMap.setMiniMapWidth(100);
            springfieldMap.setMiniMapHeight(66);
            springfieldMap.setMiniMapPosition("bottomright");
            int i=0;
            for (CidadesInvestimentos c : cidadesInvestimentos) {
				placesLayer.addMarker(new Marker(c.getCoordenate(),"<b>"+c.getMunicipio()+"/"+c.getUnidade_federativa()+"</b><br>Populacao: "
						+c.getPopulacao() +" <br>Valor Investido: R$" 
						+c.getValor_investido()+"<br>Valor Previsto: R$"+c.getValor_total_previsto()+"<br>Pontos Atendidos: "+c.getPontos_atendidos()+"<br> Status:"+c.getStatus_implantacao()));
				
			}
//            for (Municipios m : municipios ) {
//				placesLayer.addMarker(new Marker(new LatLong(m.getLatitude().toString(), m.getLongetude().toString()),"<b>"+m.getCidade()+"</b><br>Uf: "+m.getUf()));
//			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		springfieldMap.addLayer(placesLayer);
		
//		//Cluster Layer
//		Layer clusterLayer = (new Layer()).setLabel("Cluster").setClusterEnabled(true);
//		for(double lat=42; lat<43; lat+=0.0001)	//10k markers !
//			clusterLayer.addMarker(new Marker(new LatLong(""+lat,"-72.547488"),lat+" -72.547488"));
//		springfieldMap.addLayer(clusterLayer);
//		
//		//Polyline and Circle Layer
//		Layer polycircleLayer = (new Layer()).setLabel("Polyline/Circle");
//		polycircleLayer.addPolyline((new Polyline()).addPoint(new LatLong("42.114556","-72.526309")).addPoint(new LatLong("42.120000","-72.540000")).addPoint(new LatLong("42.120286","-72.547488")));
//		polycircleLayer.addCircle((new Circle()).setPosition(new LatLong("42.111707","-72.541008")));
//		springfieldMap.addLayer(polycircleLayer);
		
//		List<Estado> estados;
//		try {
//			estados = conexaoMongoDB.getEstados();
//			Polyline polyline = null;
//			for (Estado estado : estados) {
//				polyline = new Polyline();
//				polyline.addPoint(estado.getCoordenadasWrapper());
//				polyline.setColor(configurarCor(estado.getCity()));
//				placesLayer.addPolyline(polyline);
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
	
	private String configurarCor(Cidade city) {
		if (city.getState().equals(EstadoEnum.AC.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.AL.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.AM.toString())){
			return "#990066";
		}
		if (city.getState().equals(EstadoEnum.AP.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.BA.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.CE.toString())){
			return "#800080";
		}
		if (city.getState().equals(EstadoEnum.DF.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.ES.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.GO.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.MA.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.MG.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.MS.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.MT.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.PA.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.PB.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.PE.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.PI.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.PR.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.RJ.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.RN.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.RO.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.RR.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.RS.toString())){
			return "#3300CC";
		}
		if (city.getState().equals(EstadoEnum.SC.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.SE.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.SP.toString())){
			return "#F2000D";
		}
		if (city.getState().equals(EstadoEnum.TO.toString())){
			return "#0000FF";
		}
		return "#E6001A";
	}

	public Map getSpringfieldMap() {
		return springfieldMap;
	}

	public String getEstados() {
		return estados;
	}

	public void setEstados(String estados) {
		this.estados = estados;
	}


}