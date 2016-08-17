package com.ofweek.live.core.modules.sys.dao;

import com.ofweek.live.core.common.persistence.CrudDao;
import com.ofweek.live.core.common.persistence.annotation.MyBatisDao;
import com.ofweek.live.core.modules.sys.entity.SysFile;

/**
 * 
 * @author tangqian
 */
@MyBatisDao
public interface SysFileDao extends CrudDao<SysFile> {
	
}