package com.ofweek.live.web.speaker.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ofweek.live.core.common.persistence.Page;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.web.BaseController;

import com.ofweek.live.core.modules.speaker.entity.SpeakerVideo;
import com.ofweek.live.core.modules.speaker.service.SpeakerVideoService;

/**
 * 
 * @author tangqian
 */
@Controller
@RequestMapping(value = "/speaker/video")
public class SpeakerVideoController extends BaseController {
	
    @Resource
    private SpeakerVideoService speakerVideoService;
    
	@ModelAttribute("video")
	public SpeakerVideo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return speakerVideoService.get(id);
		}else{
			return new SpeakerVideo();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SpeakerVideo video, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpeakerVideo> page = speakerVideoService.findPage(new Page<>(request, response), video);
        model.addAttribute("page", page);
		return "modules/speaker/videoList";
	}

	@RequestMapping(value = "form")
	public String form(@ModelAttribute("video") SpeakerVideo video, Model model) {
		return "modules/speaker/videoForm";
	}
	
	@RequestMapping(value = "save")
	public String save(SpeakerVideo video, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, video)){
			return form(video, model);
		}
		speakerVideoService.save(video);
		addMessage(redirectAttributes, "保存视频'" + video.getName() + "'成功");
		return "redirect:/speaker/video/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(SpeakerVideo video, RedirectAttributes redirectAttributes) {
		speakerVideoService.delete(video);
		addMessage(redirectAttributes, "删除视频成功");
		return "redirect:/speaker/video/?repage";
	}
	
}
