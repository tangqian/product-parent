package com.ofweek.live.core.modules.speaker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;

import com.ofweek.live.core.modules.speaker.dao.SpeakerSpeechDao;
import com.ofweek.live.core.modules.speaker.entity.SpeakerSpeech;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class SpeakerSpeechService extends CrudService<SpeakerSpeechDao, SpeakerSpeech> {
	
	@Override
	@Transactional(readOnly = false)
	public void save(SpeakerSpeech entity) {
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(SequenceUtils.getNextString("SpeakerSpeech"));
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
    
}
