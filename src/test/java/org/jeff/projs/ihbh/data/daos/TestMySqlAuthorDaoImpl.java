/**
 * 
 */
package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff Sulman
 *
 */
public class TestMySqlAuthorDaoImpl {
	Object[] tableName = new Object[] { "AUTHOR" };
	AuthorDto testAuthorDto;

	static List<AuthorDto> authors;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.authorDaoImpl.deleteAll();
	}

	@Test
	public void testAdd() {
		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorTuneTimDto);
		testAuthorDto = TestDaoHelper.authorDaoImpl.getAuthorByFullName(
				TestDaoHelper.authorTuneTimDto.getFirstName(),
				TestDaoHelper.authorTuneTimDto.getLastName());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "METER" }),
				TestDaoHelper.authorTuneTimDto.equals(testAuthorDto));

		TestDaoHelper.authorDaoImpl.add(TestDaoHelper.authorTuneBillDto);
		testAuthorDto = TestDaoHelper.authorDaoImpl.getAuthorByFullName(
				TestDaoHelper.authorTuneBillDto.getFirstName(),
				TestDaoHelper.authorTuneBillDto.getLastName());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "METER" }),
				TestDaoHelper.authorTuneBillDto.equals(testAuthorDto));
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoTuneAuthors();
		authors = TestDaoHelper.authorDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				authors.size() == 2);
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoTuneAuthors();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.authorDaoImpl.getCount() == 2);
	}

	@Test
	public void testGetAuthorByFullName() {
		TestDaoHelper.addTwoTuneAuthors();
		testAuthorDto = TestDaoHelper.authorDaoImpl.getAuthorByFullName(
				TestDaoHelper.authorTuneTimDto.getFirstName(),
				TestDaoHelper.authorTuneTimDto.getLastName());

		assertTrue(MessageUtils.getMessage("ju", "dao.getByFullName.fail",
				tableName),
				TestDaoHelper.authorTuneTimDto.equals(testAuthorDto));
	}

	@Test
	public void testGetAuthorById() {
		TestDaoHelper.addTwoTuneAuthors();

		testAuthorDto = TestDaoHelper.authorDaoImpl
				.getAuthorById(TestDaoHelper.authorTuneTimId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.authorTuneTimDto.equals(testAuthorDto));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoTuneAuthors();

		TestDaoHelper.authorTuneBillDto.setId(TestDaoHelper.authorTuneBillId);
		TestDaoHelper.authorDaoImpl.update(TestDaoHelper.authorTuneBillDto);
		testAuthorDto = TestDaoHelper.authorDaoImpl
				.getAuthorById(TestDaoHelper.authorTuneBillId);
		// testUpdateDto and TestHelper.authorTuneBillDto should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.authorTuneBillDto.equals(testAuthorDto));
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoTuneAuthors();

		TestDaoHelper.authorDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.authorDaoImpl.getCount() == 0);
	}

	@Test
	public void testDelete() {
		TestDaoHelper.addTwoTuneAuthors();

		authors = TestDaoHelper.authorDaoImpl.getAll();
		TestDaoHelper.authorDaoImpl.delete(TestDaoHelper.authorTuneBillId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail"),
				TestDaoHelper.authorDaoImpl.getCount() == 1);
		TestDaoHelper.authorDaoImpl.delete(TestDaoHelper.authorTuneTimId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail"),
				TestDaoHelper.authorDaoImpl.getCount() == 0);
	}

	@After
	public void tearDown() {
		TestDaoHelper.authorDaoImpl.deleteAll();
	}
}
