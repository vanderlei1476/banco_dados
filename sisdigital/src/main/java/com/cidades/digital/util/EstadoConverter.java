package com.cidades.digital.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (context == null) { throw new NullPointerException("context");}
        if (component == null) {throw new NullPointerException("component");}
        if(obj instanceof EstadoEnum)
        {
             return EstadoEnum.get((EstadoEnum)obj).toString();
        }else
        {   
            return "";
        }
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (context == null) { throw new NullPointerException("context");}
        if (component == null) {throw new NullPointerException("component");}

        EstadoEnum est = null;
        if (value != null && !value.equalsIgnoreCase("") && value.trim().length() > 0) {
            est = EstadoEnum.getEstado(value);
            if (est == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown value", "The car is unknown!");
                throw new ConverterException(message);
            }
        }
        return est;
    }
}