package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlUserTypeDaoImpl;
import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.jeff.projs.ihbh.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userTypeServiceImpl")
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	MySqlUserTypeDaoImpl userTypeDaoImpl;
	
	@Override
	public int save(UserTypeDto dto) {
		if (dto.getId() != 0) {
			return userTypeDaoImpl.update(dto);
		} else {
			return userTypeDaoImpl.add(dto);
		}
	}

	@Override
	public int delete(int id) {
		return userTypeDaoImpl.delete(id);
	}

	@Override
	public UserTypeDto getUserTypeById(int id) {
		return userTypeDaoImpl.getUserTypeById(id);
	}

	@Override
	public UserTypeDto getUserTypeByDescription(UserTypeDto dto) {
		return userTypeDaoImpl.getUserTypeByDescription(dto.getDescription());
	}

	@Override
	public UserTypeDto getUserTypeByUserType(UserTypeDto dto) {
		return userTypeDaoImpl.getUserTypeByUserType(dto.getUserType());
	}

	@Override
	public List<UserTypeDto> getAll() {
		return userTypeDaoImpl.getAll();
		
	}

	@Override
	public int getCount() {
		return userTypeDaoImpl.getCount();
	}

}
