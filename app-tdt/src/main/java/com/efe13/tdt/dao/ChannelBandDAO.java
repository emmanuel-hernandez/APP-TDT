package com.efe13.tdt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.ChannelBand;

public class ChannelBandDAO extends DAOAPI<ChannelBand> {
	
	private final static String ACTIVE_CONDITION = "AND active = " + ActiveEnum.ACTIVE.getValue();
	private final static String QUERY_GET_BY_ID = "FROM ChannelBand WHERE channelBandId = :channelBandId " + ACTIVE_CONDITION;
	private final static String QUERY_GET_ALL = "FROM ChannelBand cb WHERE 1=1 " + ACTIVE_CONDITION + " ORDER BY cb.name";;

	@Override
	public ChannelBand getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( QUERY_GET_BY_ID );
		query.setParameter( "channelBandId", object.getId() );
		
		return (ChannelBand) query.uniqueResult();
	}
	
	@Override
	public List<EntityAPI> getAll() {
		try {
			Query query = getSession().createQuery( QUERY_GET_ALL );
			return query.list();
		}
		finally {
			super.closeSession();
		}
	}
}