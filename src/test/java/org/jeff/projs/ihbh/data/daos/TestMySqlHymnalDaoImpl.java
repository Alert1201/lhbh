package org.jeff.projs.ihbh.data.daos;

/**
 * 
 */

import static org.junit.Assert.*;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jeff.projs.ihbh.data.daos.TestDaoHelper;
import org.jeff.projs.ihbh.utils.MessageUtils;

/**
 * @author Jeff Sulman
 *
 */
public class TestMySqlHymnalDaoImpl {
	HymnalDto testDto;
	Object[] tableName = new Object[] {"HYMNAL"};
	
	@Before
	public void setUp() throws Exception {
		TestDaoHelper.deleteAll();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd(){
		//Test add, getCount, getAll
		TestDaoHelper.hymnalDaoImpl.add(TestDaoHelper.hymnalTrinityDto);
		testDto = TestDaoHelper.hymnalDaoImpl.getHymnalByAbbreviation(TestDaoHelper.hymnalTrinityDto.getAbbreviation());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "HYMNAL" }), TestDaoHelper.hymnalTrinityDto.equals(testDto));
		
		TestDaoHelper.hymnalDaoImpl.add(TestDaoHelper.hymnalFamilyDto);
		testDto = TestDaoHelper.hymnalDaoImpl.getHymnalByAbbreviation(TestDaoHelper.hymnalFamilyDto.getAbbreviation());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "HYMNAL" }), TestDaoHelper.hymnalFamilyDto.equals(testDto));
	}
	
	@Test
	public void testGetAll(){
		TestDaoHelper.addTwoHymnals();
		List<HymnalDto> hymnals = TestDaoHelper.hymnalDaoImpl.getAll();
		
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName), hymnals.size()==2);	
	}

	@Test
	public void testGetByName(){
		TestDaoHelper.addTwoHymnals();
		HymnalDto testDto = TestDaoHelper.hymnalDaoImpl.getHymnalByName(TestDaoHelper.hymnalTrinityDto.getName());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByName.fail",
				tableName), testDto.equals(TestDaoHelper.hymnalTrinityDto));
	}
	
	@Test
	public void testGetByAbbreviation(){
		//Test getByAbbreviation
		TestDaoHelper.addTwoHymnals();
		testDto = TestDaoHelper.hymnalDaoImpl.getHymnalByAbbreviation(TestDaoHelper.hymnalTrinityDto.getAbbreviation());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByAbbrev.fail",
				tableName), testDto.equals(TestDaoHelper.hymnalTrinityDto));
	}
	
	@Test
	public void testGetById(){
		TestDaoHelper.addTwoHymnals();
		testDto = TestDaoHelper.hymnalDaoImpl.getHymnalById(TestDaoHelper.hymnalTrinityId);
		assertTrue(MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.hymnalTrinityDto.equals(testDto));	
	}
	
	@Test
	public void testUpdate(){
		//Test Update
		TestDaoHelper.addTwoHymnals();
		TestDaoHelper.hymnalUpateDto.setId(TestDaoHelper.hymnalTrinityId);
		TestDaoHelper.hymnalDaoImpl.update(TestDaoHelper.hymnalUpateDto);
		testDto = TestDaoHelper.hymnalDaoImpl.getHymnalById(TestDaoHelper.hymnalTrinityId);
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName), 
				TestDaoHelper.hymnalUpateDto.equals(testDto));
	}
	
	@Test
	public void testDeleteAll(){
		TestDaoHelper.addTwoHymnals();
		TestDaoHelper.hymnalDaoImpl.deleteAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName), 
				TestDaoHelper.hymnalDaoImpl.getCount()==0);
	}
	

	@Test
	public void testDelete(){
		TestDaoHelper.addTwoHymnals();
	
		TestDaoHelper.hymnalDaoImpl.delete(TestDaoHelper.hymnalFamilyId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName), 
				TestDaoHelper.hymnalDaoImpl.getCount()==1);
		
		TestDaoHelper.hymnalDaoImpl.delete(TestDaoHelper.hymnalTrinityId);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName), 
				TestDaoHelper.hymnalDaoImpl.getCount()==0);
	}
	
}
