package com.efe13.tdt.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.Population;
import com.efe13.tdt.utils.AppConstant;

public class PopulationDAO extends DAOAPI<Population> {

	public PopulationDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}

	public int findByNameAndState( Population population ) throws HibernateException {
		try {
			Criteria criteria = getCriteria( "p" )
				.setProjection( Projections.rowCount() )
				.createAlias( "state", "s", JoinType.INNER_JOIN )
				.add( Restrictions.eq( "p.name", population.getName() ) )
				.add( Restrictions.eq( "s.id", population.getState().getId() ) );
			
			return (Integer) criteria.uniqueResult();
		}
		finally {
			closeSession();
		}
	}
}