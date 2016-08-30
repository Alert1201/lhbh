package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserDto;

public interface UserService {
	public int save(UserDto dto);
	public int delete(UserDto dto);
	public UserDto getById(UserDto dto);
	public List<UserDto> getAll();
	public int getCount();
	public UserDto getByUserName(UserDto dto);
	public UserDto getByFullName(UserDto dto);
	public List<UserDto> getByUserTypeId(UserDto dto);
}
