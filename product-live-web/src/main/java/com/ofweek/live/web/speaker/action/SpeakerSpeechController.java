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

import com.ofweek.live.core.modules.speaker.entity.SpeakerSpeech;
import com.ofweek.live.core.modules.speaker.service.SpeakerSpeechService;

/**
 * 
 * @author tangqian
 */
@Controller
@RequestMapping(value = "/speaker/speech")
public class SpeakerSpeechController extends BaseController {
	
    @Resource
    private SpeakerSpeechService speakerSpeechService;
    
	@ModelAttribute("speech")
	public SpeakerSpeech get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return speakerSpeechService.get(id);
		}else{
			return new SpeakerSpeech();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SpeakerSpeech speech, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpeakerSpeech> page = speakerSpeechService.findPage(new Page<>(request, response), speech);
        model.addAttribute("page", page);
		return "modules/speaker/speechList";
	}

	@RequestMapping(value = "form")
	public String form(@ModelAttribute("speech") SpeakerSpeech speech, Model model) {
		return "modules/speaker/speechForm";
	}
	
	@RequestMapping(value = "save")
	public String save(SpeakerSpeech speech, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, speech)){
			return form(speech, model);
		}
		speakerSpeechService.save(speech);
		addMessage(redirectAttributes, "保存PPT'" + speech.getName() + "'成功");
		return "redirect:/speaker/speech/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(SpeakerSpeech speech, RedirectAttributes redirectAttributes) {
		speakerSpeechService.delete(speech);
		addMessage(redirectAttributes, "删除PPT成功");
		return "redirect:/speaker/speech/?repage";
	}
	
}
