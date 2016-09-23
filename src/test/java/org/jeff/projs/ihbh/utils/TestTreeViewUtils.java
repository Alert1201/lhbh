package org.jeff.projs.ihbh.utils;

import static org.junit.Assert.assertTrue;

import org.h2.util.StringUtils;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.TreeNodeDto;
import org.junit.Before;
import org.junit.Test;

public class TestTreeViewUtils {

	CategoryDto categoryDto;
	TreeNodeDto treeNodeDto;
	@Before
	public void setUp() throws Exception {
		categoryDto = new CategoryDto();
		categoryDto.setCategory("Category");
		categoryDto.setId(1);
		categoryDto.setLevel(2);
		categoryDto.setParId(0);
		categoryDto.setListOrder(4);
		
		treeNodeDto = new TreeNodeDto();
		treeNodeDto.setId("1");
		treeNodeDto.setLabel("Label");
		treeNodeDto.setLevel("3");
		treeNodeDto.setParentId("0");
		treeNodeDto.setOrder("4");
	}
	
	@Test
	public void testConvCategoryDtoToTreeNode(){
		TreeNodeDto testTreeNodeDto = new TreeNodeDto();
		testTreeNodeDto = TreeViewUtils.convCategoryDtoToTreeNode(categoryDto);
		testForEqual(testTreeNodeDto, categoryDto);

	}
	
	@Test
	public void testConvTreeNodeToCategoryDto(){
		CategoryDto testCategeoryDto = new CategoryDto();
		testCategeoryDto = TreeViewUtils.convTreeNodeToCategoryDto(treeNodeDto, testCategeoryDto);
		testForEqual(treeNodeDto, testCategeoryDto);
	}

	private void testForEqual(TreeNodeDto testTreeNodeDto, CategoryDto testCategoryDto){
		assertTrue("Id Not Equal", StringUtils.equals(testTreeNodeDto.getId(), Integer.toString(testCategoryDto.getId())));
		assertTrue("Label Not Equal", StringUtils.equals(testTreeNodeDto.getLabel(), testCategoryDto.getCategory()));
		assertTrue("Level Not Equal", StringUtils.equals(testTreeNodeDto.getLevel(), Integer.toString(testCategoryDto.getLevel())));
		assertTrue("Label Not Equal", StringUtils.equals(testTreeNodeDto.getOrder(), Integer.toString(testCategoryDto.getListOrder())));
		assertTrue("Label Not Equal", StringUtils.equals(testTreeNodeDto.getParentId(), Integer.toString(testCategoryDto.getParId())));
	}
}
