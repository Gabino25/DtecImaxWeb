package com.dtecimax.ejb.services.hr;

import java.util.List;

import javax.ejb.Local;

import com.dtecimax.jpa.dto.hr.DocRefVsDocDto;
import com.dtecimax.jpa.dto.hr.DocRefVsDocV1Dto;

@Local
public interface DocRefVsDocLocal {

	public long insert(DocRefVsDocDto pDocRefVsDocDto); 
	public List<DocRefVsDocV1Dto> findAll(); 
	public void delete(long pNumero); 
	public void update(long pNumero
			          ,DocRefVsDocDto pDocRefVsDocDto
			          ); 
	public long countByNumeroDoctorRef(long pNumeroDoctorRef);
	public List<DocRefVsDocV1Dto> findByNumeroDoctorRef(long pNumeroDoctorRef);
}
