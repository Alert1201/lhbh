package org.jeff.projs.ihbh.utils;

import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.TreeNodeDto;

public class TreeViewUtils {

	public static TreeNodeDto convCategoryDtoToTreeNode(CategoryDto categoryDto){
		TreeNodeDto treeNodeDto = new TreeNodeDto();
		return convCategoryDtoToTreeNode(categoryDto, treeNodeDto);
	}
	
	public static TreeNodeDto convCategoryDtoToTreeNode(CategoryDto categoryDto, TreeNodeDto treeNodeDto){
		treeNodeDto.setLevel(Integer.toString(categoryDto.getLevel()));
		treeNodeDto.setId(Integer.toString(categoryDto.getId()));
		treeNodeDto.setLabel(categoryDto.getCategory());
		treeNodeDto.setParentId(Integer.toString(categoryDto.getParId()));
		treeNodeDto.setOrder(Integer.toString(categoryDto.getListOrder()));
		return treeNodeDto;
	}
	
	public static CategoryDto convTreeNodeToCategoryDto(TreeNodeDto treeNode){
		CategoryDto categoryDto = new CategoryDto();
		return convTreeNodeToCategoryDto(treeNode, categoryDto);
	}
	
	public static CategoryDto convTreeNodeToCategoryDto(TreeNodeDto treeNode, CategoryDto categoryDto){
		categoryDto.setLevel(Integer.valueOf(treeNode.getLevel()));
		categoryDto.setId(Integer.valueOf(treeNode.getId()));
		categoryDto.setCategory(treeNode.getLabel());
		categoryDto.setParId(Integer.valueOf(treeNode.getParentId()));
		categoryDto.setListOrder(Integer.valueOf(treeNode.getOrder()));
		return categoryDto;
	}
}
