package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.TuneDto;

public interface TuneService {
	public int save(TuneDto dto);
	public int delete(int id);
	public TuneDto getById(int id);
	public TuneDto getByName(String name);
	public List<TuneDto> getByMeter(int meterId);
	public List<TuneDto> getByAuthor(int authorId);
	public List<TuneDto> getWholeTunes();
	public List<TuneDto> getAll();
	public int getCount();
}
