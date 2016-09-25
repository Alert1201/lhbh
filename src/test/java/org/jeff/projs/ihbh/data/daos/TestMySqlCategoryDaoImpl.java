package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.BeforeClass;

public class TestMySqlCategoryDaoImpl {

	static int i = 0;

	static List<CategoryDto> list;
	CategoryDto testCategoryDto;
	Object[] tableName = new Object[] { "METER" };

	@BeforeClass
	public static void beforeClass() throws Exception {
		TestDaoHelper.categoryDaoImpl.deleteAll();
	}
	
	// No test for this one
	@Test
	public void getChildrenByParentIdByOrder(){

		
	}
	
	@Test
	public void testAdd() {
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1);
		testCategoryDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "CATEGORY" }),
				TestDaoHelper.level1NoPar1.equals(testCategoryDto));

		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar2);
		testCategoryDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar2.getCategory());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "CATEGORY" }),
				TestDaoHelper.level1NoPar2.equals(testCategoryDto));
	}

	@Test
	public void testAddChildren() {
		CategoryDto parentDto;

		// *********** Add First Level
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1); // Add
																		// Parent

		// *********** Add Second Level

		// Get parent ID of first level
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());
		assertNotNull(MessageUtils.getMessage("ju", "dao.null.category.fail",
				tableName), parentDto);

		// Set parids for level 2
		TestDaoHelper.level2aParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2bParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2cParLevel1.setParId(parentDto.getId());

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2aParLevel1);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2bParLevel1);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2cParLevel1);

		// Test Level 2
		list = TestDaoHelper.categoryDaoImpl.getChildrenByParentId(parentDto
				.getId());
		for (CategoryDto dto : list) {
			assertEquals(MessageUtils.getMessage("ju", "dao.level2Add.fail",
					tableName), dto.getParId(), parentDto.getId());
		}

		// *********** Add Third Level
		// Get parent ID of 2nd level 1
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level2aParLevel1
						.getCategory());
		assertNotNull(MessageUtils.getMessage("ju", "dao.null.category.fail",
				tableName), parentDto);

		// Set parids for level 3a
		TestDaoHelper.level3aParLevel2a.setParId(parentDto.getId());
		TestDaoHelper.level3bParLevel2a.setParId(parentDto.getId());

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3aParLevel2a);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3bParLevel2a);

		// Get parent ID of 2nd level 3c - No level 3b
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level2cParLevel1
						.getCategory());

		// Set parids for level 2c - No 2b
		TestDaoHelper.level3aParLevel2c.setParId(parentDto.getId());
		TestDaoHelper.level3bParLevel2c.setParId(parentDto.getId());
		assertNotNull(MessageUtils.getMessage("ju", "dao.null.category.fail",
				tableName), parentDto);

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3aParLevel2c);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level3bParLevel2c);

		// Test Level 3
		list = TestDaoHelper.categoryDaoImpl.getChildrenByParentId(parentDto
				.getId());
		for (CategoryDto dto : list) {
			assertEquals(MessageUtils.getMessage("ju", "dao.level3Add.fail",
					tableName), dto.getParId(), parentDto.getId());
		}
	}

	@Test
	public void testUpdate() {
		CategoryDto testUpdateDto;
		CategoryDto categoryByCategoryDto;
		CategoryDto updateDto = new CategoryDto("Test Meter", 9999999);

		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1);
		categoryByCategoryDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());
		updateDto.setId(categoryByCategoryDto.getId());
		TestDaoHelper.categoryDaoImpl.update(updateDto);
		testUpdateDto = TestDaoHelper.categoryDaoImpl
				.getCategoryById(categoryByCategoryDto.getId());
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				updateDto.equals(testUpdateDto));
	}

	@Test
	public void testDelete() {
		CategoryDto dto1;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1); // Add
																		// Parent
		// *********** Add Second Level
		// Get parent ID of first level
		dto1 = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());
		TestDaoHelper.categoryDaoImpl.delete(dto1.getId());

		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.categoryDaoImpl.getCount() == 0);

	}

	@Test
	public void testGetCategoryById() {
		CategoryDto categoryDto;
		CategoryDto testGetByIdDto;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1);
		categoryDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());

		testGetByIdDto = TestDaoHelper.categoryDaoImpl
				.getCategoryById(categoryDto.getId());

		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				categoryDto.equals(testGetByIdDto));
	}

	@Test
	public void testGetCategoryByCategory() {
		CategoryDto parentDto;

		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1); // Add
																		// Parent

		// *********** Add Second Level
		// Get parent ID of first level
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());

		assertTrue(MessageUtils.getMessage("ju", "dao.getByCategory.fail",
				tableName), TestDaoHelper.level1NoPar1.equals(parentDto));
	}

	@Test
	public void testGetChildren() {
		CategoryDto parentDto;

		TestDaoHelper.addFirstMainAndChildren();

		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());

		list = TestDaoHelper.categoryDaoImpl.getChildrenByParentId(parentDto
				.getId());
		for (CategoryDto dto : list) {
			assertEquals(MessageUtils.getMessage("ju", "dao.getChildren.fail",
					tableName), dto.getParId(), parentDto.getId());
		}
	}

	@Test
	public void testGetCategoryByParentName() {
		CategoryDto parentDto;
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level1NoPar1); // Add
																		// Parent
		// *********** Add Second Level

		// Get parent ID of first level
		parentDto = TestDaoHelper.categoryDaoImpl
				.getCategoryByCategory(TestDaoHelper.level1NoPar1.getCategory());

		// Set parids for level 2
		TestDaoHelper.level2aParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2bParLevel1.setParId(parentDto.getId());
		TestDaoHelper.level2cParLevel1.setParId(parentDto.getId());

		// Add um
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2aParLevel1);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2bParLevel1);
		TestDaoHelper.categoryDaoImpl.add(TestDaoHelper.level2cParLevel1);

		list = TestDaoHelper.categoryDaoImpl
				.getChildrenByParentName(TestDaoHelper.level1NoPar1
						.getCategory());
		for (CategoryDto dto : list) {
			assertEquals(MessageUtils.getMessage("ju",
					"dao.getByChildrenParentName", tableName), dto.getParId(),
					parentDto.getId());
		}
	}

	@Test
	public void testGetAll() {
		// Test List All
		int count = TestDaoHelper.addFirstMainAndChildren();
		list = TestDaoHelper.categoryDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				list.size() == count);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addFirstMainAndChildren();
		TestDaoHelper.categoryDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.categoryDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetCount() {
		int addCount = TestDaoHelper.addFirstMainAndChildren();
		int count = TestDaoHelper.categoryDaoImpl.getCount();

		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				count == addCount);

	}

	@After
	public void tearDown() {
		// Delete after each test
		TestDaoHelper.categoryDaoImpl.deleteAll();
	}
}
