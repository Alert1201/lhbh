package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMySqlTunesDaoImpl {

	TuneDto testTuneDto;
	static List<TuneDto> tunes;
	Object[] tableName = new Object[] { "TUNES" };

	@Before
	public void setup() {
		TestDaoHelper.authorDaoImpl.deleteAll();
		TestDaoHelper.meterDaoImpl.deleteAll();
		TestDaoHelper.tuneDaoImpl.deleteAll();
	}

	@Test
	public void add() {
		TestDaoHelper.addTwoTuneAuthors();
		TestDaoHelper.addTwoMeters();

		// Test Add
		TestDaoHelper.tuneDaoImpl.add(TestDaoHelper.tuneStuttgartDto);
		testTuneDto = TestDaoHelper.tuneDaoImpl
				.getTuneByName(TestDaoHelper.tuneStuttgartDto.getName());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "TUNES" }),
				TestDaoHelper.tuneStuttgartDto.equals(testTuneDto));

		TestDaoHelper.tuneDaoImpl.add(TestDaoHelper.tuneLastUnsDto);
		testTuneDto = TestDaoHelper.tuneDaoImpl
				.getTuneByName(TestDaoHelper.tuneLastUnsDto.getName());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "TUNES" }),
				TestDaoHelper.tuneLastUnsDto.equals(testTuneDto));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoTunes();
		TestDaoHelper.tuneUpdateDto.setId(TestDaoHelper.tuneStuttgartId);
		TestDaoHelper.tuneDaoImpl.update(TestDaoHelper.tuneUpdateDto);
		testTuneDto = TestDaoHelper.tuneDaoImpl
				.getTuneById(TestDaoHelper.tuneStuttgartId);

		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.tuneUpdateDto.equals(testTuneDto));
	}

	@Test
	public void testDelete() {
		TestDaoHelper.addTwoTunes();

		int count = TestDaoHelper.tuneDaoImpl.getCount();
		TestDaoHelper.tuneDaoImpl.delete(TestDaoHelper.tuneStuttgartId);
		count = TestDaoHelper.tuneDaoImpl.getCount();
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				count == 1);

		TestDaoHelper.tuneDaoImpl.delete(TestDaoHelper.tuneLastUnsId);
		count = TestDaoHelper.tuneDaoImpl.getCount();
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				count == 0);
	}

	@Test
	public void testGetTuneById() {
		TestDaoHelper.addTwoTunes();
		testTuneDto = TestDaoHelper.tuneDaoImpl
				.getTuneById(TestDaoHelper.tuneStuttgartId);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.tuneStuttgartDto.equals(testTuneDto));
	}

	@Test
	public void testGetTunesByName() {
		TestDaoHelper.addTwoTunes();
		testTuneDto = TestDaoHelper.tuneDaoImpl
				.getTuneByName(TestDaoHelper.tuneStuttgartDto.getName());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getByName.fail", tableName),
				TestDaoHelper.tuneStuttgartDto.equals(testTuneDto));
	}

	@Test
	public void testGetTunesByMeter() {
		TestDaoHelper.addTwoTunes();
		tunes = TestDaoHelper.tuneDaoImpl
				.getTunesByMeter(TestDaoHelper.tuneStuttgartDto.getMeterId());
		for (TuneDto dto : tunes) {
			assertTrue(MessageUtils.getMessage("ju", "dao.getByMeter.fail",
					tableName),
					TestDaoHelper.tuneStuttgartDto.getMeterId() == dto
							.getMeterId());
		}
	}

	@Test
	public void testGetTunesByAuthor() {
		TestDaoHelper.addTwoTunes();
		tunes = TestDaoHelper.tuneDaoImpl
				.getTunesByAuthor(TestDaoHelper.tuneStuttgartDto.getAuthorId());
		for (TuneDto dto : tunes) {
			assertTrue(MessageUtils.getMessage("ju", "dao.getByAuthor.fail",
					tableName),
					TestDaoHelper.tuneStuttgartDto.getAuthorId() == dto
							.getAuthorId());
		}
	}

	@Test
	public void testGetAll() {
		TestDaoHelper.addTwoTunes();
		tunes = TestDaoHelper.tuneDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				tunes.size() == 2);
	}

	@Test
	public void testDeleteAll() {
		TestDaoHelper.addTwoTunes();
		TestDaoHelper.tuneDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.tuneDaoImpl.getCount() == 0);
	}

	@Test
	public void testGetCount() {
		TestDaoHelper.addTwoTunes();
		int count = TestDaoHelper.tuneDaoImpl.getCount();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				count == 2);
	}

	@Test
	public void testLookupTunes() {
		TestDaoHelper.addTunesLookup();
		LinkedHashMap<Integer, String> map = TestDaoHelper.tuneDaoImpl
				.getTunesLookup();
		assertFalse(map.size() == 0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(map.get(k).equals(TestDaoHelper.lkpTune[count++]));
		}
	}

	@Test
	public void testWholeLookup() {
		TestDaoHelper.addTwoWholeTunes();
		tunes = TestDaoHelper.tuneDaoImpl.getWholeTunes();
		// Got right size
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				tunes.size() == 2);
		for (int i = 0; i < tunes.size(); i++) {
			TuneDto dto = tunes.get(i);
			if (i == 0) {
				assertTrue(dto.deepEquals(TestDaoHelper.tuneStuttgartDto));
			} else {
				assertTrue(dto.deepEquals(TestDaoHelper.tuneLastUnsDto));
			}
		}
	}

	@After
	public void tearDown() {
		// Delete after each test
		TestDaoHelper.authorDaoImpl.deleteAll();
		TestDaoHelper.meterDaoImpl.deleteAll();
		TestDaoHelper.tuneDaoImpl.deleteAll();
	}
}