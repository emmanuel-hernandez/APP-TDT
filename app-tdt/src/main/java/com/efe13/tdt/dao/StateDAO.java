package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.State;

public class StateDAO extends DAOAPI<State> {

	@Override
	public State getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM State WHERE stateId = :stateId" );
		query.setParameter("stateId", object.getId() );
		
		return (State) query.uniqueResult();
	}

}