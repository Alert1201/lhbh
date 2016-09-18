package org.jeff.projs.web.controller.admin;

import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.MeterServiceImpl;
import org.jeff.projs.web.controller.AbstractMVCController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("meters")
public class MetersController extends AbstractMVCController {

	@Autowired
	MeterServiceImpl meterService;

	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("data", meterService.getAll());
		setMenuInfo("Meters", "meters", model, AdminMenu.map);
		return "admin.meter.view";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET, params = { "id" })
	public String update(@RequestParam(value = "id", required = true, defaultValue = "0") int id, Model model) {
		MeterDto meterDto = meterService.getById(id);
		model.addAttribute("meterForm", meterDto);
		return "admin.metersUpdate";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("SpringWeb") MeterDto meterDto, Model model) {
		meterService.save(meterDto);
		addInfoMessage("Record saved", model);
		model.addAttribute("data", meterService.getAll());
		setMenuInfo("Meters", "meters", model, AdminMenu.map);
		return "admin.meter.view";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = { "id" })
	public String cancel(Model model) {
		addInfoMessage("Record not saved", model);
		model.addAttribute("data", meterService.getAll());
		setMenuInfo("Meters", "meters", model, AdminMenu.map);
		return "admin.meter.view";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET, params = { "id" })
	public String delete(@RequestParam(value = "id", required = true, defaultValue = "0") int id, Model model) {
		int retValue = meterService.delete(id);
		if (retValue == -1) {
			addErrorMessage("Cannot delete. Meter referenced in other tables", model);
		} else {
			addInfoMessage("Record Deleted", model);
		}
		model.addAttribute("data", meterService.getAll());
		setMenuInfo("Meters", "meters", model, AdminMenu.map);
		return "admin.meter.view";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		addInfoMessage("Record added", model);
		model.addAttribute("meterForm", new MeterDto());
		return "admin.metersUpdate";
	}
}