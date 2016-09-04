package org.jeff.projs.web.controller.admin;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.MeterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("meters")
public class MetersController {
	
	@Autowired
    MeterServiceImpl meterService;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Meters");
		model.addAttribute("hold", "meters");	
		List<MeterDto> list = meterService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.meter.view";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET, params = {"id"})
    public String update(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		MeterDto meterDto = meterService.getById(id);
		model.addAttribute("meterForm", meterDto);
		return "admin.metersUpdate";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("SpringWeb")MeterDto meterDto, Model model) {
		meterService.save(meterDto);
		List<MeterDto> list = meterService.getAll();
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Updated");
		model.addAttribute("data", list);
		model.addAttribute("hold", "meters");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.meter.view";
    }
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = {"id"})
    public String cancel(Model model) {
		model.addAttribute("table", "Meters");
		model.addAttribute("hold", "meters");
		List<MeterDto> list = meterService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.meter.view";
    }

	@RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public String delete(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		int retValue = meterService.delete(id);
		if(retValue==-1){
			model.addAttribute("error", "Cannot delete. Meter referenced in other tables");
		}
		model.addAttribute("table", "Meters");
		model.addAttribute("hold", "meters");
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Deleted");
		List<MeterDto> list = meterService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "redirect:/meters/view";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
		MeterDto meterDto = new MeterDto();
		model.addAttribute("meterForm", meterDto);
		return "admin.metersUpdate";
    }
}