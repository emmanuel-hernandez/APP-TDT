package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.ChannelBand;

public class ChannelBandDAO extends DAOAPI<ChannelBand> {

	@Override
	public ChannelBand getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM ChannelBand WHERE channelBandId = :channelBandId" );
		query.setParameter("channelBandId", object.getId() );
		
		return (ChannelBand) query.uniqueResult();
	}

}