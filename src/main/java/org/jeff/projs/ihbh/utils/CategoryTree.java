package org.jeff.projs.ihbh.utils;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.CategoryDto;

public class CategoryTree  {
	
	List<CategoryDto> tree = new ArrayList<CategoryDto>();
	
	public void add(CategoryDto obj){
		tree.add(obj);
	}
	
	public String toString(){
		StringBuilder strb = new StringBuilder();
		for (CategoryDto categoryDto : tree) {
			int level = categoryDto.getLevel();
			if(level > 1)
				strb.append("|");
			
			for(int i = 1; i < level; i++){
				strb.append("-");
			}
			strb.append(categoryDto.getCategory()).append("\r");
		}
		return strb.toString();
	}

	
}
