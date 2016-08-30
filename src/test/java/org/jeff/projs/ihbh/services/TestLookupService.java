package org.jeff.projs.ihbh.services;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jeff.projs.ihbh.data.daos.TestDaoHelper;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLookupService {

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.authorDaoImpl.deleteAll();
		TestDaoHelper.meterDaoImpl.deleteAll();
		TestDaoHelper.tuneDaoImpl.deleteAll();
		TestDaoHelper.categoryDaoImpl.deleteAll();
		TestDaoHelper.hymnalDaoImpl.deleteAll();
		TestDaoHelper.userDaoImpl.deleteAll();
		TestDaoHelper.userTypeDaoImpl.deleteAll();
	}

	@Test
	public void testpopulateAuthors() {
		TestDaoHelper.addAuthorLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateAuthors();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Authors Full Name"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(
					MessageUtils.getMessage("ju", "service.lkp.fail", "Authors Full Name"),
					map.get(k).equals(
							TestDaoHelper.lkpAuthorFnameLname[count++]));
		}
	}

	@Test
	public void testPopulateMeters() {
		TestDaoHelper.addMeterLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateMeters();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Meters"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Meters"), StringUtils.contains(map.get(k),
					TestDaoHelper.lkpMeter[count++]));
		}
	}

	@Test
	public void testPopulateTunes() {
		TestDaoHelper.addTunesLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateTunes();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Tunes"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Tunes"), StringUtils.contains(map.get(k),
					TestDaoHelper.lkpTune[count++]));
		}
	}

	@Test
	public void testPopulateCategoriesNoParent() {
		TestDaoHelper.addCategoryLookup();
		// Test with parId = 0
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateCategories(0);
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Categories With No Parent"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) { //
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Categories No Parent"),
					map.get(k).equals(TestDaoHelper.lkpCatNoPar[count++]));
		}
	}

	@Test
	public void testPopulateCategoriesWithParent() { //
		TestDaoHelper.addCategoryLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateCategories(TestDaoHelper.parIdLookup);
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Cagories With Parent"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Categoreis With Parent"),
					map.get(k).equals(TestDaoHelper.lkpCatWithPar[count++]));
		}
	}

	@Test
	public void testPopulateHymnalsByName() { //
		TestDaoHelper.addHymnalLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateHymnalsByName();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Hymnas By Name"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) { //
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Hymnals By Name"),
					map.get(k).equals(TestDaoHelper.lkpHymnalName[count++]));
		}
	}

	@Test
	public void testPopulateHymnalsByAbbrev() { //
		TestDaoHelper.addHymnalLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateHymnalsByAbbrev();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "Hymnals By Abbreviation"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) { //
			assertTrue(
					MessageUtils.getMessage("ju", "service.lkp.fail",
							"Hymnals By Abbreviation"),
					map.get(k).equals(
							TestDaoHelper.lkpHymnalAbbreviation[count++]));
		}
	}

	@Test
	public void testPopulateUserTypes() { //
		TestDaoHelper.addUserTypeLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateUserTypes();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "User Types"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) { //
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"User Types"),
					map.get(k).equals(TestDaoHelper.lkpUserType[count++]));
		}
	}

	@Test
	public void testPopulateUsersByFullName() { 
		TestDaoHelper.addUsersLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateUsersByFullName();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "User by Full Name"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) {
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Users By Full Name"),
					map.get(k).equals(TestDaoHelper.lkpUserFnameLname[count++]));
		}
	}

	@Test
	public void testPopulateUsersByUserName() { // TestDaoHelper.addTuneLookup();
		TestDaoHelper.addUsersLookup();
		LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) TestDaoHelper.lookupServiceImpl
				.populateUsersByUserName();
		assertFalse(MessageUtils.getMessage("ju", "service.lkp.fail.no.rows", "User by UserName"), map.size()==0);
		Set<Integer> keys = map.keySet();
		int count = 0;
		for (Integer k : keys) { //
			assertTrue(MessageUtils.getMessage("ju", "service.lkp.fail",
					"Users By User Name"),
					map.get(k).equals(TestDaoHelper.lkpUserUserName[count++]));
		}
	}

	@After
	public void tearDown() {
		TestDaoHelper.authorDaoImpl.deleteAll();
		TestDaoHelper.meterDaoImpl.deleteAll();
		TestDaoHelper.tuneDaoImpl.deleteAll();
		TestDaoHelper.categoryDaoImpl.deleteAll();
		TestDaoHelper.hymnalDaoImpl.deleteAll();
		TestDaoHelper.userDaoImpl.deleteAll();
		TestDaoHelper.userTypeDaoImpl.deleteAll();
	}
}
