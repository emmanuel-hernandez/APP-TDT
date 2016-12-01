package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Population;

public class PopulationDAO extends DAOAPI<Population> {
	
	private final static String ACTIVE_CONDITION = "AND active = " + ActiveEnum.ACTIVE.getValue();
	private final static String QUERY_GET_BY_ID = "FROM Population WHERE populationId = :populationId " + ACTIVE_CONDITION;

	@Override
	public Population getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( QUERY_GET_BY_ID );
		query.setParameter( "populationId", object.getId() );
		
		return (Population) query.uniqueResult();
	}

}