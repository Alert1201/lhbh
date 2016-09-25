package org.jeff.projs.ihbh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.data.domains.TreeNodeDto;
import org.jeff.projs.ihbh.services.CategoryService;
import org.jeff.projs.ihbh.utils.CategoryTree;
import org.jeff.projs.ihbh.utils.TreeViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryServiceImpl")
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

	public void removeTreeViewNode(int id, int parId, int order){
		categoryDaoImpl.delete(id);
		 List<CategoryDto> list = categoryDaoImpl.getChildrenByParentIdByOrder(parId, order);
		 for(CategoryDto dto : list){
			 dto.setListOrder(order++);
			 categoryDaoImpl.update(dto);
		 }
		
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

	@Override
	public List<TreeNodeDto> buildJsonTree(List<TreeNodeDto> tree, int parentId, boolean expand){
		List<CategoryDto> retList = categoryDaoImpl.getChildrenByParentId(parentId);
		if(retList==null || retList.size()==0)
			return null;
		else{
			for (CategoryDto categoryDto : retList) {
				TreeNodeDto dto = new TreeNodeDto();
				dto = TreeViewUtils.convCategoryDtoToTreeNode(categoryDto);
				List<TreeNodeDto> treeTemp = new ArrayList<TreeNodeDto>();
				dto.setBranch(buildJsonTree(treeTemp, categoryDto.getId(), expand));
				if(dto.getBranch() != null && dto.getBranch().size()>0){
					dto.setInode(true);
					//if node, expand
					dto.setOpen(expand);
				}
				tree.add(dto);
			}
		}
		return tree;
	}
	
	@Override
	public CategoryTree buildCategoryTree(CategoryTree tree, int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
