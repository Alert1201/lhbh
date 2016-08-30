package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnDto;

public interface HymnService {
	public int save(HymnDto dto);
	public int delete(HymnDto dto);
	public HymnDto getById(HymnDto dto);
	public List<HymnDto> getAll();
	public int getCount();
	public List<HymnDto> getByAuthor(HymnDto dto);
	public List<HymnDto> getByCategory(HymnDto dto);
	public List<HymnDto> getByTitle(HymnDto dto);
	public List<HymnDto> getByHymnalNumber(HymnDto dto);
	public List<HymnDto> getByTune(HymnDto dto);
	public List<HymnDto> getByFirstLine(HymnDto dto);
}
