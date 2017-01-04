package com.efe13.tdt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.util.Utils;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Population;
import com.efe13.tdt.model.entity.State;
import com.efe13.tdt.utils.AppConstant;

public class PopulationDAO extends DAOAPI<Population> {

	public PopulationDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}

	public short findByNameAndState( Population population ) throws HibernateException {
		try {
			Criteria criteria = getCriteria( "p" )
				.setProjection( Projections.id() )
				.createAlias( "state", "s", JoinType.INNER_JOIN )
				.add( Restrictions.eq( "p.name", population.getName() ) )
				.add( Restrictions.eq( "s.id", population.getState().getId() ) )
				.add( Restrictions.eq( "p.active", ActiveEnum.ACTIVE.getValue() ) );

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
	
	public List<EntityAPI> getByState( State state ) throws HibernateException {
		try {
			Criteria criteria = getCriteria( "p" )
				.createAlias( "state", "s", JoinType.INNER_JOIN )
				.add( Restrictions.eq( "s.id", state.getId() ) )
				.add( Restrictions.eq( "p.active", ActiveEnum.ACTIVE.getValue() ) );

			return criteria.list();
		}
		finally {
			closeSession();
		}
	}
}