package org.jeff.projs.ihbh.data.daos;

import java.util.LinkedHashMap;
import java.util.List;

import org.jeff.projs.ihbh.data.domains.UserTypeDto;

public interface UserTypesDAO {
	public int add(UserTypeDto  dto);
	public int update(UserTypeDto dto);
	public int delete(int id);
	public UserTypeDto getUserTypeById(int id);
	public UserTypeDto getUserTypeByDescription(String desc);
	public UserTypeDto getUserTypeByUserType(String userType);
	public List<UserTypeDto> getAll();
	public int deleteAll();
	public int getCount();
	LinkedHashMap<Integer, String> getUserTypesLookup();
}
