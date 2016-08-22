package com.ofweek.live.core.modules.speaker.dao;

import com.ofweek.live.core.common.persistence.CrudDao;
import com.ofweek.live.core.common.persistence.annotation.MyBatisDao;
import com.ofweek.live.core.modules.speaker.entity.SpeakerVideo;

/**
 * 
 * @author tangqian
 */
@MyBatisDao
public interface SpeakerVideoDao extends CrudDao<SpeakerVideo> {
	
}