package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.services.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meterServiceImpl")
public class MeterServiceImpl implements MeterService {

	@Autowired
	MySqlMeterDaoImpl meterDaoImpl;
	
	@Override
	public int save(MeterDto dto) {
		if(dto.getId()!=0){
			meterDaoImpl.update(dto);
		} else {
			meterDaoImpl.add(dto);
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		return meterDaoImpl.delete(id);
	}

	@Override
	public MeterDto getById(int id) {
		return meterDaoImpl.getMeterById(id);
	}

	@Override
	public List<MeterDto> getAll() {
			return meterDaoImpl.getAll();
	}

	@Override
	public int getCount() {
		return meterDaoImpl.getCount();
	}

	@Override
	public MeterDto getByMeter(MeterDto dto) {
		return meterDaoImpl.getMeterByMeter(dto.getMeter());
	}

}
