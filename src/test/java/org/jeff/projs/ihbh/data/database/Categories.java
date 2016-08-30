package org.jeff.projs.ihbh.data.database;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.utils.CategoryTree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Categories {
	
	static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"test-application-context.xml");
	static MySqlCategoryDaoImpl categoryDaoImpl = ctx.getBean(MySqlCategoryDaoImpl.class);
	
	// Category DTO Setup
		
	public static void loadTable(){
		categoryDaoImpl.deleteAll();
		CategoryDto level1NoPar1 = new CategoryDto(
				"God: His Being, Works and Word",0, 1, 1);
		CategoryDto level2aParLevel1 = 
					new CategoryDto("The Glory of God",0, 1, 2);
		CategoryDto level3aParLevel2a = 
						new CategoryDto("The Divine Perfections",0, 1, 3);
		CategoryDto level3bParLevel2a = 
						new CategoryDto("God: Unchangeable",0, 2, 3);
		CategoryDto level2bParLevel1 = 
					new CategoryDto("The Holy Trinity",0, 2, 2);
		CategoryDto level2cParLevel1 = 
					new CategoryDto("The Lord Jesus Christ",0, 3, 2);
		CategoryDto level3aParLevel2c = 
						new CategoryDto("His Deity",0, 1, 3);
		CategoryDto level3bParLevel2c = 
						new CategoryDto("His Praise",0, 2, 3);
		
		CategoryDto level1NoPar2 = 
				new CategoryDto("The Church",0, 2, 1);
		CategoryDto level2aParLevel2 = 
					new CategoryDto("The Church of Christ",0, 1, 2);
		CategoryDto level2bParLevel2 = 
					new CategoryDto("The Lord's Day",0, 2, 2);
		CategoryDto level3aParLevel2b = 
						new CategoryDto("Morning",0, 1, 3);
		CategoryDto level3bParLevel2b = 
						new CategoryDto("Evening",0, 2, 3);			
		CategoryDto level2cParLevel2 = 
					new CategoryDto("The Lord's Supper",0, 3, 2);

		// *********** Add Second Level
		// Get parent ID of first level
	
		
		CategoryDto parentDto;
//		level1NoPar1.setListOrder(1);
		categoryDaoImpl.add(level1NoPar1); // Add Parent
		
		
		parentDto = categoryDaoImpl
				.getCategoryByCategory(level1NoPar1.getCategory());

		// Set parids for level 2
		level2aParLevel1.setParId(parentDto.getId());
		level2bParLevel1.setParId(parentDto.getId());
		level2cParLevel1.setParId(parentDto.getId());
		
		// Add um
		categoryDaoImpl.add(level2aParLevel1);
		categoryDaoImpl.add(level2bParLevel1);
		categoryDaoImpl.add(level2cParLevel1);


		// *********** Add Third Level
		// Get parent ID of 2nd level 1
		parentDto = categoryDaoImpl
				.getCategoryByCategory(level2aParLevel1
						.getCategory());

		// Set parids for level 3a
		level3aParLevel2a.setParId(parentDto.getId());
		level3bParLevel2a.setParId(parentDto.getId());
		

		// Add um
		categoryDaoImpl.add(level3aParLevel2a);
		categoryDaoImpl.add(level3bParLevel2a);

		// Get parent ID of 2nd level 3c - No level 3b
		parentDto = categoryDaoImpl
				.getCategoryByCategory(level2cParLevel1
						.getCategory());

		// Set parids for level 2c - No 2b
		level3aParLevel2c.setParId(parentDto.getId());
		level3bParLevel2c.setParId(parentDto.getId());

		// Add um
		categoryDaoImpl.add(level3aParLevel2c);
		categoryDaoImpl.add(level3bParLevel2c);

		categoryDaoImpl.add(level1NoPar2);
		// Get parent ID of first level 2 - The Church
		parentDto = categoryDaoImpl
				.getCategoryByCategory(level1NoPar2.getCategory());

		// Set parids for level 2
		level2aParLevel2.setParId(parentDto.getId());
		level2bParLevel2.setParId(parentDto.getId());
		level2cParLevel2.setParId(parentDto.getId());

		
		// Add um
		categoryDaoImpl.add(level2aParLevel2);
		categoryDaoImpl.add(level2bParLevel2);
		categoryDaoImpl.add(level2cParLevel2);
		
		parentDto = categoryDaoImpl
				.getCategoryByCategory(level2bParLevel2.getCategory());
		level3aParLevel2b.setParId(parentDto.getId());
		level3bParLevel2b.setParId(parentDto.getId());
		
		
		categoryDaoImpl.add(level3aParLevel2b);
		categoryDaoImpl.add(level3bParLevel2b);
	}
		
	public static List<CategoryDto> recursive(CategoryTree tree, int parentId){
		List<CategoryDto> retList = categoryDaoImpl.getChildrenByParentId(parentId);
		if(retList==null || retList.size()==0)
			return null;
		else{
			for (CategoryDto categoryDto : retList) {
				tree.add(categoryDto);
				System.out.println(tree);
				recursive(tree, categoryDto.getId() );
			}
		}
		return retList;
	}
	
	public static void main(String[] args){
		loadTable();
		CategoryTree tree = new CategoryTree();
		recursive(tree, 0);
	}

}
