package com.dtecimax.jpa.jdbc.hr;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.hr.DocRefVsDocDao;
import com.dtecimax.jpa.dto.hr.DocRefVsDocDto;
import com.dtecimax.jpa.dto.hr.DocRefVsDocV1Dto;

@Stateless 
public class DocRefVsDocDaoImpl implements DocRefVsDocDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public long insert(DocRefVsDocDto pDocRefVsDocDto) {
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.DOC_REF_VS_DOC_S");
		BigInteger lDocRefVsDocS = (BigInteger)q.getSingleResult();
		pDocRefVsDocDto.setNumero(lDocRefVsDocS.longValue());
        em.persist(pDocRefVsDocDto);
		return lDocRefVsDocS.longValue();
	}

	@Override
	public List<DocRefVsDocV1Dto> findAll() {
		String strQuery = "SELECT d FROM DocRefVsDocV1Dto d"; 
		Query query = em.createQuery(strQuery); 
		return query.getResultList();
	}

	@Override
	public void delete(long pNumero) {
		DocRefVsDocDto docRefVsDocDto = em.find(DocRefVsDocDto.class, pNumero); 
		em.remove(docRefVsDocDto);
		
	}

	@Override
	public void update(long pNumero, DocRefVsDocDto pDocRefVsDocDto) {
		DocRefVsDocDto docRefVsDocDto = em.find(DocRefVsDocDto.class, pNumero); 
		docRefVsDocDto.setNumeroDoctor(pDocRefVsDocDto.getNumeroDoctor());
		docRefVsDocDto.setNumeroDoctorReferente(pDocRefVsDocDto.getNumeroDoctorReferente());
		docRefVsDocDto.setNumeroUbicacion(pDocRefVsDocDto.getNumeroUbicacion());
	}

	@Override
	public long countByNumeroDoctorRef(long pNumeroDoctorRef) {
		String strQuery = "SELECT count(1) FROM DocRefVsDocV1Dto d WHERE d.numeroDoctorReferente="+pNumeroDoctorRef; 
		Query query = em.createQuery(strQuery); 
		Long longResult = (Long)query.getSingleResult(); 
		return longResult.longValue();
	}

	@Override
	public List<DocRefVsDocV1Dto> findByNumeroDoctorRef(long pNumeroDoctorRef) {
		String strQuery = "SELECT d FROM DocRefVsDocV1Dto d WHERE d.numeroDoctorReferente = "+pNumeroDoctorRef; 
		Query query = em.createQuery(strQuery); 
		return query.getResultList();
	}

}
