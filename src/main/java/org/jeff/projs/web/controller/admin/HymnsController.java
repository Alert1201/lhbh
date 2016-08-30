package org.jeff.projs.web.controller.admin;

import org.jeff.projs.ihbh.model.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hymns")
public class HymnsController {
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Hymns");
		model.addAttribute("hold", "hymns");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.hymnsView";
	}

}
