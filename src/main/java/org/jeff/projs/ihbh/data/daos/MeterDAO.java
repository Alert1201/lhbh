package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.MeterDto;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public interface MeterDAO {
	public int add(MeterDto  dto) throws MySQLIntegrityConstraintViolationException;
	public int update(MeterDto dto) throws MySQLIntegrityConstraintViolationException;
	public int delete(int id);
	public MeterDto getMeterById(int id);
	public MeterDto getMeterByMeter(String meter);
	public List<MeterDto> getAll();
	public int deleteAll();
	public int getCount();
}
