package org.jeff.projs.web.controller.admin;

import org.jeff.projs.ihbh.model.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class StartController {
	
	@RequestMapping("start")
	public String view(Model model) {
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Select Menu Option");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.start.view";
	}
}
