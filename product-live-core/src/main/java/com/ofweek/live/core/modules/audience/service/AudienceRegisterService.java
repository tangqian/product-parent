package com.ofweek.live.core.modules.audience.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.modules.audience.dao.AudienceRegisterDao;
import com.ofweek.live.core.modules.audience.entity.AudienceRegister;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class AudienceRegisterService extends CrudService<AudienceRegisterDao, AudienceRegister> {
	
	@Override
	@Transactional(readOnly = false)
	public void save(AudienceRegister entity) {
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(SequenceUtils.getNextString("LiveAudienceRegister"));
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
    
}
