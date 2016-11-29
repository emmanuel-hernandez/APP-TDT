package com.efe13.tdt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Channel;

public class ChannelDAO extends DAOAPI<Channel> {

	@Override
	public Channel getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( "FROM Channel WHERE channelId = :channelId" );
		query.setParameter("channelId", object.getId() );
		
		return (Channel) query.uniqueResult();
	}

}