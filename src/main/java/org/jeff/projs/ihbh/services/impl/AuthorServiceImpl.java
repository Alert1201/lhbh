package org.jeff.projs.ihbh.services.impl;

import java.util.List;

import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorsServiceImpl")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	MySqlAuthorDaoImpl authorDaoImpl;

	@Override
	public int save(AuthorDto dto) {
		if (dto.getId() != 0) {
			authorDaoImpl.update(dto);
		} else {
			authorDaoImpl.add(dto);
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		return authorDaoImpl.delete(id);

	}

	@Override
	public AuthorDto getById(int id) {
		return authorDaoImpl.getAuthorById(id);
	}

	@Override
	public AuthorDto getByFullName(AuthorDto dto) {
		// TODO Auto-generated method stub
		return authorDaoImpl.getAuthorByFullName(dto.getFirstName(),
				dto.getLastName());
	}

	@Override
	public List<AuthorDto> getAll() {
		return authorDaoImpl.getAll();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
