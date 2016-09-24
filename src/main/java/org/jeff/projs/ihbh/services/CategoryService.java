package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.TreeNodeDto;
import org.jeff.projs.ihbh.utils.CategoryTree;

public interface CategoryService {
	public int save(CategoryDto  dto);
	public int delete(CategoryDto  dto);
	public CategoryDto getById(CategoryDto  dto);
	public CategoryDto getByCategory(CategoryDto  dto);
	public List<CategoryDto> getChildrenByParentId(CategoryDto  dto);
	public List<CategoryDto> getChildrenByParentName(CategoryDto  dto);
	public List<CategoryDto> getAll();
	public CategoryTree buildCategoryTree(CategoryTree tree, int parentId);
	public List<TreeNodeDto> buildJsonTree(List<TreeNodeDto> tree, int parentId);
	public int getCount();
}
