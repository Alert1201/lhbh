package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMySqlUsersDaoImpl {

	Object[] tableName = new Object[] { "USERS" };
	UserDto testUserDto;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.userTypeDaoImpl.deleteAll();
		TestDaoHelper.userDaoImpl.deleteAll();
	}

	@Test
	public void testAdd() {

		TestDaoHelper.addTwoUserTypes();
		TestDaoHelper.userJeffDto.setTypeId(TestDaoHelper.userTypeAdmDtoId);

		TestDaoHelper.userDaoImpl.add(TestDaoHelper.userFrankDto);
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserByUserName(TestDaoHelper.userFrankDto.getUsername());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "USERS" }),
				TestDaoHelper.userFrankDto.equals(testUserDto));

		TestDaoHelper.userDaoImpl.add(TestDaoHelper.userJeffDto);
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserByUserName(TestDaoHelper.userJeffDto.getUsername());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "USERS" }),
				TestDaoHelper.userJeffDto.equals(testUserDto));

	}

	@Test
	public void testGetUserByUserName() {
		TestDaoHelper.addTwoUsers();
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserByUserName(TestDaoHelper.userFrankDto.getUsername());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByName.fail", tableName),
				TestDaoHelper.userFrankDto.equals(testUserDto));
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoUsers();
		List<UserDto> user = TestDaoHelper.userDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				user.size() == 2);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoUsers();
		TestDaoHelper.userDaoImpl.deleteAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.userDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoUsers();
		int count = TestDaoHelper.userDaoImpl.getCount();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName), count == 2);
	}

	@Test
	public void testDelete() {
		TestDaoHelper.addTwoUsers();
		assertTrue("User Add Returns 0 rows",
				TestDaoHelper.userDaoImpl.getCount() == 2);
		TestDaoHelper.userDaoImpl.delete(TestDaoHelper.userFrankId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.userDaoImpl.getCount() == 1);
		TestDaoHelper.userDaoImpl.delete(TestDaoHelper.userJeffId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.userDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetUserById() {
		TestDaoHelper.addTwoUsers();
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserById(TestDaoHelper.userFrankId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByUserId.fail",
						tableName),
				TestDaoHelper.userFrankDto.equals(testUserDto));
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserById(TestDaoHelper.userJeffId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByUserId.fail",
				tableName),
				TestDaoHelper.userJeffDto.equals(testUserDto));
	}

	@Test
	public void testGetUserByUsersTypeId() {
		TestDaoHelper.addTwoUsers();		List<UserDto> list = null;

		// Test Frank
		list = TestDaoHelper.userDaoImpl
				.getUsersByUserTypeId(TestDaoHelper.userFrankDto.getTypeId());
		testUserDto = list.get(0);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByUserTypeId.fail", tableName),
				TestDaoHelper.userFrankDto.equals(testUserDto));

		// Test Jeff
		list = TestDaoHelper.userDaoImpl
				.getUsersByUserTypeId(TestDaoHelper.userJeffDto.getTypeId());

		testUserDto= list.get(0);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByUserTypeId.fail", tableName),
				TestDaoHelper.userJeffDto.equals(testUserDto));
	}

	@Test
	public void testGetUserByFullName() {
		TestDaoHelper.addTwoUsers();
		testUserDto = TestDaoHelper.userDaoImpl.getUserByFullName(
				TestDaoHelper.userFrankDto.getFirstName(),
				TestDaoHelper.userFrankDto.getLastName());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByFullName.fail", tableName),
				TestDaoHelper.userFrankDto.equals(testUserDto));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoUsers();

		TestDaoHelper.userDaoImpl.update(TestDaoHelper.userUpdateDto,
				TestDaoHelper.userFrankId);
		testUserDto = TestDaoHelper.userDaoImpl
				.getUserById(TestDaoHelper.userFrankId);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.userUpdateDto.equals(testUserDto));
	}

	@After
	public void tearDown() throws Exception {
		TestDaoHelper.userDaoImpl.deleteAll();
		TestDaoHelper.userTypeDaoImpl.deleteAll();
	}
}
