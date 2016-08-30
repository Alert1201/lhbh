package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.BaseDto;

public interface BaseService {
	//Crud Commands
	public BaseDto save(BaseDto dto);
	public BaseDto getById(int id);
	public List<BaseDto> getAll();
	public void delete(BaseDto dto);
}
