package org.jeff.projs.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.TreeNodeDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
		return "admin.category.view";
	}
	
	@RequestMapping("getTree")
	public @ResponseBody List<TreeNodeDto> search(Model model) {
		ArrayList<TreeNodeDto> list = new ArrayList<TreeNodeDto>();
		TreeNodeDto node = new TreeNodeDto();
		node.setId("1");
		node.setLabel("1st Node");
		node.setInode(false);
		node.setOpen(false);
		list.add(node);
		
		node = new TreeNodeDto();
		node.setId("2");
		node.setLabel("2nd Node");
		node.setInode(true);
		node.setOpen(false);
		
			TreeNodeDto nodeInner = new TreeNodeDto();
			nodeInner.setId("3");
			nodeInner.setLabel("1st Child  2nd Node");
			nodeInner.setInode(false);
			nodeInner.setOpen(false);		
			node.addBranch(nodeInner);
		list.add(node);

		
	//	System.out.println(retValue);
		return list;
	}
	
	

}
