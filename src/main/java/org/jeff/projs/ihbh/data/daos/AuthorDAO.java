package org.jeff.projs.ihbh.data.daos;

import java.util.List;

import org.jeff.projs.ihbh.data.domains.AuthorDto;

public interface AuthorDAO {
	public int add(AuthorDto  dto);
	public int update(AuthorDto  dto);
	public int delete(int id);
	public AuthorDto getAuthorById(int id);
	public AuthorDto getAuthorByFullName(String first_name, String last_name);
	public List<AuthorDto> getAll();
	public int deleteAll();
	public int getCount();
}
