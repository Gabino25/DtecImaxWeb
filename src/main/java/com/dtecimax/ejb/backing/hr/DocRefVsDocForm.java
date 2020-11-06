package com.dtecimax.ejb.backing.hr;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.hr.DocRefVsDoc;
import com.dtecimax.ejb.services.hr.DocRefVsDocLocal;
import com.dtecimax.ejb.services.hr.DoctoresLocal;
import com.dtecimax.jpa.dto.hr.DocRefVsDocDto;
import com.dtecimax.jpa.dto.hr.DocRefVsDocV1Dto;
import com.dtecimax.jpa.dto.hr.DoctoresDto;

import java.util.List; 
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator; 

@ManagedBean  
@ViewScoped
public class DocRefVsDocForm {

	private long numeroUbicacion; 
	private long numeroDoctorReferente; 
	private long numeroDoctor; 
	private List<DocRefVsDoc> listDocRefVsDoc = new ArrayList<DocRefVsDoc>(); 
	private Date sysdate = new Date();
	private DocRefVsDoc docRefVsDocForAction = new DocRefVsDoc(); 
	
	@Inject 
	DocRefVsDocLocal docRefVsDocLocal; 
	
	@Inject
	DoctoresLocal doctoresLocal;
	
	@PostConstruct
	public void init() {
		refreshEntity();
	}
	
	private void refreshEntity() {
		 List<DocRefVsDocV1Dto> listDocRefVsDocV1Dto = docRefVsDocLocal.findAll(); 
		 listDocRefVsDoc = new ArrayList<DocRefVsDoc>(); 
		 for(DocRefVsDocV1Dto docRefVsDocV1Dto:listDocRefVsDocV1Dto) {
			 DocRefVsDoc docRefVsDoc = new DocRefVsDoc(); 
			 docRefVsDoc.setNumero(docRefVsDocV1Dto.getNumero());
			 docRefVsDoc.setNumeroUbicacion(docRefVsDocV1Dto.getNumeroUbicacion());
			 docRefVsDoc.setNumeroDoctor(docRefVsDocV1Dto.getNumeroDoctor());
			 docRefVsDoc.setNumeroDoctorReferente(docRefVsDocV1Dto.getNumeroDoctorReferente());
			 docRefVsDoc.setNombreUbicacion(docRefVsDocV1Dto.getNombreUbicacion());
			 docRefVsDoc.setNombreCompletoDoctor(docRefVsDocV1Dto.getNombreCompletoDoctor());
			 docRefVsDoc.setNombreCompletoDoctorRef(docRefVsDocV1Dto.getNombreCompletoDoctorRef());
			 listDocRefVsDoc.add(docRefVsDoc); 
		 }
	}

	public void insert() {
		boolean loggedIn = false; 
		DocRefVsDocDto 	docRefVsDocDto = new DocRefVsDocDto(); 
		docRefVsDocDto.setNumeroUbicacion(this.getNumeroUbicacion());
		docRefVsDocDto.setNumeroDoctorReferente(this.getNumeroDoctorReferente());
		docRefVsDocDto.setNumeroDoctor(this.getNumeroDoctor());
		docRefVsDocDto.setFechaCreacion(new java.sql.Timestamp(sysdate.getTime()));
		docRefVsDocDto.setFechaUltimaActualizacion(new java.sql.Timestamp(sysdate.getTime()));
		docRefVsDocDto.setEstatus(true);
		docRefVsDocLocal.insert(docRefVsDocDto); 
		refreshEntity();
		loggedIn = true;
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}
	
	public void selectForAction(DocRefVsDoc pDocRefVsDoc) {
		docRefVsDocForAction.setNumero(pDocRefVsDoc.getNumero());
		docRefVsDocForAction.setNumeroDoctor(pDocRefVsDoc.getNumeroDoctor());
		docRefVsDocForAction.setNumeroDoctorReferente(pDocRefVsDoc.getNumeroDoctorReferente());
		docRefVsDocForAction.setNumeroUbicacion(pDocRefVsDoc.getNumeroUbicacion());
	}
	
	public void delete() {
		boolean deleteIn = false; 
		docRefVsDocLocal.delete(docRefVsDocForAction.getNumero());
		refreshEntity();
		deleteIn = true;
		PrimeFaces.current().ajax().addCallbackParam("deleteIn", deleteIn);
	}
	
	public void update() {
		boolean updateIn = false;
		DocRefVsDocDto 	docRefVsDocDto = new DocRefVsDocDto(); 
		docRefVsDocDto.setNumeroUbicacion(docRefVsDocForAction.getNumeroUbicacion());
		docRefVsDocDto.setNumeroDoctorReferente(docRefVsDocForAction.getNumeroDoctorReferente());
		docRefVsDocDto.setNumeroDoctor(docRefVsDocForAction.getNumeroDoctor());
		docRefVsDocLocal.update(docRefVsDocForAction.getNumero(),docRefVsDocDto);
		refreshEntity();
		updateIn = true;
		PrimeFaces.current().ajax().addCallbackParam("updateIn", updateIn);
	}
	

	public List<DocRefVsDoc> getListDocRefVsDoc() {
		return listDocRefVsDoc;
	}

	public void setListDocRefVsDoc(List<DocRefVsDoc> listDocRefVsDoc) {
		this.listDocRefVsDoc = listDocRefVsDoc;
	}

	public long getNumeroUbicacion() {
		return numeroUbicacion;
	}

	public void setNumeroUbicacion(long numeroUbicacion) {
		this.numeroUbicacion = numeroUbicacion;
	}

	public long getNumeroDoctorReferente() {
		return numeroDoctorReferente;
	}

	public void setNumeroDoctorReferente(long numeroDoctorReferente) {
		this.numeroDoctorReferente = numeroDoctorReferente;
	}

	public long getNumeroDoctor() {
		return numeroDoctor;
	}

	public void setNumeroDoctor(long numeroDoctor) {
		this.numeroDoctor = numeroDoctor;
	}

	public DocRefVsDoc getDocRefVsDocForAction() {
		return docRefVsDocForAction;
	}

	public void setDocRefVsDocForAction(DocRefVsDoc docRefVsDocForAction) {
		this.docRefVsDocForAction = docRefVsDocForAction;
	}

	
	
}
