package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Concessionaire;

public class ConcessionaireDAO extends DAOAPI<Concessionaire> {

	@Override
	public Concessionaire getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM Concessionaire WHERE concessionaireId = :concessionaireId" );
		query.setParameter("concessionaireId", object.getId() );
		
		return (Concessionaire) query.uniqueResult();
	}

}