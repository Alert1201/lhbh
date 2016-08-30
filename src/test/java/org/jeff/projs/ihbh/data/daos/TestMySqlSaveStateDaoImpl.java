package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.SaveStateDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TestMySqlSaveStateDaoImpl implements TestDaoImpls {

	Object[] tableName = new Object[] { "SAVE_STATE" };
	SaveStateDto testSaveStateDto = null;

	@Before
	public void setUp() throws Exception {
		// Cleanup
		TestDaoHelper.stDaoImpl.deleteAll();
		TestDaoHelper.userDaoImpl.deleteAll();
		TestDaoHelper.userTypeDaoImpl.deleteAll();
	}

	@Test
	public void testAdd() {
		TestDaoHelper.addTwoUsers();
		TestDaoHelper.firstSaveStateDto.setUserId(TestDaoHelper.userJeffId);
		TestDaoHelper.stDaoImpl.add(TestDaoHelper.firstSaveStateDto);
		testSaveStateDto = TestDaoHelper.stDaoImpl
				.getSaveStateByUserId(TestDaoHelper.userJeffId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "SAVE_STATE" }),
				TestDaoHelper.firstSaveStateDto.equals(testSaveStateDto));

		TestDaoHelper.secondSaveStateDto.setUserId(TestDaoHelper.userFrankId);
		TestDaoHelper.stDaoImpl.add(TestDaoHelper.secondSaveStateDto);
		testSaveStateDto = TestDaoHelper.stDaoImpl
				.getSaveStateByUserId(TestDaoHelper.userFrankId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "SAVE_STATE" }),
				TestDaoHelper.secondSaveStateDto.equals(testSaveStateDto));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoSavedStates();
		TestDaoHelper.stDaoImpl.update(TestDaoHelper.updateSaveStateDto,
				TestDaoHelper.firstSaveStateId);
		testSaveStateDto = TestDaoHelper.stDaoImpl
				.getSaveStateById(TestDaoHelper.firstSaveStateId);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.updateSaveStateDto.equals(testSaveStateDto));
	}

	@Test
	public void testDelete() {
		TestDaoHelper.addTwoSavedStates();

		TestDaoHelper.stDaoImpl.delete(TestDaoHelper.firstSaveStateId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.stDaoImpl.getCount() == 1);
		TestDaoHelper.stDaoImpl.delete(TestDaoHelper.secondSaveStateId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.stDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetSaveStateById() {
		TestDaoHelper.addTwoSavedStates();
		testSaveStateDto = TestDaoHelper.stDaoImpl
				.getSaveStateById(TestDaoHelper.firstSaveStateId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.firstSaveStateDto.equals(testSaveStateDto));
	}

	@Test
	public void testGetSaveStateByUserId() {
		TestDaoHelper.addTwoSavedStates();
		testSaveStateDto = TestDaoHelper.stDaoImpl
				.getSaveStateById(TestDaoHelper.firstSaveStateId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getByUserId.fail",
				tableName),
				testSaveStateDto.equals(TestDaoHelper.firstSaveStateDto));
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoSavedStates();
		List<SaveStateDto> saveStates = TestDaoHelper.stDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				saveStates.size() == 2);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoSavedStates();
		TestDaoHelper.stDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.stDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoSavedStates();
		int count = TestDaoHelper.stDaoImpl.getCount();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				count == 2);
	}

	@After
	public void tearDown() throws Exception {
		TestDaoHelper.stDaoImpl.deleteAll();
		TestDaoHelper.userDaoImpl.deleteAll();
		TestDaoHelper.userTypeDaoImpl.deleteAll();
	}
}
