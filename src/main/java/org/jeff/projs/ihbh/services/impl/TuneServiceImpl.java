package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlTuneDaoImpl;
import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.jeff.projs.ihbh.services.TuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tuneServiceImpl")
public class TuneServiceImpl implements TuneService {

	@Autowired
	MySqlTuneDaoImpl tuneDaoImpl;
	
	@Override
	public int save(TuneDto dto) {
		if (dto.getId() != 0) {
			return tuneDaoImpl.update(dto);
		} else {
			return tuneDaoImpl.add(dto);
		}
	}

	@Override
	public int delete(int id) {
		return tuneDaoImpl.delete(id);
	}

	@Override
	public TuneDto getById(int id) {
		return tuneDaoImpl.getTuneById(id);
	}

	@Override
	public TuneDto getByName(String name) {
		return tuneDaoImpl.getTuneByName(name);
	}

	@Override
	public List<TuneDto> getByMeter(int meterId) {
		return tuneDaoImpl.getTunesByMeter(meterId);
	}

	@Override
	public List<TuneDto> getByAuthor(int authorId) {
		return tuneDaoImpl.getTunesByAuthor(authorId);
	}

	@Override
	public List<TuneDto> getWholeTunes() {
		return tuneDaoImpl.getWholeTunes();
	}

	@Override
	public List<TuneDto> getAll() {
		return tuneDaoImpl.getAll();
	}

	@Override
	public int getCount() {
		return tuneDaoImpl.getCount();
	}
}
