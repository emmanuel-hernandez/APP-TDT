package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Population;

public class PopulationDAO extends DAOAPI<Population> {

	@Override
	public Population getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM Population WHERE populationId = :populationId" );
		query.setParameter("populationId", object.getId() );
		
		return (Population) query.uniqueResult();
	}

}