package com.efe13.tdt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.commons.api.exception.DAOException;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.mvc.model.api.impl.entity.EntityAPI;
import com.efe13.tdt.model.entity.Concessionaire;

public class ConcessionaireDAO extends DAOAPI<Concessionaire> {
	
	private final static String ACTIVE_CONDITION = "AND active = " + ActiveEnum.ACTIVE.getValue();
	private final static String QUERY_GET_BY_ID = "FROM Concessionaire WHERE concessionaireId = :concessionaireId " + ACTIVE_CONDITION;
	private final static String QUERY_GET_ALL = "FROM Concessionaire WHERE 1=1 " + ACTIVE_CONDITION;

	@Override
	public Concessionaire getById( EntityAPI object ) throws HibernateException, DAOException {
		Query query = getSession().createQuery( QUERY_GET_BY_ID );
		query.setParameter( "concessionaireId", object.getId() );
		
		return (Concessionaire) query.uniqueResult();
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