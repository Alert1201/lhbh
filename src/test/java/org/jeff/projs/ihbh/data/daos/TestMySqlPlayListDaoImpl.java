/**
 * 
 */
package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.data.domains.PlayListDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeff Sulman
 *
 */
public class TestMySqlPlayListDaoImpl {
	Object[] tableName = new Object[] { "PLAY_LIST" };
	PlayListDto testPlayListDto;

	static List<PlayListDto> playLists;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd() {
		TestDaoHelper.addTwoUsers();
		TestDaoHelper.jeffs1stPlayList.setUserId(TestDaoHelper.userJeffId);
		TestDaoHelper.playListDaoImpl.add(TestDaoHelper.jeffs1stPlayList);
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.userJeffId);
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.jeffs1stPlayList.equals(playLists.get(0)));

		TestDaoHelper.franks1stPlayList.setUserId(TestDaoHelper.userFrankId);
		TestDaoHelper.playListDaoImpl.add(TestDaoHelper.franks1stPlayList);
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.userFrankId);
		assertTrue(MessageUtils.getMessage("ju", "dao.add.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.franks1stPlayList.equals(playLists.get(0)));
	}

	@Test
	public void testUpdate() {
		TestDaoHelper.addTwoUsers();

		TestDaoHelper.jeffs1stPlayList.setUserId(TestDaoHelper.userJeffId);
		TestDaoHelper.playListDaoImpl.add(TestDaoHelper.jeffs1stPlayList);
		testPlayListDto = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.userJeffId).get(0);
		TestDaoHelper.updatedPlayList.setUserId(TestDaoHelper.userFrankId);
		TestDaoHelper.updatedPlayList.setId(testPlayListDto.getId());
		TestDaoHelper.playListDaoImpl.update(TestDaoHelper.updatedPlayList);
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.userFrankId);
		// testUpdateDto and dto2 should be equal

		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.updatedPlayList.equals(playLists.get(0)));
	}
	
	@Test
	public void testGetAllByUser(){
		TestDaoHelper.addFourPlayLists();
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.jeffs1stPlayList.getUserId());
		assertTrue(MessageUtils.getMessage("ju", "dao.getAllByUser.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.jeffs1stPlayList.equals(playLists.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.getAllByUser.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.jeffs2ndPlayList.equals(playLists.get(1)));
		
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.franks1stPlayList.getUserId());
		assertTrue(MessageUtils.getMessage("ju", "dao.getAllByUser.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.franks1stPlayList.equals(playLists.get(0)));
		assertTrue(MessageUtils.getMessage("ju", "dao.getAllByUser.fail", new Object[] { "1", "PLAY_LIST" }),
				TestDaoHelper.franks2ndPlayList.equals(playLists.get(1)));
	}
		
	@Test
	public void testGetAll(){
		TestDaoHelper.addFourPlayLists();
		playLists = TestDaoHelper.playListDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				playLists.size() == 4);
	}
	
	
	@Test
	public void testDeleteAll(){
		TestDaoHelper.addFourPlayLists();
		TestDaoHelper.playListDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCount() == 0);
	}
	
	@Test
	public void testDeleteAllByUser(){
		TestDaoHelper.addFourPlayLists();
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCount() == 4);
		TestDaoHelper.playListDaoImpl.deleteAllByUser(TestDaoHelper.userJeffId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCount() == 2);
		TestDaoHelper.playListDaoImpl.deleteAllByUser(TestDaoHelper.userFrankId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCount() == 0);
	}
	
	@Test
	public void testGetCount(){
		TestDaoHelper.addFourPlayLists();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCount() == 4);
	}

	@Test
	public void testGetCountByUser(){
		TestDaoHelper.addFourPlayLists();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.playListDaoImpl.getCountByUser(TestDaoHelper.userJeffId) == 2);		
	}

	@Test
	public void testGetPlayListById(){
		TestDaoHelper.addFourPlayLists();
		playLists = TestDaoHelper.playListDaoImpl.getAllByUser(TestDaoHelper.userJeffId);
		testPlayListDto = TestDaoHelper.playListDaoImpl.getPlayListById(playLists.get(0).getId());
		assertTrue(MessageUtils.getMessage("ju", "dao.getAllByUser.fail", new Object[] { "1", "PLAY_LIST" }),
				testPlayListDto.equals(playLists.get(0)));
	}
	
	@After
	public void tearDown() {
		TestDaoHelper.deleteAll();
	}
}
