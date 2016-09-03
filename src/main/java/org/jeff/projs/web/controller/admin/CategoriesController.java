package org.jeff.projs.web.controller.admin;

import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.CategoryServiceImpl;
import org.jeff.projs.ihbh.utils.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("categories")
public class CategoriesController {
	
	@Autowired
    CategoryServiceImpl categoryService;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Categories");
		model.addAttribute("hold", "categories");
		model.addAttribute("menu", AdminMenu.map);
		CategoryTree tree = categoryService.getTree();
		model.addAttribute("tree", tree);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.category.view";
	}
	
	

}
