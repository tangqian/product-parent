package com.ofweek.live.web.speaker.action;

import com.ofweek.live.core.common.persistence.Page;
import com.ofweek.live.core.common.utils.StringUtils;
import com.ofweek.live.core.common.web.BaseController;
import com.ofweek.live.core.modules.speaker.entity.SpeakerData;
import com.ofweek.live.core.modules.speaker.service.SpeakerDataService;
import com.ofweek.live.core.modules.sys.entity.SysFile;
import com.ofweek.live.core.modules.sys.utils.SysFileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tangqian
 *
 */
@Controller
@RequestMapping(value = "/speaker/data")
public class SpeakerDataController extends BaseController {

    @Resource
    private SpeakerDataService speakerDataService;

    @ModelAttribute("data")
    public SpeakerData get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return speakerDataService.get(id);
        }else{
            return new SpeakerData();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(SpeakerData data, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SpeakerData> page = speakerDataService.findPage(new Page<>(request, response), data);
        model.addAttribute("page", page);
        return "modules/speaker/dataList";
    }

    @RequestMapping(value = "form")
    public String form(@ModelAttribute("data") SpeakerData data, Model model) {
        return "modules/speaker/dataForm";
    }

    @RequestMapping(value = "save")
    public String save(SpeakerData data, Model model, RedirectAttributes redirectAttributes) throws IOException {
        if (!beanValidator(model, data)){
            return form(data, model);
        }
        SysFile sysFile = sysFileService.save(data.getFileId(), SysFileUtils.TypeEnum.SPEAKER_SPEECH);
        data.setFileId(sysFile.getId());
        data.setName(sysFile.getOriginalName());
        speakerDataService.save(data);
        addMessage(redirectAttributes, "保存下载资料'" + data.getName() + "'成功");
        return "redirect:/speaker/data/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(SpeakerData data, RedirectAttributes redirectAttributes) {
        speakerDataService.delete(data);
        addMessage(redirectAttributes, "删除下载资料成功");
        return "redirect:/speaker/data/?repage";
    }

}

