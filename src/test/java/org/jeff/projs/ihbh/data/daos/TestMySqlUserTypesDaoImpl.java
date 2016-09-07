package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMySqlUserTypesDaoImpl implements TestDaoImpls {

	Object[] tableName = new Object[] { "USER_TYPES" };
	UserTypeDto testUserTypeDto;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd() {
		// Test Add
		TestDaoHelper.userTypeDaoImpl.add(TestDaoHelper.userTypeAdmDto);
		testUserTypeDto = TestDaoHelper.userTypeDaoImpl
				.getUserTypeByUserType(TestDaoHelper.userTypeAdmDto.getUserType());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "USER_TYPES" }),
				TestDaoHelper.userTypeAdmDto.equals(testUserTypeDto));

		TestDaoHelper.userTypeDaoImpl.add(TestDaoHelper.userTypeUsrDto);
		testUserTypeDto = TestDaoHelper.userTypeDaoImpl
				.getUserTypeByUserType(TestDaoHelper.userTypeAdmDto.getUserType());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "USER_TYPES" }),
				TestDaoHelper.userTypeAdmDto.equals(testUserTypeDto));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoUserTypes();
		UserTypeDto testUpdateDto;
		TestDaoHelper.userTypeUpdateDto.setId(TestDaoHelper.userTypeAdmDtoId);
		TestDaoHelper.userTypeDaoImpl.update(TestDaoHelper.userTypeUpdateDto);
		testUpdateDto = TestDaoHelper.userTypeDaoImpl
				.getUserTypeById(TestDaoHelper.userTypeAdmDtoId);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.userTypeUpdateDto.equals(testUpdateDto));
	}

	@Test
	public void testDelete() {
		TestDaoHelper.addTwoUserTypes();
		TestDaoHelper.userTypeDaoImpl.delete(TestDaoHelper.userTypeAdmDtoId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.userTypeDaoImpl.getCount() == 1);
		TestDaoHelper.userTypeDaoImpl.delete(TestDaoHelper.userTypeUsrDtoId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.userTypeDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetUserTypeById() {
		TestDaoHelper.addTwoUserTypes();
		UserTypeDto testGetByIdDto;
		testGetByIdDto = TestDaoHelper.userTypeDaoImpl
				.getUserTypeById(TestDaoHelper.userTypeUsrDtoId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.userTypeUsrDto.equals(testGetByIdDto));
	}

	@Test
	public void testGetUserTypesByDescription() {
		TestDaoHelper.addTwoUserTypes();
		testUserTypeDto = TestDaoHelper.userTypeDaoImpl
				.getUserTypeByDescription(TestDaoHelper.userTypeAdmDto
						.getDescription());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByDesc.fail", tableName),
				TestDaoHelper.userTypeAdmDto.equals(testUserTypeDto));
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoUserTypes();

		List<UserTypeDto> userTypes = TestDaoHelper.userTypeDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				userTypes.size() == 2);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoUserTypes();
		;
		TestDaoHelper.userTypeDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.userTypeDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoUserTypes();
		int count = TestDaoHelper.userTypeDaoImpl.getCount();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				count == 2);
	}

	@After
	public void tearDown() throws Exception {
		TestDaoHelper.deleteAll();
	}
}
