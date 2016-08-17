package com.ofweek.live.core.modules.sys.service;

import java.io.File;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ofweek.live.core.common.config.LiveEnv;
import com.ofweek.live.core.common.service.CrudService;
import com.ofweek.live.core.common.utils.DateUtils;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.modules.sys.dao.SysFileDao;
import com.ofweek.live.core.modules.sys.entity.SysFile;
import com.ofweek.live.core.modules.sys.entity.User;
import com.ofweek.live.core.modules.sys.utils.FileServiceUtils;
import com.ofweek.live.core.modules.sys.utils.RandomUtils;
import com.ofweek.live.core.modules.sys.utils.SequenceUtils;
import com.ofweek.live.core.modules.sys.utils.SysFileUtils.TypeEnum;
import com.ofweek.live.core.modules.sys.utils.UserUtils;

/**
 * 
 * @author tangqian
 */
@Service
@Transactional(readOnly = true)
public class SysFileService extends CrudService<SysFileDao, SysFile> {

	@Override
	@Transactional(readOnly = false)
	public void save(SysFile entity) {
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(SequenceUtils.getNextString("SysFile"));
			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

	public SysFile add(MultipartFile file, TypeEnum typeEnum, boolean isTemp) {
		if (file == null || file.getSize() == 0)
			return null;

		String fileName = file.getOriginalFilename();
		String ext = FileServiceUtils.getExt(fileName);

		String relativePath = generatePath(typeEnum);
		if (isTemp) {
			relativePath = "temp/" + relativePath;
		}
		File targetFile = generateNewFile(relativePath, ext);

		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			logger.error("保存文件失败", e);
		}

		SysFile entity = new SysFile();
		entity.setId(SequenceUtils.getNextString("SysFile"));
		entity.setUri(relativePath + targetFile.getName());
		entity.setOriginalName(fileName);
		entity.setSize((int) targetFile.length());
		entity.setExt(ext);
		entity.setIsTemp(isTemp ? 1 : 0);
		entity.setSubjectType(typeEnum.getCode());

		User user = UserUtils.getUser();
		entity.setCreateBy(user == null ? "0" : user.getId());
		entity.preInsert();
		dao.insert(entity);
		return entity;
	}

	public String getAbsolutePath(String relativePath) {
		return LiveEnv.getUploadRoot() + relativePath;
	}

	private File generateNewFile(String relativePath, String ext) {
		String newName = generateRandomName(ext);
		File targetFile = new File(getAbsolutePath(relativePath), newName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		} else {
			while (targetFile.exists()) {
				newName = generateRandomName(ext);
				targetFile = new File(getAbsolutePath(relativePath), newName);
			}
		}
		return targetFile;
	}

	private String generatePath(TypeEnum typeEnum) {
		Date now = new Date();
		StringBuilder sb = new StringBuilder(typeEnum.getClassifyDir()).append('/').append(DateUtils.formatDate(now, "yyyy-MM")).append('/');
		return sb.toString();
	}

	private String generateRandomName(String ext) {
		return StringUtils.isEmpty(ext) ? RandomUtils.getRandomString(10) : RandomUtils.getRandomString(10) + "." + ext;
	}
}
