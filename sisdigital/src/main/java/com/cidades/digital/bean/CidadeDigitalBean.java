package com.cidades.digital.bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.cidades.digital.jsf2leaf.model.LatLong;
import com.cidades.digital.jsf2leaf.model.Layer;
import com.cidades.digital.jsf2leaf.model.Map;
import com.cidades.digital.jsf2leaf.model.Marker;
import com.cidades.digital.model.CidadesInvestimentos;
import com.cidades.digital.model.PopulacaoEstado;
import com.cidades.digital.util.ConexaoMongoDB;
import com.cidades.digital.util.EstadoEnum;

@ManagedBean(name = "cidadedigital")
@ViewScoped
public class CidadeDigitalBean implements Serializable {

	private Map mapa = new Map();

	private double investimentoTotal;

	private EstadoEnum estado;

	private CidadesInvestimentos cidade;

	private Double valorInvestimento = new Double(0.0);

	private List<CidadesInvestimentos> cidadesInvestimentos;

	private double valorInvestimentoPrevisto;

	private List<CidadesInvestimentos> cidadesEscolhidas = new ArrayList<CidadesInvestimentos>();

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public List<SelectItem> getEstados() {
		List<EstadoEnum> list = Arrays.asList(EstadoEnum.values());
		Collections.sort(list, new Comparator<EstadoEnum>() {
			@Override
			public int compare(EstadoEnum o1, EstadoEnum o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		});
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (EstadoEnum type : list) {
			items.add(new SelectItem(type, type.getNome()));
		}
		return items;
	}

	public List<SelectItem> getCidades() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		Collections.sort(cidadesInvestimentos, new Comparator<CidadesInvestimentos>() {
			@Override
			public int compare(CidadesInvestimentos o1, CidadesInvestimentos o2) {
				return o1.getMunicipio().compareTo(o2.getMunicipio());
			}
		});
		if (cidadesInvestimentos != null) {
			for (CidadesInvestimentos c : cidadesInvestimentos) {
				items.add(new SelectItem(c, c.getMunicipio()));
			}
		}
		return items;
	}

