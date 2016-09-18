package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.PlayListDetailDto;

public interface PlayListDetailDao {
	public int add(PlayListDetailDto dto);
	public int deleteAll();
	public int deleteByPlayList(int playListId);
	public int deleteSingleEntry(int id);
	public List<PlayListDetailDto> getAll();
	public List<PlayListDetailDto> getByPlayList(int playListId);
	public PlayListDetailDto getSingleEntry(int id);
	public int getCount();
	public int getCountByPlayList(int playListId);
	public int update(PlayListDetailDto dto);
}
