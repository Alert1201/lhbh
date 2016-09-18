package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.services.MeterService;
import org.jeff.projs.ihbh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service("meterServiceImpl")
public class MeterServiceImpl implements MeterService {

	@Autowired
	MySqlMeterDaoImpl meterDaoImpl;

	@Override
	public int save(MeterDto dto) {
		try {
			if (dto.getId() != 0) {
				meterDaoImpl.update(dto);
			} else {
				meterDaoImpl.add(dto);
			}
		} catch (MySQLIntegrityConstraintViolationException ex) {
			return Constants.DB_INTEGERITY_CONSTRAINT_VIOLATION_EXCEPTION_RETVALUE;
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		try {
			return meterDaoImpl.delete(id);
		} catch (DataIntegrityViolationException e) {
			return Constants.DB_DATA_INTEGRITY_VIOLATION_EXCEPTION_RETVALUE;
		}
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
