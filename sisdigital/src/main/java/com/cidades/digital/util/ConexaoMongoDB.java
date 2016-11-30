package com.cidades.digital.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.cidades.digital.model.CidadesInvestimentos;
import com.cidades.digital.model.Estado;
import com.cidades.digital.model.Municipios;
import com.cidades.digital.model.PopulacaoEstado;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;


public class ConexaoMongoDB {
	
	private static final String DBNAME = "cidadeDigital";
//	private static final String HOST = "192.168.64.101";
	private static final String HOST = "200.133.38.14";
	private static final int PORT = 27017;
	private static MongoClient client = null;
	
	private final static String COLLECTION = "municipiosinvestimentos";
	
	
	public static DB connectToMongo() throws Exception {
	    client = new MongoClient(HOST);                
	    return client.getDB(DBNAME);    
	}
	
	
	public List<Estado> getEstados() throws Exception{
		
		Jongo jongo = new Jongo(this.connectToMongo());
	    List<Estado> l = new  ArrayList<Estado>();
	    Iterator<Estado> cidades = jongo.getCollection("estados").find().as(Estado.class).iterator();
	    while(cidades.hasNext()){
	        l.add(cidades.next());
	    }
		return l;
		
	}
	
	
	public List<Municipios> getMuncipios() throws Exception{
		
		Jongo jongo = new Jongo(this.connectToMongo());
	    List<Municipios> l = new  ArrayList<Municipios>();
	    Iterator<Municipios> municipios = jongo.getCollection("municipios").find().as(Municipios.class).iterator();
	    while(municipios.hasNext()){
	        l.add(municipios.next());
	    }
		return l;
		
	}
	public List<CidadesInvestimentos> getCidadeInvestimentos(EstadoEnum estadoEnum) throws Exception{
		
		MongoClient  client = new MongoClient(HOST);                
	    DB db = client.getDB(DBNAME);    
		Jongo jongo =  new Jongo(db);
	    List<CidadesInvestimentos> l = new  ArrayList<CidadesInvestimentos>();
	    Iterator<CidadesInvestimentos> cidades = null;
	    if (estadoEnum==null)
	     cidades = jongo.getCollection(COLLECTION).find().as(CidadesInvestimentos.class).iterator();
	    else 
	    	cidades = jongo.getCollection(COLLECTION).find("{unidade_federativa:'"+estadoEnum.toString()+"'}").as(CidadesInvestimentos.class).iterator();
	    List<CidadesInvestimentos> list = new ArrayList<>();
	    cidades.forEachRemaining(list::add);
//	    while(cidades.hasNext()){
//	        l.add(cidades.next());
//	    }
		return list;
		
	}
	
	
	@SuppressWarnings({ "unused", "static-access" })
	public List<PopulacaoEstado> getTotalPopulucao(EstadoEnum estado) throws Exception{
		
		Jongo jongo = new Jongo(this.connectToMongo());
		Iterator<PopulacaoEstado> total = null;
		if (estado==null)
			total =  jongo.getCollection(COLLECTION).aggregate("{$group:{_id:\"$unidade_federativa\", total:{$sum:\"$populacao\"}}},{$sort:{_id:1}}").as(PopulacaoEstado.class);
		else 
			total =  jongo.getCollection(COLLECTION).aggregate("{$match:{unidade_federativa:\""+estado.toString()+"\"}}").and("{$group:{_id:\"$unidade_federativa\", total:{$sum:\"$populacao\"}}},{$sort:{_id:1}}").as(PopulacaoEstado.class);
		List<PopulacaoEstado> list = new ArrayList<PopulacaoEstado>();
	    total.forEachRemaining(list::add);
		return list;
	}
	
	public List<CidadesInvestimentos> getTotalStatusCidades(EstadoEnum estado, String status){
		Jongo jongo = null;
		Iterator<CidadesInvestimentos> total = null;
		try {
			jongo = new Jongo(this.connectToMongo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (estado==null)
			total =  jongo.getCollection(COLLECTION).find("{$and:[{\"status_implantacao\" :{$regex:\""+status+"\"}}]}").as(CidadesInvestimentos.class);
		else 
			total = jongo.getCollection(COLLECTION).find("{$and:[{\"status_implantacao\" :{$regex:\""+status+"\"}},{\"unidade_federativa\":\""+estado.toString()+"\"}]}").as(CidadesInvestimentos.class);

		List<CidadesInvestimentos> list = new ArrayList<CidadesInvestimentos>();
	    total.forEachRemaining(list::add);
		return list;
	}
	
	private static void atualizarMunicipio(List<Municipios> ms) throws Exception {
		Jongo jongo = new Jongo(connectToMongo());
//		CidadesInvestimentos ci = jongo.getCollection("municipiosinvestimentos").findOne("{IBGE:"+ms.getCod_ibge()+"}").as(CidadesInvestimentos.class);
//		if (ci==null)
//			return;
		MongoCollection collection = jongo.getCollection("municipiosinvestimentos");
//		for (Municipios m : ms) {
//			collection.update("{IBGE:"+m.getCod_ibge()+"}").with("{$set:{coordenate:{latitude:"+m.getLatitude()+",longitude:"+m.getLongetude()+"}}}");
//		}
//		collection.update("{'municipio':'Grão Mogol'}").with("{$set:{IBGE:3127800}}");
//		collection.update("{IBGE:3127800}").with("{$set:{coordenate:{latitude:-16.5653347,longitude:-42.8932343}}}");
		collection.update("{IBGE:2102002}").with("{$set:{unidade_federativa:'MA'}}");
	}

	
	
	public static void main(String[] args) {
		try {
//			List<CidadesInvestimentos>  cidadesInvestimentos = new ConexaoMongoDB().getCidadeInvestimentos(null);
//			System.out.println();
//			List<Municipios>  municipios = new ConexaoMongoDB().getMuncipios();
//			List<Municipios> ms = new ArrayList<Municipios>();
//			for (Municipios m : municipios) {
//				for (CidadesInvestimentos c : cidadesInvestimentos) {
//					if (m.getCod_ibge().equals(c.getIbge()))
//						ms.add(m);
//				}
//			}
			atualizarMunicipio(null);
			
//			for (CidadesInvestimentos cidade : valores) {
//				System.out.println("Cidade "+ cidade.getMunicipio()+ " "
//						+ " Status:  " + cidade.getStatus_implantacao() + " Pontos Atendidos : "+cidade.getPontos_atendidos() + " "
//						+ " Populacao :"+ cidade.getPopulacao() + " Valor Previsto: " 
//						+ cidade.getValor_total_previsto() + " Valor Investido: " + cidade.getValor_investido());
//				
//			
//			
//			
//			}
			
			
			
			
//			for (CidadeDigital cidadeDigital : valores) {
//				System.out.println("Cidade "+ cidadeDigital.getMunicipio()+ " Latitude:  " + cidadeDigital.getLatLong().getLatitude() + " Logentude : "+cidadeDigital.getLatLong().getLongitude());
//			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
