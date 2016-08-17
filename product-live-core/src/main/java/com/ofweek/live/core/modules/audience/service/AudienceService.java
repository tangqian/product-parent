package com.ofweek.live.core.modules.audience.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.modules.audience.dao.AudienceDao;
import com.ofweek.live.core.modules.audience.entity.Audience;
import com.ofweek.live.core.modules.rpc.dto.AddressDto;
import com.ofweek.live.core.modules.rpc.dto.UserDto;
import com.ofweek.live.core.modules.sys.entity.User;
import com.ofweek.live.core.modules.sys.enums.SexEnum;
import com.ofweek.live.core.modules.sys.enums.UserTypeEnum;
import com.ofweek.live.core.modules.sys.service.UserService;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class AudienceService extends CrudService<AudienceDao, Audience> {

	@Resource
	private UserService userService;
	
	/**
	 * 不支持此操作，勿调用
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(Audience entity) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param userDto
	 */
	@Transactional(readOnly = false)
	public Audience save(UserDto userDto) {
		User exsitUser = userService.get(userDto.getAccount(), UserTypeEnum.AUDIENCE.getCode());
		if (exsitUser == null) {
			Audience audience = from(userDto);

			User newUser = audience.getUser();
			userService.save(newUser);
			audience.setId(newUser.getId());
			audience.preInsert();
			dao.insert(audience);
			return audience;
		} else {// 切记此处不要更新EXH_AUDIENCE中的数据，因为从资讯网中传过来的name,company等关键字段可能为空，会冲掉观众在直播系统填的登记信息
			String newEmail = userDto.getEmail();
			if (StringUtils.isNotBlank(newEmail) && !newEmail.equals(exsitUser.getEmail())) {
				User updateUser = new User(exsitUser.getId());
				updateUser.setEmail(newEmail);
				userService.save(updateUser);

				exsitUser.setEmail(newEmail);
			}
			Audience audience = get(exsitUser.getId());
			audience.setUser(exsitUser);
			return audience;
		}

	}

	private Audience from(UserDto userDto) {
		Audience audience = new Audience();

		User user = new User();
		user.setType(UserTypeEnum.AUDIENCE.getCode());
		user.setAccount(userDto.getAccount());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		audience.setUser(user);

		audience.setCompany(userDto.getCompany());
		audience.setJob(userDto.getJob());
		audience.setName(userDto.getName());
		audience.setSex(SexEnum.fromOfweek(userDto.getSex()).getCode());
		audience.setTelephone(userDto.getTelephone());
		audience.setMobilePhone(userDto.getMobilePhone());
		audience.setDepartment(userDto.getDepartment());
		audience.setFax(userDto.getFax());

		AddressDto addressDto = userDto.getAddress();
		audience.setCountry(addressDto.getCountry());
		audience.setProvince(addressDto.getProvince());
		audience.setAddress(addressDto.getDetail());
		
		audience.setBizType(userDto.getBizType());
		return audience;
	}

}
