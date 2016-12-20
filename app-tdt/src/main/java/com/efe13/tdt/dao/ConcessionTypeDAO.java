package com.efe13.tdt.dao;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.ConcessionType;
import com.efe13.tdt.utils.AppConstant;

public class ConcessionTypeDAO extends DAOAPI<ConcessionType> {

	public ConcessionTypeDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}
}