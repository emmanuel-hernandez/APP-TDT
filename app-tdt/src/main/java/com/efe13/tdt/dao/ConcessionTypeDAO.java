package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.ConcessionType;

public class ConcessionTypeDAO extends DAOAPI<ConcessionType> {

	@Override
	public ConcessionType getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM ConcessionType WHERE concessionTypeId = :concessionTypeId" );
		query.setParameter("concessionTypeId", object.getId() );
		
		return (ConcessionType) query.uniqueResult();
	}

}