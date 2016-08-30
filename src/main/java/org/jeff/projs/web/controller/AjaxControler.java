package org.jeff.projs.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("search")
public class AjaxControler {

	@RequestMapping("start")
	public String view(Model model) {

		return "myapp.looseSearch";
	}

	@RequestMapping("looseSearch")
	public @ResponseBody List<Book> search(@RequestParam("CHARS") String chars, Model model) {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Star Treck", "Bettle Bum"));
		 model.addAttribute("books", books);
		return books; //bookService.searchBooksByLooseMatch(chars);
	}
}
