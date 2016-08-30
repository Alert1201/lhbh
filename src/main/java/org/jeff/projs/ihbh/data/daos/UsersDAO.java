package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserDto;


public interface UsersDAO {
	public int add(UserDto  dto);
	public int update(UserDto dto, int id);
	public int delete(int id);
	public UserDto getUserById(int id);
	public UserDto getUserByUserName(String userName);
	public UserDto getUserByFullName(String firstName,  String lastName);
	public List<UserDto> getUsersByUserTypeId(int id);
	public List<UserDto> getAll();
	public int deleteAll();
	public int getCount();
}
