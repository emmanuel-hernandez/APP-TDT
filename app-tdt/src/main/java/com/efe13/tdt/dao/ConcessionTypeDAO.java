package com.efe13.tdt.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.util.Utils;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.ConcessionType;
import com.efe13.tdt.utils.AppConstant;

public class ConcessionTypeDAO extends DAOAPI<ConcessionType> {

	public ConcessionTypeDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}
	
	public short findByName( ConcessionType concessionType ) throws HibernateException {
		try {
			Criteria criteria = getCriteria()
				.setProjection( Projections.id() )
				.add( Restrictions.eq( "type", concessionType.getType() ) )
				.add( Restrictions.eq( "active", ActiveEnum.ACTIVE.getValue() ) );

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