	public void drawMaps() {
		// Places Layer
		Layer placesLayer = (new Layer()).setLabel("Places");
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		try {
			List<CidadesInvestimentos> cidadesInvestimentos = conexaoMongoDB.getCidadeInvestimentos(getEstado());
			setCidadeInvestimentos(cidadesInvestimentos);
			mapa.setAttribution("Municipios | Brasileiros");
			mapa.setMiniMap(true);
			mapa.setMiniMapWidth(100);
			mapa.setMiniMapHeight(66);
			mapa.setMiniMapPosition("bottomright");
			int i = 0;
			for (CidadesInvestimentos c : cidadesInvestimentos) {
				if (getEstado() == null) {
					if (c.getUnidade_federativa().equals("DF")) {
						mapa.setWidth("1500px").setHeight("850px")
								.setCenter(
										new LatLong(c.getCoordenate().getLatitude(), c.getCoordenate().getLongitude()))
								.setZoom(5);
					}
				} else
					mapa.setWidth("1500px").setHeight("850px")
							.setCenter(new LatLong(c.getCoordenate().getLatitude(), c.getCoordenate().getLongitude()))
							.setZoom(5);
				placesLayer
						.addMarker(new Marker(c.getCoordenate(),
								"<b>" + c.getMunicipio() + "/" + c.getUnidade_federativa() + "</b><br>Populacao: "
										+ NumberFormat.getNumberInstance(new Locale("pt", "BR"))
												.format(c.getPopulacao())
										+ " <br>Valor Investido: " + configurarValorMontario(c.getValor_investido())
										+ "<br>Valor Previsto: " + configurarValorMontario(c.getValor_total_previsto())
										+ ((c.getStatus_implantacao().equals("Concluída")) ? "<br>Pontos Atendidos: "
												: "<br>Pontos a serem Atendidos: ")
										+ c.getPontos_atendidos() + "<b><br> Status:" + c.getStatus_implantacao()
										+ "</b>"));

			}
			setEstado(getEstado());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapa.addLayer(placesLayer);
	}

	private String configurarValorMontario(String valor) {
		BigDecimal reais = new BigDecimal(valor.replace(".", "").replace(",", "."));
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		String formatado = nf.format(reais);
		return formatado;
	}

	private void setCidadeInvestimentos(List<CidadesInvestimentos> cidadesInvestimentos) {
		this.cidadesInvestimentos = cidadesInvestimentos;
	}

	public void addCidades(List<CidadesInvestimentos> cidades) {
		this.cidadesEscolhidas.addAll(cidades);
	}

	public Map getMapa() {
		this.cidadesEscolhidas.clear();
		drawMaps();
		return mapa;
	}

	public String getPopulacaoTotal() {
		Integer populacaoTotal = 0;
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		try {
			List<PopulacaoEstado> populacaoEstados = conexaoMongoDB.getTotalPopulucao(getEstado());
			for (PopulacaoEstado populacaoEstado : populacaoEstados) {
				if (getEstado() != null && getEstado().toString().equals(populacaoEstado.get_id())) {
					return NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(populacaoEstado.getTotal());
				} else {
					populacaoTotal += populacaoEstado.getTotal();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(populacaoTotal);
	}

	public double getInvestimentoTotal() {
		return investimentoTotal;
	}

	public void setInvestimentoTotal(double investimentoTotal) {
		this.investimentoTotal = investimentoTotal;
	}

	public Integer getTotalCidadesConcluidas() {
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		List<CidadesInvestimentos> cidades = conexaoMongoDB.getTotalStatusCidades(getEstado(), "Conclu");
		addCidades(cidades);
		setValorInvestimento(configurarValorInvestimentoTotal(cidades));
		setValorPrevisto(configurarValorInvestimentoPrevisto(cidades));
		return cidades.size();
	}

	public Integer getTotalCidadesSemPrevisao() {
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		List<CidadesInvestimentos> cidades = conexaoMongoDB.getTotalStatusCidades(getEstado(), "Sem Pre");
		addCidades(cidades);
		setValorInvestimento(configurarValorInvestimentoTotal(cidades));
		setValorPrevisto(configurarValorInvestimentoPrevisto(cidades));
		return cidades.size();

	}

	public Integer getTotalCidadesEmAndamento() {
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		List<CidadesInvestimentos> cidades = conexaoMongoDB.getTotalStatusCidades(getEstado(), "Em ");
		addCidades(cidades);
		setValorInvestimento(configurarValorInvestimentoTotal(cidades));
		setValorPrevisto(configurarValorInvestimentoPrevisto(cidades));
		return cidades.size();
	}

	private Double configurarValorInvestimentoTotal(List<CidadesInvestimentos> cidades) {
		Double valorInvestimento = new Double(0.0);
		for (CidadesInvestimentos cidade : cidades) {
			Double valor = new Double(cidade.getValor_investido().replace(".", "").replace(",", "."));
			valorInvestimento = valorInvestimento.doubleValue() + valor;
		}
		return valorInvestimento;
	}

	private Double configurarValorInvestimentoPrevisto(List<CidadesInvestimentos> cidades) {
		Double valorPrevisto = new Double(0.0);
		for (CidadesInvestimentos cidade : cidades) {
			Double valor = new Double(cidade.getValor_total_previsto().replace(".", "").replace(",", "."));
			valorPrevisto = valorPrevisto.doubleValue() + valor;
		}
		return valorPrevisto;
	}

	private void setValorPrevisto(Double valorInvestimentoPrevisto) {
		this.valorInvestimentoPrevisto = this.valorInvestimentoPrevisto
				+ (valorInvestimentoPrevisto == null ? 0.0 : valorInvestimentoPrevisto);
	}

	private void setValorInvestimento(Double valorInvestimento) {
		this.valorInvestimento = this.valorInvestimento + (valorInvestimento == null ? 0.0 : valorInvestimento);
	}

	public String getValorInvestimento() {
		BigDecimal valor = new BigDecimal(valorInvestimento);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		String formatado = nf.format(valor);
		return formatado;
	}

	public String getValorPrevisto() {
		BigDecimal valor = new BigDecimal(valorInvestimentoPrevisto);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		String formatado = nf.format(valor);
		return formatado;
	}

	public CidadesInvestimentos getCidade() {
		return cidade;
	}

	public void setCidade(CidadesInvestimentos cidade) {
		this.cidade = cidade;
	}

	public void buscarCidades(ActionEvent actionEvent) {
		buscar();
	}

	public void buscar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		try {
			setCidadesEscolhidas(conexaoMongoDB.getCidadeInvestimentos(EstadoEnum.getEstado(getIdEstado(fc))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setCidadesEscolhidas(List<CidadesInvestimentos> cidadeInvestimentos) {
		this.cidadesEscolhidas = cidadeInvestimentos;
	}

	public List<CidadesInvestimentos> getCidadesEscolhidas() {
		if (cidadesEscolhidas.isEmpty()) buscar();
		return this.cidadesEscolhidas;
	}

	public String getIdEstado(FacesContext fc) {
		java.util.Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("jsfcidades:idEstados_input");
	}
}
