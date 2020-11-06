package com.dtecimax.jpa.dao.hr;

import java.util.List;

import com.dtecimax.jpa.dto.hr.DocRefVsDocDto;
import com.dtecimax.jpa.dto.hr.DocRefVsDocV1Dto;

public interface DocRefVsDocDao {

	public long insert(DocRefVsDocDto pDocRefVsDocDto); 
	public List<DocRefVsDocV1Dto> findAll(); 
	public void delete(long pNumero); 
	public void update(long pNumero
			          ,DocRefVsDocDto pDocRefVsDocDto
			          ); 
	public long countByNumeroDoctorRef(long pNumeroDoctorRef);
	public List<DocRefVsDocV1Dto> findByNumeroDoctorRef(long pNumeroDoctorRef);
}
