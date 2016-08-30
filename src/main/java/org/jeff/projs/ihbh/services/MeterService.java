package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.MeterDto;

public interface MeterService {
	public int save(MeterDto dto);
	public int delete(int id);
	public MeterDto getById(int id);
	public List<MeterDto> getAll();
	public int getCount();
	public MeterDto getByMeter(MeterDto dto);
}
