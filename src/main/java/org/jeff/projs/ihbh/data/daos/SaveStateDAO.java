package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.SaveStateDto;

public interface SaveStateDAO {
	public int add(SaveStateDto  dto);
	public int update(SaveStateDto dto, int id);
	public int delete(int id);
	public List<SaveStateDto> getAll();
	public int deleteAll();
	public int getCount();
	public SaveStateDto getSaveStateById(int id);
	public SaveStateDto getSaveStateByUserId(int id);
}
