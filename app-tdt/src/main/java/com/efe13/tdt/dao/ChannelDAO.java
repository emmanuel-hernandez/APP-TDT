package com.efe13.tdt.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.util.Utils;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.Channel;
import com.efe13.tdt.utils.AppConstant;

public class ChannelDAO extends DAOAPI<Channel> {
	
	private final SimpleExpression ACTIVE_RESTRICTION = Restrictions.eq( "active", ActiveEnum.ACTIVE.getValue() );
	
	public ChannelDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}
	
	public short findByDistinctive( Channel channel ) throws HibernateException {
		try {
			Criteria criteria = getCriteria()
				.setProjection( Projections.id() )
				.add( Restrictions.eq( "distinctive", channel.getDistinctive() ) )
				.add( ACTIVE_RESTRICTION );

			Object object = criteria.uniqueResult();

			if( !Utils.isNull( object  ) ) {
				return (short) object;
			}
			
			return 0;
		}
		finally {
			closeSession();
		}
	}
	
	public short findByName( Channel channel ) throws HibernateException {
		try {
			Criteria criteria = getCriteria()
				.setProjection( Projections.id() )
				.add( Restrictions.eq( "name", channel.getName() ) )
				.add( ACTIVE_RESTRICTION );

			Object object = criteria.uniqueResult();

			if( !Utils.isNull( object ) ) {
				return (short) object;
			}

			return 0;
		}
		finally {
			closeSession();
		}
	}
	
	public short findByPhysicChannel( Channel channel ) throws HibernateException {
		try {
			Criteria criteria = getCriteria( "c" )
				.setProjection( Projections.id() )
				.createAlias( "population", "p", JoinType.INNER_JOIN )
				.add( Restrictions.eq( "c.physicChannel", channel.getPhysicChannel() ) )
				.add( Restrictions.eq( "p.id", channel.getPopulation().getId() ) )
				.add( ACTIVE_RESTRICTION );

			Object object = criteria.uniqueResult();

			if( !Utils.isNull( object  ) ) {
				return (short) object;
			}
			
			return 0;
		}
		finally {
			closeSession();
		}
	}
	
	public short findByVirtualChannel( Channel channel ) throws HibernateException {
		try {
			Criteria criteria = getCriteria( "c" )
				.setProjection( Projections.id() )
				.createAlias( "population", "p", JoinType.INNER_JOIN )
				.add( Restrictions.eq( "c.virtualChannel", channel.getVirtualChannel() ) )
				.add( Restrictions.eq( "p.id", channel.getPopulation().getId() ) )
				.add( ACTIVE_RESTRICTION );

			Object object = criteria.uniqueResult();

			if( !Utils.isNull( object  ) ) {
				return (short) object;
			}
			
			return 0;
		}
		finally {
			closeSession();
		}
	}
}
