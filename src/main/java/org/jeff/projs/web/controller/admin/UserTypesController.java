package org.jeff.projs.web.controller.admin;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.UserTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("userTypes")
public class UserTypesController {

	@Autowired
    UserTypeServiceImpl userTypeService;
	
    // Map requests to product Page
	@RequestMapping("view")
    public String view(Model model){
		model.addAttribute("table", "User Types" );
		model.addAttribute("hold","userTypes");
		List<UserTypeDto> list = userTypeService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
        return "admin.userTypes.view";
    }
	
	@RequestMapping(value = "update", method = RequestMethod.GET, params = {"id"})
    public String update(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		UserTypeDto userTypeDto = userTypeService.getUserTypeById(id);
		model.addAttribute("userTypeForm", userTypeDto);
		return "admin.userTypeUpdate";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("SpringWeb")UserTypeDto UserTypeDto, Model model) {
		userTypeService.save(UserTypeDto);
		List<UserTypeDto> list = userTypeService.getAll();
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Updated");
		model.addAttribute("data", list);
		model.addAttribute("hold", "userType");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.userTypes.view";
    }
	
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = {"id"})
    public String cancel(Model model) {
		model.addAttribute("table", "User Types");
		model.addAttribute("hold", "userTypes");
		List<UserTypeDto> list = userTypeService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.userTypes.view";
    }

	@RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public String delete(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		userTypeService.delete(id);
		model.addAttribute("table", "User Type");
		model.addAttribute("hold", "userType");
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Deleted");
		List<UserTypeDto> list = userTypeService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "redirect:/userTypes/view";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
		UserTypeDto userTypeDto = new UserTypeDto();
		model.addAttribute("userTypeForm", userTypeDto);
		return "admin.userTypeUpdate";
    }
}