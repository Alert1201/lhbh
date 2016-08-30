package org.jeff.projs.ihbh.utils;

public class HtmlMessageUtils {

	private int messageType;
	private String message;
	
	public HtmlMessageUtils(){
		
	}
	
	public HtmlMessageUtils(int messageType, String message){
		this.messageType = messageType;
		this.message = message;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
