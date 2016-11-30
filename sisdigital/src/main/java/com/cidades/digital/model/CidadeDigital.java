package com.cidades.digital.model;

import org.bson.types.ObjectId;

import com.cidades.digital.jsf2leaf.model.LatLong;

public class CidadeDigital {
	
	
	private ObjectId _id;
	
	private String fid;
	
	private Integer codigo;
	
	private String empreendimento;
	
	private String subeixo;
	private String tipo;
	private String orgao_responsavel;
	private String executor;
	private String unidade_federativa;
	private String municipio;
	private String investimento_previsto;
	private String observacao;
	private String estagio;
	private String data_de_referencia;
	private String geometria;
	private Integer count;
	
	public CidadeDigital(){
	}
	
	public CidadeDigital(Object[] objCidade) {
		// TODO Auto-generated constructor stub
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(String empreendimento) {
		this.empreendimento = empreendimento;
	}
	public String getSubeixo() {
		return subeixo;
	}
	public void setSubeixo(String subeixo) {
		this.subeixo = subeixo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOrgao_responsavel() {
		return orgao_responsavel;
	}
	public void setOrgao_responsavel(String orgao_responsavel) {
		this.orgao_responsavel = orgao_responsavel;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
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
	public String getInvestimento_previsto() {
		return investimento_previsto;
	}
	public void setInvestimento_previsto(String investimento_previsto) {
		this.investimento_previsto = investimento_previsto;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getEstagio() {
		return estagio;
	}
	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}
	public String getData_de_referencia() {
		return data_de_referencia;
	}
	public void setData_de_referencia(String data_de_referencia) {
		this.data_de_referencia = data_de_referencia;
	}
	public String getGeometria() {
		return geometria;
	}
	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public LatLong getLatLong(){
		String valores = geometria.replace("POINT"," ").replace(')', ' ').replace('(', ' ').trim();
		String[] v = valores.split(" ");
		LatLong latLong = new LatLong(v[1], v[0]);
		return latLong;
	}

	
}
