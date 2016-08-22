package com.ofweek.live.core.modules.speaker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;

import com.ofweek.live.core.modules.speaker.dao.SpeakerVideoDao;
import com.ofweek.live.core.modules.speaker.entity.SpeakerVideo;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class SpeakerVideoService extends CrudService<SpeakerVideoDao, SpeakerVideo> {
	
	@Override
	@Transactional(readOnly = false)
	public void save(SpeakerVideo entity) {
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(SequenceUtils.getNextString("SpeakerVideo"));
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
    
}
