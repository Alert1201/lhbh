package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.AuthorDAO;
import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("authorDaoImpl")
public class MySqlAuthorDaoImpl implements AuthorDAO {

	static Logger log = Logger.getLogger(MySqlAuthorDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	// Tested
	public int add(AuthorDto dto) {
		String sql = "Insert into authors (first_name, last_name, DOB_month, "
				+ "DOB_year, DOD_month, DOD_year, DOD_circa, DOB_circa, nationality, bio) "
				+ "values (:firstName, :lastName, "
				+ ":DOB_month, :DOB_year, :DOD_month, :DOD_year, :DOD_circa, :DOB_circa, :nationality, :bio)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	private MapSqlParameterSource setNamedParameter(AuthorDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstName", dto.getFirstName());
		namedParameters.addValue("lastName", dto.getLastName());
		namedParameters.addValue("DOB_month", dto.getDobMonth());
		namedParameters.addValue("DOB_year", dto.getDobYear());
		namedParameters.addValue("DOD_month", dto.getDodMonth());
		namedParameters.addValue("DOD_circa", dto.getDodCirca());
		namedParameters.addValue("DOB_circa", dto.getDobCirca());
		namedParameters.addValue("DOD_year", dto.getDodYear());
		namedParameters.addValue("nationality", dto.getNationality());
		namedParameters.addValue("bio", dto.getBio());
		return namedParameters;
	}

	public AuthorDto getAuthorById(int id) {
		try {
			String sql = "Select * from authors where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id },
					new AuthorMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	// Tested
	public AuthorDto getAuthorByFullName(String first_name, String last_name) {
		try {
			String sql = "Select * from authors where first_name = ? and last_name = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { first_name,
					last_name }, new AuthorMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public int update(AuthorDto dto) {
		String sql = "update authors SET first_name = :firstName, "
				+ "last_name = :lastName, " + "DOB_month = :DOB_month, "
				+ "DOB_year = :DOB_year, " + "DOD_month = :DOD_month,"
				+ "DOD_year = :DOD_year, " + "DOD_circa = :DOD_circa, "
				+ "DOB_circa = :DOB_circa, " + "nationality = :nationality, "
				+ "bio = :bio where id = " + dto.getId() + ";";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	public int delete(int id) {
		String sql = "DELETE from authors where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	public int deleteAll() {
		String sql = "DELETE from authors";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<AuthorDto> getAll() {
		String sql = "Select * from authors";
		return jdbcTemplate.query(sql, new AuthorMapper());
	}

	public int getCount() {
		String sql = "SELECT COUNT(*) FROM Authors";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	private static final class AuthorMapper implements RowMapper<AuthorDto> {
		// Use column names from table for rs.getXXX() methods.
		@Override
		public AuthorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			AuthorDto author = new AuthorDto();
			author.setBio(rs.getBytes("bio"));
			author.setDobCirca(rs.getString("DOB_circa"));
			author.setDobMonth(rs.getString("DOB_month"));
			author.setDobYear(rs.getString("DOB_year"));
			author.setDodCirca(rs.getString("DOD_circa"));
			author.setDodMonth(rs.getString("DOD_month"));
			author.setDodYear(rs.getString("DOD_year"));
			author.setFirstName(rs.getString("first_name"));
			author.setLastName(rs.getString("last_name"));
			author.setId(rs.getInt("id"));
			author.setNationality(rs.getString("nationality"));
			author.setBio(rs.getBytes("bio"));
			return author;
		}

	}
}
