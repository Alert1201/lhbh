package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnalDto;

public interface HymnalService {
	public int save(HymnalDto  dto);
	public int delete(int id);
	public HymnalDto getHymnalById(int id);
	public List<HymnalDto> getAll();
	public int getCount();
}
