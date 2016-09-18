package org.jeff.projs.web.controller;

import java.util.LinkedHashMap;

import org.springframework.ui.Model;

public abstract class AbstractMVCController {

	protected void addErrorMessage(String message, Model model){
		model.addAttribute("messageType", "error");
		model.addAttribute("message", message);
	}

	protected void addWaringMessage(String message, Model model){
		model.addAttribute("messageType", "warning");
		model.addAttribute("message", message);
	}

	protected void addInfoMessage(String message, Model model){
		model.addAttribute("messageType", "info");
		model.addAttribute("message", message);
	}
	
	protected void setMenuInfo(String table, String active, Model model, LinkedHashMap<String, String> menu){
		model.addAttribute("table", table);
		model.addAttribute("active", active);
		model.addAttribute("menu", menu);

	}
}
