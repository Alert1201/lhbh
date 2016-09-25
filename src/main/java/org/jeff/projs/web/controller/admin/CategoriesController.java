package org.jeff.projs.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.TreeNodeDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.CategoryServiceImpl;
import org.jeff.projs.web.controller.AbstractMVCController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("categories")
public class CategoriesController extends AbstractMVCController  {
	
	@Autowired
    CategoryServiceImpl categoryService;
	
	@RequestMapping("view")
	public String view(Model model) {
		setMenuInfo("Categories", "categories", model, AdminMenu.map);
		return "admin.category.view";
	}
	
	@RequestMapping("getTree")
	public @ResponseBody List<TreeNodeDto> getTree(Model model) {
		model.addAttribute("table", "Categories");
		setMenuInfo("Categories", "categories", model, AdminMenu.map);
		List<TreeNodeDto> list = new ArrayList<TreeNodeDto>();
		list = categoryService.buildJsonTree(list, 0, true);	
		return list;
	}
	
	@RequestMapping("removeItem")
	public @ResponseBody List<TreeNodeDto> removeItem(Model model, 
			@RequestParam("id") String id,
			@RequestParam("parId") String parId,
			@RequestParam("levelOrder") String levelOrder) {
		model.addAttribute("table", "Categories");
		setMenuInfo("Categories", "categories", model, AdminMenu.map);
		categoryService.removeTreeViewNode(Integer.valueOf(id).intValue(), 
											Integer.valueOf(parId).intValue(), 
											Integer.valueOf(levelOrder).intValue());
		List<TreeNodeDto> list = new ArrayList<TreeNodeDto>();
		list = categoryService.buildJsonTree(list, 0, true);	
		return list;
	}
	

}
