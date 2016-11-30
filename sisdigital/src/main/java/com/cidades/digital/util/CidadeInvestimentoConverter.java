package com.cidades.digital.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.cidades.digital.model.CidadesInvestimentos;

@FacesConverter(forClass = CidadesInvestimentos.class)
public class CidadeInvestimentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (CidadesInvestimentos) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof CidadesInvestimentos) {
			CidadesInvestimentos entity = (CidadesInvestimentos) value;
			if (entity != null && entity instanceof CidadesInvestimentos && entity.getIbge() != null) {
				uiComponent.getAttributes().put(entity.getIbge().toString(), entity);
				return entity.getIbge().toString();
			}
		}
		return "";
	}

}