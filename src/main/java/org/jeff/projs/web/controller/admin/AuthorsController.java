package org.jeff.projs.web.controller.admin;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.model.AdminMenu;
import org.jeff.projs.ihbh.services.impl.AuthorServiceImpl;
import org.jeff.projs.ihbh.services.impl.LookupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("authors")
public class AuthorsController {

	@Autowired
    AuthorServiceImpl authorService;
	@Autowired
    LookupServiceImpl lookupService;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("table", "Authors");
		model.addAttribute("hold", "authors");
		List<AuthorDto> list = authorService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.author.view";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET, params = {"id"})
    public String update(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		AuthorDto authorDto = authorService.getById(id);
		model.addAttribute("monthList",lookupService.getMonths());
		model.addAttribute("authorForm", authorDto);
		return "admin.authorsUpdate";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("SpringWeb")AuthorDto authorDto, Model model) {
		authorService.save(authorDto);
		List<AuthorDto> list = authorService.getAll();
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Updated");
		model.addAttribute("data", list);
		model.addAttribute("hold", "author");
		model.addAttribute("menu", AdminMenu.map);
		return "admin.author.view";
    }
	
	@RequestMapping(value = "cancel", method = RequestMethod.POST, params = {"id"})
    public String cancel(Model model) {
		model.addAttribute("table", "Authors");
		model.addAttribute("hold", "authors");
		List<AuthorDto> list = authorService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "admin.author.view";
    }

	@RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public String delete(@RequestParam(value="id", required=true, defaultValue="0") int id, Model model) {
		authorService.delete(id);
		model.addAttribute("table", "Authors");
		model.addAttribute("hold", "author");
		model.addAttribute("messageType", "info");
		model.addAttribute("message", "Record Deleted");
		List<AuthorDto> list = authorService.getAll();
		model.addAttribute("data", list);
		model.addAttribute("menu", AdminMenu.map);
		return "redirect:/authors/view";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
		AuthorDto authorDto = new AuthorDto();
		model.addAttribute("authorForm", authorDto);
		model.addAttribute("monthList",lookupService.getMonths());
		return "admin.authorsUpdate";
    }
}
