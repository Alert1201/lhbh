package org.jeff.projs.ihbh.data.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.utils.MessageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class TestMySqlMeterDaoImpl implements TestDaoImpls {

	Object[] tableName = new Object[] { "METER" };
	MeterDto testMeterDto;

	@Before
	public void setUp() throws Exception {
		TestDaoHelper.deleteAll();
	}

	@Test
	public void testAdd() throws MySQLIntegrityConstraintViolationException {
		
		// Test Add
		TestDaoHelper.meterDaoImpl.add(TestDaoHelper.meter8Dto);
		testMeterDto = TestDaoHelper.meterDaoImpl.getMeterByMeter(TestDaoHelper.meter8Dto
				.getMeter());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"1", "METER" }), TestDaoHelper.meter8Dto.equals(testMeterDto));

		TestDaoHelper.meterDaoImpl.add(TestDaoHelper.meter4Dto);
		testMeterDto = TestDaoHelper.meterDaoImpl.getMeterByMeter(TestDaoHelper.meter4Dto
				.getMeter());
		assertTrue(
				MessageUtils.getMessage("ju", "dao.add.fail", new Object[] {
						"2", "METER" }), TestDaoHelper.meter4Dto.equals(testMeterDto));
	}

	@Test
	public void testGetAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		List<MeterDto> meter = TestDaoHelper.meterDaoImpl.getAll();
		assertTrue(MessageUtils.getMessage("ju", "dao.getall.fail", tableName),
				meter.size() == 2);
	}

	@Test
	public void testGetCount() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getcount.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 2);
	}

	@Test
	public void testGetMeterByMeter() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		testMeterDto = TestDaoHelper.meterDaoImpl
				.getMeterByMeter(TestDaoHelper.meter8Dto.getMeter());
		assertTrue(MessageUtils.getMessage("ju", "dao.getByMeter.fail",
				tableName), TestDaoHelper.meter8Dto.equals(testMeterDto));
	}

	@Test
	public void testGetMeterById() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		testMeterDto = TestDaoHelper.meterDaoImpl
				.getMeterById(TestDaoHelper.meter8Id);
		assertTrue(
				MessageUtils.getMessage("ju", "dao.getById.fail", tableName),
				TestDaoHelper.meter8Dto.equals(testMeterDto));
	}

	@Test
	public void testUpdate() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		TestDaoHelper.updateMeterXDto.setId(TestDaoHelper.meter8Id);
		TestDaoHelper.meterDaoImpl.update(TestDaoHelper.updateMeterXDto);
		testMeterDto = TestDaoHelper.meterDaoImpl
				.getMeterById(TestDaoHelper.meter8Id);
		// testUpdateDto and dto2 should be equal
		assertTrue(MessageUtils.getMessage("ju", "dao.update.fail", tableName),
				TestDaoHelper.updateMeterXDto.equals(testMeterDto));
	}

	@Test
	public void testDeleteAll() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		TestDaoHelper.meterDaoImpl.deleteAll();
		assertTrue(
				MessageUtils.getMessage("ju", "dao.delete.all.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 0);
	}

	@Test
	public void testDelete() throws MySQLIntegrityConstraintViolationException {
		TestDaoHelper.addTwoMeters();
		
		TestDaoHelper.meterDaoImpl.delete(TestDaoHelper.meter8Id);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 1);
		TestDaoHelper.meterDaoImpl.delete(TestDaoHelper.meter4Id);
		assertTrue(MessageUtils.getMessage("ju", "dao.delete.fail", tableName),
				TestDaoHelper.meterDaoImpl.getCount() == 0);
	}
	
	@After
	public void tearDown() throws Exception {
		TestDaoHelper.deleteAll();
	}

}
