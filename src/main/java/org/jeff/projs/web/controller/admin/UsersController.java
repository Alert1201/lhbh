package org.jeff.projs.web.controller.admin;

import org.jeff.projs.ihbh.model.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UsersController {

	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Users");
		model.addAttribute("hold","users");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.usersView";
	}
}