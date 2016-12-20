package com.efe13.tdt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.model.entity.State;

public class StateDAO extends DAOAPI<State> {
	
	private final static String ORDER_BY = " ORDER BY s.name";
	private final static String ACTIVE_CONDITION = "AND s.active = " + ActiveEnum.ACTIVE.getValue();
	private final static String QUERY_GET_BY_ID = "FROM State s WHERE s.id = :stateId " + ACTIVE_CONDITION + ORDER_BY;
	private final static String QUERY_GET_ALL = "FROM State s WHERE 1=1 " + ACTIVE_CONDITION + ORDER_BY;

	@Override
	public State getById( EntityAPI object ) throws HibernateException, DAOException {
		try {
			Query query = getSession().createQuery( QUERY_GET_BY_ID );
			query.setParameter( "stateId", object.getId() );
			
			return (State) query.uniqueResult();
		}
		finally {
			super.closeSession();
		}
	}

	@Override
	public <E> List<EntityAPI> getAll( E helper ) {
		try {
			if( helper != null && !(helper instanceof QueryHelper) ) {
				throw new RuntimeException( "Query Helper expected!" );
			}
			
			QueryHelper queryHelper = (QueryHelper) helper;
			
			Query query = getSession().createQuery( QUERY_GET_ALL );
			if( queryHelper != null ) {
				if( queryHelper.getPaginationAPI() != null ) {
					query.setFirstResult( queryHelper.getPaginationAPI().getPage() );
					query.setMaxResults( queryHelper.getPaginationAPI().getPageSize() );
				}
			}
			
			return query.list();
		}
		finally {
			super.closeSession();
		}
	}
}