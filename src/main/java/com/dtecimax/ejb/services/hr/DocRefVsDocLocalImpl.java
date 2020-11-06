package com.dtecimax.ejb.services.hr;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.jpa.dao.hr.DocRefVsDocDao;
import com.dtecimax.jpa.dto.hr.DocRefVsDocDto;
import com.dtecimax.jpa.dto.hr.DocRefVsDocV1Dto;

@Stateless
public class DocRefVsDocLocalImpl implements DocRefVsDocLocal {

	@Inject 
	DocRefVsDocDao docRefVsDocDao; 
	
	@Override
	public long insert(DocRefVsDocDto pDocRefVsDocDto) {
		return docRefVsDocDao.insert(pDocRefVsDocDto);
	}

	@Override
	public List<DocRefVsDocV1Dto> findAll() {
		return docRefVsDocDao.findAll();
	}

	@Override
	public void delete(long pNumero) {
		docRefVsDocDao.delete(pNumero);
	}

	@Override
	public void update(long pNumero, DocRefVsDocDto pDocRefVsDocDto) {
		docRefVsDocDao.update(pNumero, pDocRefVsDocDto);
	}

	@Override
	public long countByNumeroDoctorRef(long pNumeroDoctorRef) {
		return docRefVsDocDao.countByNumeroDoctorRef(pNumeroDoctorRef);
	}

	@Override
	public List<DocRefVsDocV1Dto> findByNumeroDoctorRef(long pNumeroDoctorRef) {
		return docRefVsDocDao.findByNumeroDoctorRef(pNumeroDoctorRef);
	}

}
