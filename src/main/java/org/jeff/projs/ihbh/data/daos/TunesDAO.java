package org.jeff.projs.ihbh.data.daos;

import java.util.LinkedHashMap;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.TuneDto;

public interface TunesDAO {
	public int add(TuneDto dto);
	public int update(TuneDto dto);
	public int delete(int id);
	public TuneDto getTuneById(int id);
	public TuneDto getTuneByName(String name);
	public List<TuneDto> getTunesByMeter(int meterId);
	public List<TuneDto> getTunesByAuthor(int authorId);
	public List<TuneDto> getAll();
	public int deleteAll();
	public int getCount();
	public List<TuneDto> getWholeTunes();
	LinkedHashMap<Integer, String> getTunesLookup();
}

