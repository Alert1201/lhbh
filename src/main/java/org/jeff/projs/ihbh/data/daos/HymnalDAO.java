package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnalDto;



public interface HymnalDAO {
	public int add(HymnalDto  dto);
	public int update(HymnalDto dto);
	public int delete(int id);
	public HymnalDto getHymnalById(int id);
	public List<HymnalDto> getAll();
	public int deleteAll();
	public int getCount();
}
