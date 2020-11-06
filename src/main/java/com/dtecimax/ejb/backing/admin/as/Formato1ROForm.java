package com.dtecimax.ejb.backing.admin.as;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean  
@ViewScoped
public class Formato1ROForm {

	 @PostConstruct
	 public void init() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap  = context.getSessionMap();
        Long longNumeroOrden =  (Long)sessionMap.get("svNumeroOrden"); 
	 }
}
