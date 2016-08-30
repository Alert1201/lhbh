package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.SaveStateDto;

public interface SaveStateService {
	public int save(SaveStateDto dto);
	public int delete(SaveStateDto dto);
	public SaveStateDto getById(SaveStateDto dto);
	public List<SaveStateDto> getAll();
	public int getCount();
	public SaveStateDto getByUserId(SaveStateDto dto);
}
