package org.jeff.projs.ihbh.utils;

public class SessionUtil {
	private String selectedMenu;
	HtmlMessageUtils htmlMessages;
	
	public String getSelectedMenu() {
		return selectedMenu;
	}
	public HtmlMessageUtils getHtmlMessages() {
		return htmlMessages;
	}
	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}
	public void setHtmlMessages(HtmlMessageUtils htmlMessages) {
		this.htmlMessages = htmlMessages;
	}
	
	
}
