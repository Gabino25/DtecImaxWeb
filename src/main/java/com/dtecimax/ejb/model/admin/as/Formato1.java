package com.dtecimax.ejb.model.admin.as;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named  
@ViewScoped
public class Formato1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3915312156279980368L;

	private String nombrePaciente;

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	} 
	
}
