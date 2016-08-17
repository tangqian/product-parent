package com.ofweek.live.core.modules.speaker.dao;

import com.ofweek.live.core.common.persistence.CrudDao;
import com.ofweek.live.core.common.persistence.annotation.MyBatisDao;
import com.ofweek.live.core.modules.speaker.entity.Speaker;

/**
 * 
 * @author tangqian
 */
@MyBatisDao
public interface SpeakerDao extends CrudDao<Speaker> {
	
}