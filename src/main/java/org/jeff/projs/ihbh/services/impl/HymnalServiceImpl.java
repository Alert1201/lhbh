package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.jeff.projs.ihbh.services.HymnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hymnalServiceImpl")
public class HymnalServiceImpl implements HymnalService {

	@Autowired
	MySqlHymnalDaoImpl hymnalDaoImpl;

	@Override
	public int save(HymnalDto dto) {
		if (dto.getId() != 0) {
			return hymnalDaoImpl.update(dto);
		} else {
			return hymnalDaoImpl.add(dto);
		}
	}

	@Override
	public int delete(int id) {
		return hymnalDaoImpl.delete(id);
	}

	@Override
	public HymnalDto getHymnalById(int id) {
		return hymnalDaoImpl.getHymnalById(id);
	}

	@Override
	public List<HymnalDto> getAll() {
		return hymnalDaoImpl.getAll();
	}

	@Override
	public int getCount() {
		return hymnalDaoImpl.getCount();
	}
}
