package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class TestMySqlHymnDaoImpl implements TestDaoImpls {

	List<HymnDto> hymns;
	Object[] tableName = new Object[] { "HYMNS" };
	HymnDto testHymnDto;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoTunes();
		TestDaoHelper.addTwoHymnAuthors();
		TestDaoHelper.addTwoHymnals();
		TestDaoHelper.hymnAndCanDto.setHymnalId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnAndCanDto.setAuthorId(TestDaoHelper.authorHymnBettyId);
		TestDaoHelper.hymnAndCanDto.setTuneId(TestDaoHelper.tuneStuttgartId);
		TestDaoHelper.hymnHowGrDto.setHymnalId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnHowGrDto.setAuthorId(TestDaoHelper.authorHymnJoeId);
		TestDaoHelper.hymnHowGrDto.setTuneId(TestDaoHelper.tuneLastUnsId);
		TestDaoHelper.hymnDaoImpl.add(TestDaoHelper.hymnHowGrDto);
		TestDaoHelper.hymnDaoImpl.add(TestDaoHelper.hymnAndCanDto);
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle("And Can it Be");
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "HYMNS" }),
				TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));

		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle("How Great Thou Art");
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "HYMNS" }),
				TestDaoHelper.hymnHowGrDto.equals(hymns.get(0)));
	}

	@Test
	public void testDelete() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();

		TestDaoHelper.hymnDaoImpl.delete(TestDaoHelper.hymnAndCanId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 1);
		TestDaoHelper.hymnDaoImpl.delete(TestDaoHelper.hymnHowGrId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 0);

	}

	@Test
	public void testGetHymnById() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		testHymnDto = TestDaoHelper.hymnDaoImpl
				.getHymnById(TestDaoHelper.hymnHowGrId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.hymnHowGrDto.equals(testHymnDto));
	}

	@Test
	public void testGetCount() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 2);

	}

	@Test
	public void testGetHymnsByAuthor() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl
				.getHymnsByAuthor(TestDaoHelper.authorHymnBettyId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getByAuthor.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));
	}

	@Test
	public void testGetHymnsByCategory() {
		// Test when we get category_detail working.
		/*
		 * TestHelper.addTwoHymns(); hymns = TestHelper.hymnDaoImpl
		 * .getHymnsByCategory(TestHelper.authorHymnBettyId);
		 * assertTrue(MessageUtils.getMessage("ju", "dao.getByAuthor.fail",
		 * tableName), TestHelper.hymnAndCanDto.equals(hymns.get(0)));
		 */
	}

	@Test
	public void testGetHymnsByTitle() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle(TestDaoHelper.hymnAndCanDto
				.getTitle());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTitle.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));
	}

	@Test
	public void testGetHymnsByTitleMany() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(TestDaoHelper.hymnalTrinityId, TestDaoHelper.tuneStuttgartId,"Test AND1 with","First AND3", TestDaoHelper.authorHymnBettyId, "3333", 545));
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(TestDaoHelper.hymnalTrinityId, TestDaoHelper.tuneStuttgartId,"Test AND2 with","First line and4", TestDaoHelper.authorHymnJoeId, "4444", 566));
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle("and");
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTitleMany.fail",
				tableName), hymns.size()==3);
	}
	
	@Test
	public void testGetHymnsByFirstLine() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(TestDaoHelper.hymnalTrinityId, TestDaoHelper.tuneStuttgartId, "Test AND1 with","First AND3", TestDaoHelper.authorHymnBettyId, "3333", 545));
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(TestDaoHelper.hymnalTrinityId, TestDaoHelper.tuneStuttgartId, "Test AND2 with","First line and4", TestDaoHelper.authorHymnJoeId, "4444", 566));
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByFirstLine("and");
		assertTrue(MessageUtils.getMessage("ju", "dao.getByFirstLine.fail",
				tableName), hymns.size()==3);
	}

	@Test
	public void testGetHymnsByFirstLineMany() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl
				.getHymnsByFirstLine(TestDaoHelper.hymnAndCanDto.getFirstLine());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByFirstLineMany.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));
	}

	
	@Test
	public void testGetHymnByHymnalNumber() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		testHymnDto = TestDaoHelper.hymnDaoImpl.getHymnByHymnalNumber(
				TestDaoHelper.hymnalTrinityId,
				TestDaoHelper.hymnAndCanDto.getNumber());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByHymnalNumber.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(testHymnDto));

	}

	@Test
	public void testGetHymnsByTune() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTune(TestDaoHelper.tuneLastUnsId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTune.fail",
				tableName), TestDaoHelper.hymnHowGrDto.equals(hymns.get(0)));
	}

	@Test
	public void testGetAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		List<HymnDto> hymn = TestDaoHelper.hymnDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				hymn.size() == 2);
	}

	@Test
	public void testDeleteAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 0);
	}

	@Test
	public void testUpdate() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnUpdateDto.setId(TestDaoHelper.hymnAndCanId);
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(TestDaoHelper.hymnalTrinityId, 
				TestDaoHelper.tuneStuttgartId, "Test AND2 with","First line and4", TestDaoHelper.authorHymnJoeId, "4444", 566));
		TestDaoHelper.hymnUpdateDto.setAuthorId(TestDaoHelper.authorHymnJoeId);
		TestDaoHelper.hymnUpdateDto.setTuneId(TestDaoHelper.tuneStuttgartId);
		TestDaoHelper.hymnUpdateDto.setHymnalId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnDaoImpl.update(TestDaoHelper.hymnUpdateDto);
		testHymnDto = TestDaoHelper.hymnDaoImpl
				.getHymnById(TestDaoHelper.hymnAndCanId);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.hymnUpdateDto.equals(testHymnDto));

	}

	@After
	public void tearDown() throws Exception {
		TestDaoHelper.deleteAll();
	}
}
