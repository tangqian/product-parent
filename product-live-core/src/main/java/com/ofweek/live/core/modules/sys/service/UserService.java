package com.ofweek.live.core.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.modules.sys.dao.UserDao;
import com.ofweek.live.core.modules.sys.entity.User;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {
	
	@Override
	@Transactional(readOnly = false)
	public void save(User entity) {
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(SequenceUtils.getNextString("LiveUser"));
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 根据账号和类型获取用户
	 * @param account
	 * @param type
	 * @return
	 */
	public User get(String account, Integer type) {
		return dao.getByAccount(account, type);
	}
	
    
}
