package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMySqlHymnDaoImpl implements TestDaoImpls {

	List<HymnDto> hymns;
	Object[] tableName = new Object[] { "HYMNS" };
	HymnDto testHymnDto;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.authorDaoImpl.deleteAll();
		TestDaoHelper.tuneDaoImpl.deleteAll();
		TestDaoHelper.meterDaoImpl.deleteAll();
		TestDaoHelper.hymnalDaoImpl.deleteAll();
		TestDaoHelper.hymnDaoImpl.deleteAll();
		TestDaoHelper.categoryDaoImpl.deleteAll();
	}

	@Test
	public void testAdd() {
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
	public void testDelete() {
		TestDaoHelper.addTwoHymns();

		TestDaoHelper.hymnDaoImpl.delete(TestDaoHelper.hymnAndCanId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 1);
		TestDaoHelper.hymnDaoImpl.delete(TestDaoHelper.hymnHowGrId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 0);

	}

	@Test
	public void testGetHymnById() {
		TestDaoHelper.addTwoHymns();
		testHymnDto = TestDaoHelper.hymnDaoImpl
				.getHymnById(TestDaoHelper.hymnHowGrId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.hymnHowGrDto.equals(testHymnDto));
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoHymns();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 2);

	}

	@Test
	public void testGetHymnsByAuthor() {
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
	public void testGetHymnsByTitle() {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle(TestDaoHelper.hymnAndCanDto
				.getTitle());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTitle.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));
	}

	@Test
	public void testGetHymnsByTitleMany() {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(0, 0,"Test AND1 with","First AND3", 0, "3333", 545));
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(0, 0,"Test AND2 with","First line and4", 0, "4444", 566));
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTitle("and");
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTitleMany.fail",
				tableName), hymns.size()==3);
	}
	
	@Test
	public void testGetHymnsByFirstLine() {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(0, 0,"Test AND1 with","First AND3", 0, "3333", 545));
		TestDaoHelper.hymnDaoImpl.add(new HymnDto(0, 0,"Test AND2 with","First line and4", 0, "4444", 566));
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByFirstLine("and");
		assertTrue(MessageUtils.getMessage("ju", "dao.getByFirstLine.fail",
				tableName), hymns.size()==3);
	}

	@Test
	public void testGetHymnsByFirstLineMany() {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl
				.getHymnsByFirstLine(TestDaoHelper.hymnAndCanDto.getFirstLine());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByFirstLineMany.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(hymns.get(0)));
	}

	
	@Test
	public void testGetHymnByHymnalNumber() {
		TestDaoHelper.addTwoHymns();
		testHymnDto = TestDaoHelper.hymnDaoImpl.getHymnByHymnalNumber(
				TestDaoHelper.hymnalTrinityId,
				TestDaoHelper.hymnAndCanDto.getNumber());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByHymnalNumber.fail",
				tableName), TestDaoHelper.hymnAndCanDto.equals(testHymnDto));

	}

	@Test
	public void testGetHymnsByTune() {
		TestDaoHelper.addTwoHymns();
		hymns = TestDaoHelper.hymnDaoImpl.getHymnsByTune(TestDaoHelper.tuneLastUnsId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getByTune.fail",
				tableName), TestDaoHelper.hymnHowGrDto.equals(hymns.get(0)));
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoHymns();
		List<HymnDto> hymn = TestDaoHelper.hymnDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				hymn.size() == 2);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.hymnDaoImpl.getCount() == 0);
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.hymnDaoImpl.update(TestDaoHelper.hymnUpdateDto,
				TestDaoHelper.hymnAndCanId);
		testHymnDto = TestDaoHelper.hymnDaoImpl
				.getHymnById(TestDaoHelper.hymnAndCanId);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.hymnUpdateDto.equals(testHymnDto));

	}

	@After
	public void tearDown() throws Exception {

	}
}
