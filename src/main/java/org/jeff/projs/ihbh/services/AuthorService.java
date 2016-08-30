package org.jeff.projs.ihbh.services;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.AuthorDto;

public interface AuthorService {
	public int save(AuthorDto  dto);
	public int delete(int id);
	public AuthorDto getById(int id);
	public AuthorDto getByFullName(AuthorDto  dto);
	public List<AuthorDto> getAll();
	public int getCount();
}
