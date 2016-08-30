package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.MeterDto;

public interface MeterDAO {
	public int add(MeterDto  dto);
	public int update(MeterDto dto);
	public int delete(int id);
	public MeterDto getMeterById(int id);
	public MeterDto getMeterByMeter(String meter);
	public List<MeterDto> getAll();
	public int deleteAll();
	public int getCount();
}
