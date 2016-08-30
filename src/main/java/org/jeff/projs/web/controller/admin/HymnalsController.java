package org.jeff.projs.web.controller.admin;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.HymnalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("hymnals")
public class HymnalsController {
	
	@Autowired
    HymnalServiceImpl hymnalService;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Hymnals");
		model.addAttribute("hold", "hymnals");
		List<HymnalDto> list = hymnalService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.hymnals.view";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET, params = {"id"})
    public String update(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		HymnalDto hymnalDto = hymnalService.getHymnalById(id);
		model.addAttribute("hymnalForm", hymnalDto);
		return "admin.hymnalUpdate";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("SpringWeb")HymnalDto hymnalDto, Model model) {
		hymnalService.save(hymnalDto);
		List<HymnalDto> list = hymnalService.getAll();
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Updated");
		model.addAttribute("data", list);
		model.addAttribute("hold", "hymnals");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.hymnals.view";
    }
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = {"id"})
    public String cancel(Model model) {
		model.addAttribute("table", "Hymnal");
		model.addAttribute("hold", "hymnals");
		List<HymnalDto> list = hymnalService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.hymnals.view";
    }

	@RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public String delete(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		hymnalService.delete(id);
		model.addAttribute("table", "Hymnals");
		model.addAttribute("hold", "hymnals");
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Deleted");
		List<HymnalDto> list = hymnalService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "redirect:/hymnals/view";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
		HymnalDto hymnalDto = new HymnalDto();
		model.addAttribute("hymnalForm", hymnalDto);
		return "admin.hymnalUpdate";
    }
}
