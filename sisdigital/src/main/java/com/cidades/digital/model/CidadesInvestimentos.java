package com.cidades.digital.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.bson.types.ObjectId;

import com.cidades.digital.jsf2leaf.model.LatLong;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CidadesInvestimentos {

private ObjectId _id;
private String unidade_federativa;
private String municipio;

@JsonProperty("IBGE")
private Integer ibge;

private Integer populacao;

private String valor_total_previsto;

private BigDecimal valorTotal01;

private Byte valorCentavos;

private String status_implantacao;

private Byte pontos_atendidos;

private String valor_investido;

private BigDecimal valorInvestido01;

private Byte valorInvestidoCentavos;

private LatLong coordenate;


public ObjectId get_id() {
	return _id;
}

public void set_id(ObjectId _id) {
	this._id = _id;
}

public String getUnidade_federativa() {
	return unidade_federativa;
}

public void setUnidade_federativa(String unidade_federativa) {
	this.unidade_federativa = unidade_federativa;
}

public String getMunicipio() {
	return municipio;
}

public void setMunicipio(String municipio) {
	this.municipio = municipio;
}

public Integer getIbge() {
	return ibge;
}

public void setIbge(Integer ibge) {
	this.ibge = ibge;
}

public Integer getPopulacao() {
	return populacao;
}

public void setPopulacao(Integer populacao) {
	this.populacao = populacao;
}

public String getValor_total_previsto() {
	return valor_total_previsto;
}

public void setValor_total_previsto(String valorTotalPrevisto) {
	this.valor_total_previsto = valorTotalPrevisto;
}

public BigDecimal getValorTotal01() {
	return valorTotal01;
}

public void setValorTotal01(BigDecimal valorTotal01) {
	this.valorTotal01 = valorTotal01;
}

public Byte getValorCentavos() {
	return valorCentavos;
}

public void setValorCentavos(Byte valorCentavos) {
	this.valorCentavos = valorCentavos;
}

public String getStatus_implantacao() {
	return status_implantacao;
}

public void setStatus_implantacao(String statusImplantacao) {
	this.status_implantacao = statusImplantacao;
}

public Byte getValorInvestidoCentavos() {
	return valorInvestidoCentavos;
}

public void setValorInvestidoCentavos(Byte valorInvestidoCentavos) {
	this.valorInvestidoCentavos = valorInvestidoCentavos;
}

public BigDecimal getValorInvestido01() {
	return valorInvestido01;
}

public void setValorInvestido01(BigDecimal valorInvestido01) {
	this.valorInvestido01 = valorInvestido01;
}

public String getValor_investido() {
	return valor_investido;
}

public void setValor_investido(String vaorInvestido) {
	this.valor_investido = vaorInvestido;
}

public Byte getPontos_atendidos() {
	return pontos_atendidos;
}

public void setPontos_atendidos(Byte pontosAtendidos) {
	this.pontos_atendidos = pontosAtendidos;
}

public LatLong getCoordenate() {
	return coordenate;
}

public void setCoordenate(LatLong coordenate) {
	this.coordenate = coordenate;
}

@Override
public String toString() {
	return getMunicipio();
}


public String getValorPrevisto(){
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	String formatado = nf.format(new Double(valor_total_previsto != null ? valor_total_previsto.replace(".", "").replace(",", ".") : "0.0"));
	return formatado;

}

public String getValorInvestido(){
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	String formatado = nf.format(new Double(valor_investido != null ? valor_investido.replace(".", "").replace(",", ".") : "0.0"));
	return formatado;

}

public String getPorcentagem(){
	Double previsto = new Double(valor_total_previsto != null ? valor_total_previsto.replace(".", "").replace(",", ".") : "0.0");
	Double investido = new Double(valor_investido != null ? valor_investido.replace(".", "").replace(",", ".") : "0.0");
	
	if (investido.doubleValue()>0){
		return String.valueOf(((int)((investido*100)/previsto)))+"%"; 
	}
	return "0%";
}

}
