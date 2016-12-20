package com.efe13.tdt.dao;

import com.efe13.mvc.commons.api.enums.ActiveEnum;
import com.efe13.mvc.dao.api.impl.DAOAPI;
import com.efe13.tdt.model.entity.State;
import com.efe13.tdt.utils.AppConstant;

public class StateDAO extends DAOAPI<State> {

	public StateDAO() {
		super( AppConstant.ACTIVE_COLUMN_NAME, ActiveEnum.ACTIVE );
	}
}