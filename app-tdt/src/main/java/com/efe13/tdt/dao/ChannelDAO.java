package com.efe13.tdt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.mvc.model.api.impl.helper.QueryHelper;
import com.efe13.tdt.model.entity.Channel;

public class ChannelDAO extends DAOAPI<Channel> {

	private final static String ACTIVE_CONDITION = "AND active = " + ActiveEnum.ACTIVE.getValue();
	private final static String QUERY_GET_BY_ID = "FROM Channel WHERE channelId = :channelId " + ACTIVE_CONDITION;
	private final static String QUERY_GET_ALL = "FROM Channel c WHERE 1=1 " + ACTIVE_CONDITION + " ORDER BY c.name";
	
	@Override
	public Channel getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( QUERY_GET_BY_ID );
		query.setParameter( "channelId", object.getId() );
		
		return (Channel) query.uniqueResult();
	}

	@Override
	public <E> List<EntityAPI> getAll( E helper ) {
		try {
			if( helper != null && !(helper instanceof QueryHelper) ) {
				throw new RuntimeException( "Query Helper expected!" );
			}
			
			QueryHelper queryHelper = (QueryHelper) helper;
			
			Query query = getSession().createQuery( QUERY_GET_ALL );
			if( queryHelper.getPaginationAPI() != null ) {
				query.setFirstResult( queryHelper.getPaginationAPI().getPage() );
				query.setMaxResults( queryHelper.getPaginationAPI().getPageSize() );
			}
			
			return query.list();
		}
		finally {
			super.closeSession();
		}
	}
}