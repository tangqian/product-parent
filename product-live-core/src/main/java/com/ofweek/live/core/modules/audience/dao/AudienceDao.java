package com.ofweek.live.core.modules.audience.dao;

import com.ofweek.live.core.common.persistence.CrudDao;
import com.ofweek.live.core.common.persistence.annotation.MyBatisDao;
import com.ofweek.live.core.modules.audience.entity.Audience;

/**
 * 
 * @author tangqian
 */
@MyBatisDao
public interface AudienceDao extends CrudDao<Audience> {
	
}