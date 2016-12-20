package com.efe13.tdt.dao;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.Concessionaire;
import com.efe13.tdt.utils.AppConstant;

public class ConcessionaireDAO extends DAOAPI<Concessionaire> {
	
	public ConcessionaireDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}
}