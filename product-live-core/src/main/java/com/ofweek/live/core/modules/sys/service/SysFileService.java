package com.ofweek.live.core.modules.sys.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ofweek.live.core.common.utils.FileUtils;
import com.ofweek.live.core.modules.sys.utils.*;
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
import com.ofweek.live.core.modules.sys.utils.SysFileUtils.TypeEnum;

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
		throw new UnsupportedOperationException();
	}

	@Transactional(readOnly = false)
	public SysFile save(String tempId, TypeEnum typeEnum) throws IOException {
		SysFile tempFile = get(tempId);
		if(SysFileUtils.TempEnum.isNotTemp(tempFile.getIsTemp())){//非临时文件，直接返回
			return tempFile;
		}

		String targetId = SequenceUtils.getNextString("SysFile");
		String targetUri = generatePath(typeEnum) + generateNewName(targetId, tempFile.getExt());
		FileUtils.copyFile(getAbsolutePath(tempFile.getUri()), targetUri);

		SysFile entity = new SysFile();
		entity.setId(targetId);
		entity.setUri(targetUri);
		entity.setOriginalName(tempFile.getOriginalName());
		entity.setSize(tempFile.getSize());
		entity.setExt(tempFile.getExt());
		entity.setIsTemp(SysFileUtils.TempEnum.NO.getCode());
		entity.setType(typeEnum.getFileType());
		entity.setSubjectType(typeEnum.getCode());

		entity.preInsert();
		dao.insert(entity);
		return entity;
	}

	@Transactional(readOnly = false)
	public SysFile saveTemp(MultipartFile file) throws IOException {
		if (file == null || file.getSize() == 0)
			return null;

		String fileName = file.getOriginalFilename();
		String ext = FileServiceUtils.getExt(fileName);

		String relativePath = "temp/" + DateUtils.formatDate(new Date(), "yyyy-MM/");
		String id = SequenceUtils.getNextString("SysFile");
		String newFileName = generateNewName(id, ext);

		File targetFile = new File(getAbsolutePath(relativePath), newFileName);
		targetFile.mkdirs();
		file.transferTo(targetFile);

		SysFile entity = new SysFile();
		entity.setId(id);
		entity.setUri(relativePath + newFileName);
		entity.setOriginalName(fileName);
		entity.setSize((int) file.getSize());
		entity.setExt(ext);
		entity.setIsTemp(SysFileUtils.TempEnum.YES.getCode());
		entity.setType(0);
		entity.setSubjectType(-1);

		entity.preInsert();
		dao.insert(entity);
		return entity;
	}

	private String generateNewName(String id, String ext) {
		String timeIdStr = id + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		return StringUtils.isEmpty(ext) ? timeIdStr : timeIdStr + "." + ext;
	}

	public String getAbsolutePath(String relativePath) {
		return LiveEnv.getUploadRoot() + relativePath;
	}

	/**
	 * 生成存放路径，格式=分类名/yyyy-MM/ ,例audience/logo/2016-08
	 * @param typeEnum
	 * @return
	 */
	private String generatePath(TypeEnum typeEnum) {
		StringBuilder sb = new StringBuilder(typeEnum.getClassifyDir()).append('/').append(DateUtils.formatDate(new Date(), "yyyy-MM/"));
		return sb.toString();
	}

	private String generateRandomName(String ext) {
		return StringUtils.isEmpty(ext) ? RandomUtils.getRandomString(10) : RandomUtils.getRandomString(10) + "." + ext;
	}
}
