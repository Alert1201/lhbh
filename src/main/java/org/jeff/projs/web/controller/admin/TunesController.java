package org.jeff.projs.web.controller.admin;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.LookupServiceImpl;
import org.jeff.projs.ihbh.services.impl.TuneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("tunes")
public class TunesController {
	
	@Autowired
	 TuneServiceImpl tuneService;
	@Autowired
	LookupServiceImpl lookupService;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Tunes");
		model.addAttribute("hold", "tunes");
		List<TuneDto> list = tuneService.getWholeTunes();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.tunes.view";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET, params = {"id"})
    public String update(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		TuneDto tuneDto = tuneService.getById(id);
		model.addAttribute("authorList",lookupService.populateAuthors());
		model.addAttribute("meterList",lookupService.populateMeters());
		model.addAttribute("tuneForm", tuneDto);
		return "admin.tuneUpdate";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("SpringWeb")TuneDto tuneDto, Model model) {
		tuneService.save(tuneDto);
		List<TuneDto> list = tuneService.getWholeTunes();
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Updated");
		model.addAttribute("data", list);
		model.addAttribute("hold", "tune");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.tunes.view";
    }
	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = {"id"})
    public String cancel(Model model) {
		model.addAttribute("table", "Tunes");
		model.addAttribute("hold", "tunes");
		List<TuneDto> list = tuneService.getWholeTunes();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.tunes.view";
    }
	
	@RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public String delete(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		tuneService.delete(id);
		model.addAttribute("table", "User Type");
		model.addAttribute("hold", "tune");
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Deleted");
		List<TuneDto> list = tuneService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "redirect:/tunes/view";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
		TuneDto tuneDto = new TuneDto();
		model.addAttribute("tuneForm", tuneDto);
		model.addAttribute("authorList",lookupService.populateAuthors());
		model.addAttribute("meterList",lookupService.populateMeters());
		return "admin.tuneUpdate";
    }
	
}
