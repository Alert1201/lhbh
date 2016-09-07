package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.PlayListDto;

public interface PlayListDAO {
	public PlayListDto getPlayListById(int id);
	public int add(PlayListDto  dto);
	public int update(PlayListDto dto);
	public List<PlayListDto> getAll();
	public List<PlayListDto> getAllByUser(int userId);
	public int deleteAll();
	public int getCount();
	public int deleteAllByUser(int userId);
	public int getCountByUser(int userId);
}
