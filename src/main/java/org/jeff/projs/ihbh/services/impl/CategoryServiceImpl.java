package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.services.CategoryService;
import org.jeff.projs.ihbh.utils.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	MySqlCategoryDaoImpl categoryDaoImpl;
	
	@Override
	public int save(CategoryDto dto) {
		if(dto.getId()!=0){
			categoryDaoImpl.update(dto);
		} else {
			categoryDaoImpl.add(dto);
		}
		return 0;
	}

	@Override
	public int delete(CategoryDto dto) {
		return categoryDaoImpl.delete(dto.getId());
	}

	@Override
	public CategoryDto getById(CategoryDto dto) {
		return categoryDaoImpl.getCategoryById(dto.getId());
	}

	@Override
	public CategoryDto getByCategory(CategoryDto dto) {
		return categoryDaoImpl.getCategoryByCategory(dto.getCategory());
	}

	@Override
	public List<CategoryDto> getChildrenByParentId(CategoryDto dto) {
		return categoryDaoImpl.getChildrenByParentId(dto.getParId());
	}

	@Override
	public List<CategoryDto> getChildrenByParentName(CategoryDto dto) {
		return categoryDaoImpl.getChildrenByParentName(dto.getCategory());
	}

	@Override
	public List<CategoryDto> getAll() {
		return categoryDaoImpl.getAll();
	}

	@Override
	public int getCount() {
		return categoryDaoImpl.getCount();
	}

	@Override
	public CategoryTree getTree() {
		CategoryTree tree = new CategoryTree();
		getChildrenRecursively(tree, 0);
		return tree;
	}
	
	
	private List<CategoryDto> getChildrenRecursively(CategoryTree tree, int parentId){
		List<CategoryDto> retList = categoryDaoImpl.getChildrenByParentId(parentId);
		if(retList==null || retList.size()==0)
			return null;
		else{
			for (CategoryDto categoryDto : retList) {
				tree.add(categoryDto);
				System.out.println(tree);
				getChildrenRecursively(tree, categoryDto.getId() );
			}
		}
		return retList;
	}
}
