/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ofweek.live.web.sys.action;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ofweek.live.core.common.persistence.Page;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.web.BaseController;
import com.ofweek.live.core.modules.sys.entity.Dict;
import com.ofweek.live.core.modules.sys.entity.SysFile;
import com.ofweek.live.core.modules.sys.service.DictService;
import com.ofweek.live.core.modules.sys.service.SysFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典Controller
 * @author ThinkGem
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "/sys/file")
public class SysFileController extends BaseController {

	@Autowired
	private SysFileService sysFileService;
	
	@ModelAttribute
	public SysFile get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return sysFileService.get(id);
		}else{
			return new SysFile();
		}
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "upload")
	public Map<String, String> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<>();
		SysFile sysFile = null;
		try {
			sysFile = sysFileService.saveTemp(file);
			map.put("fileId", sysFile.getId());
			map.put("status", "0");
		} catch (IOException e) {
			logger.error("上传文件失败,IO异常", e);
			map.put("status", "-1");
		} catch (Exception e) {
			logger.error("上传文件失败，服务器异常", e);
			map.put("status", "-2");
			throw new Exception("uploadify上传发生异常!");
		}
		return map;
	}

}
