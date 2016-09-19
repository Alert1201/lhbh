package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.TreeNodeDto;

public interface CategoryDAO {
	public int add(CategoryDto  dto);
	public int update(CategoryDto dto);
	public int delete(int id);
	public CategoryDto getCategoryById(int id);
	public CategoryDto getCategoryByCategory(String Category);
	public List<CategoryDto> getChildrenByParentId(int parentId);
	public List<CategoryDto> getChildrenByParentName(String parentName);
	public List<CategoryDto> getAll();
	public int deleteAll();
	public int getCount();
	List<TreeNodeDto> getChildrenByParentIdForTreeNode(int parId);
}
