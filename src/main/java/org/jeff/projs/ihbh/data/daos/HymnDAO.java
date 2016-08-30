package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.HymnDto;

public interface HymnDAO {
	public int add(HymnDto  dto);
	public int update(HymnDto dto, int id);
	public int delete(int id);
	public HymnDto getHymnById(int id);
	public List<HymnDto> getAll();
	public List<HymnDto> getHymnsByAuthor(int AuthorId);
	public List<HymnDto> getHymnsByCategory(int CategoryId);
	public List<HymnDto> getHymnsByTitle(String title);
	public HymnDto getHymnByHymnalNumber(int hymnalId, int hymnNumber);
	public List<HymnDto> getHymnsByTune(int TuneId);
	public List<HymnDto> getHymnsByFirstLine(String firstLine);
	public int getCount();
	public int deleteAll();
}
