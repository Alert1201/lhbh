package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.PlayListDetailDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class TestMySqlPlayListDetailDaoImpl {

	Object[] tableName = new Object[] { "PLAY_LIST_DETAIL" };
	PlayListDetailDto testPlayListDetailDto;

	List<PlayListDetailDto> list = null;

	@Before
	public void setUp() {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd() throws MySQLIntegrityConstraintViolationException {
		//TestDaoHelper.addTwoUsers();
		TestDaoHelper.addTwoHymns();
		TestDaoHelper.addFourPlayLists();
		TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto.setHymnId(TestDaoHelper.hymnAndCanId);
		TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto.setHymnId(TestDaoHelper.hymnAndCanId);
		TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto.setHymnId(TestDaoHelper.hymnHowGrId);
		TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto.setPlayListId(TestDaoHelper.jeffs1stPlayListId);
		TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto.setPlayListId(TestDaoHelper.jeffs1stPlayListId);
		TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto.setPlayListId(TestDaoHelper.jeffs1stPlayListId);

		TestDaoHelper.playListFranks1stGreatIsThyFai1Dto.setHymnId(TestDaoHelper.hymnHowGrId);
		TestDaoHelper.playListFranks1stGreatIsThyFai2Dto.setHymnId(TestDaoHelper.hymnHowGrId);
		TestDaoHelper.playListFranks1stGreatIsThyFai3Dto.setHymnId(TestDaoHelper.hymnAndCanId);
		TestDaoHelper.playListFranks1stGreatIsThyFai1Dto.setPlayListId(TestDaoHelper.franks1stPlayListId);
		TestDaoHelper.playListFranks1stGreatIsThyFai2Dto.setPlayListId(TestDaoHelper.franks1stPlayListId);
		TestDaoHelper.playListFranks1stGreatIsThyFai3Dto.setPlayListId(TestDaoHelper.franks1stPlayListId);

		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto);
		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto);
		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto);

		list = TestDaoHelper.playListDetailDaoImpl.getByPlayList(TestDaoHelper.jeffs1stPlayListId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == list.size());

		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto.equals(list.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto.equals(list.get(1)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto.equals(list.get(2)));

		TestDaoHelper.playListDetailDaoImpl.deleteAll();

		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListFranks1stGreatIsThyFai1Dto);
		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListFranks1stGreatIsThyFai2Dto);
		TestDaoHelper.playListDetailDaoImpl.add(TestDaoHelper.playListFranks1stGreatIsThyFai3Dto);

		list = TestDaoHelper.playListDetailDaoImpl.getByPlayList(TestDaoHelper.franks1stPlayListId);

		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == list.size());

		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai1Dto.equals(list.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai2Dto.equals(list.get(1)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai3Dto.equals(list.get(2)));

		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == list.size());

	}

	@Test
	public void testDeleteAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();

		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 6);

		TestDaoHelper.playListDetailDaoImpl.deleteAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 0);
	}

	@Test
	public void testDeleteByPlayList() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 6);

		TestDaoHelper.playListDetailDaoImpl.deleteByPlayList(TestDaoHelper.jeffs1stPlayListId);

		assertTrue(MessageUtils.getMessage("ju", "dao.deleteByPlayList.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 3);

		TestDaoHelper.playListDetailDaoImpl.deleteByPlayList(TestDaoHelper.franks1stPlayListId);

		assertTrue(MessageUtils.getMessage("ju", "dao.deleteByPlayList.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 0);
	}

	@Test
	public void testDeleteSingleEntry() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		int count;
		list = TestDaoHelper.playListDetailDaoImpl.getAll();
		count = list.size();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == count);
		for (PlayListDetailDto dto : list) {
			TestDaoHelper.playListDetailDaoImpl.deleteSingleEntry(dto.getId());
			assertTrue(MessageUtils.getMessage("ju", "dao.deleteBySingleEntry.fail", tableName),
					TestDaoHelper.playListDetailDaoImpl.getCount() == --count);
		}
	}

	@Test
	public void testGetAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		list = TestDaoHelper.playListDetailDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				list.size() == TestDaoHelper.playListDetailDaoImpl.getCount());

		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto.equals(list.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto.equals(list.get(1)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto.equals(list.get(2)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai1Dto.equals(list.get(3)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai2Dto.equals(list.get(4)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai3Dto.equals(list.get(5)));
	}

	@Test
	public void testGetByPlayList() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 6);

		list = TestDaoHelper.playListDetailDaoImpl.getByPlayList(TestDaoHelper.jeffs1stPlayListId);

		assertTrue(MessageUtils.getMessage("ju", "dao.deleteByPlayList.fail", tableName), list.size() == 3);

		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly1Dto.equals(list.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly2Dto.equals(list.get(1)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListJeffs1stHolyHolyHoly3Dto.equals(list.get(2)));

		list = TestDaoHelper.playListDetailDaoImpl.getByPlayList(TestDaoHelper.franks1stPlayListId);

		assertTrue(MessageUtils.getMessage("ju", "dao.deleteByPlayList.fail", tableName), list.size() == 3);

		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai1Dto.equals(list.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai2Dto.equals(list.get(1)));
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
				TestDaoHelper.playListFranks1stGreatIsThyFai3Dto.equals(list.get(2)));
	}

	@Test
	public void testGetBySingleEntry() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		list = TestDaoHelper.playListDetailDaoImpl.getAll();
		for (PlayListDetailDto dto : list) {
			testPlayListDetailDto = TestDaoHelper.playListDetailDaoImpl.getSingleEntry(dto.getId());
			assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
					dto.equals(testPlayListDetailDto));
		}
	}

	@Test
	public void testGetCount() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 6);
	}

	@Test
	public void testGetCountByPlayList() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		assertTrue(MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCount() == 6);

		assertTrue(MessageUtils.getMessage("ju", "dao.getCountByPlayList.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCountByPlayList(TestDaoHelper.jeffs1stPlayListId) == 3);

		assertTrue(MessageUtils.getMessage("ju", "dao.getCountByPlayList.fail", tableName),
				TestDaoHelper.playListDetailDaoImpl.getCountByPlayList(TestDaoHelper.franks1stPlayListId) == 3);
	}

	@Test
	public void testUpdate() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addPlayListDetails();
		list = TestDaoHelper.playListDetailDaoImpl.getByPlayList(TestDaoHelper.jeffs1stPlayListId);
		for (PlayListDetailDto dto : list) {
			testPlayListDetailDto = TestDaoHelper.playListDetailDaoImpl.getSingleEntry(dto.getId());
			testPlayListDetailDto.setHymnId(TestDaoHelper.hymnAndCanId);
			testPlayListDetailDto.setPart(2);
			testPlayListDetailDto.setType(2);
			TestDaoHelper.playListDetailDaoImpl.update(testPlayListDetailDto);
			dto = TestDaoHelper.playListDetailDaoImpl.getSingleEntry(dto.getId());
			assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "2", "PLAY_LIST_DETAIL" }),
					dto.equals(testPlayListDetailDto));
		}
	}

	@After
	public void tearDown() {
		TestDaoHelper.deleteAll();
	}
}
