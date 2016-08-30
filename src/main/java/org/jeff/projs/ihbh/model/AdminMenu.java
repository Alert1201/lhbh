package org.jeff.projs.ihbh.model;

import java.util.LinkedHashMap;


public class AdminMenu {
	
	public static LinkedHashMap<String, String> map;
	
	static{
		map = new LinkedHashMap<String, String>();
		map.put("authors", "Authors");
		map.put("categories", "Categories");
		map.put("hymnals", "Hymnals");
		map.put("hymns", "Hymns");
		map.put("meters", "Meters");
		map.put("tunes", "Tunes");
		map.put("users", "Users");
		map.put("userTypes", "User Types");
	}
	
	
}
