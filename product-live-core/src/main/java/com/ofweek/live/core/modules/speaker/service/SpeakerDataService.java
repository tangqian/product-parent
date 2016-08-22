package com.ofweek.live.core.modules.speaker.service;

import com.ofweek.live.core.common.utils.FileUtils;
import com.ofweek.live.core.modules.sys.service.SysFileService;
import com.ofweek.live.core.modules.sys.utils.SysFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;

import com.ofweek.live.core.modules.speaker.dao.SpeakerDataDao;
import com.ofweek.live.core.modules.speaker.entity.SpeakerData;

import javax.annotation.Resource;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class SpeakerDataService extends CrudService<SpeakerDataDao, SpeakerData> {



	@Override
	@Transactional(readOnly = false)
	public void save(SpeakerData entity) {
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(SequenceUtils.getNextString("SpeakerData"));
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
    
}
