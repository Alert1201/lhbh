package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.UsersDAO;
import org.jeff.projs.ihbh.data.domains.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDaoImpl")
public class MySqlUserDaoImpl implements UsersDAO {

	static Logger log = Logger.getLogger(MySqlUserDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(UserDto dto) {
		// For values use the dto property names, not the sql column names.
		String sql = "Insert into users (username, first_name, last_name, hashed_password, type_id, email, salt) "
				+ " values (:username, :firstName, :lastName, :hashedPassword, :typeId, :email, :salt) ";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(UserDto dto, int updateId) {
		String sql = "update USERS SET username = :username,"
				+ "first_name = :firstName, "
				+ "last_name = :lastName, " 
				+ "hashed_password = :hashedPassword, "
				+ "type_id = :typeId, " 
				+ "email = :email,"
				+ "salt = :salt "
				+ "where id = " + updateId + ";";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE from USERS where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public UserDto getUserById(int id) {
		String sql = "Select * from USERS where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				new UserMapper());
	}

	@Override
	public List<UserDto> getUsersByUserTypeId(int userTypeId) {
		String sql = "Select * from USERS where type_id = :typeId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("typeId", userTypeId);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new UserMapper());
	}

	@Override
	public UserDto getUserByUserName(String userName) {
		String sql = "Select * from USERS where username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userName },
				new UserMapper());
	}

	@Override
	public UserDto getUserByFullName(String firstName, String lastName) {
		try {
			String sql = "Select * from USERS where first_name = ? and last_name = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { firstName,
					lastName }, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public List<UserDto> getAll() {
		String sql = "Select * from USERS";
		return jdbcTemplate.query(sql, new UserMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from USERS";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM USERS";
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

	private static final class UserMapper implements RowMapper<UserDto> {
		// Use column names from table for rs.getXXX() methods.
		public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserDto user = new UserDto();
			user.setId(rs.getInt("id"));
			user.setTypeId(rs.getInt("type_id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setHashedPassword(rs.getString("hashed_password"));
			user.setUsername(rs.getString("username"));
			user.setSalt(rs.getBytes("salt"));
			return user;
		}
	}

	private MapSqlParameterSource setNamedParameter(UserDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("typeId", dto.getTypeId());
		namedParameters.addValue("email", dto.getEmail());
		namedParameters.addValue("firstName", dto.getFirstName());
		namedParameters.addValue("lastName", dto.getLastName());
		namedParameters.addValue("username", dto.getUsername());
		namedParameters.addValue("hashedPassword", dto.getHashedPassword());
		namedParameters.addValue("salt", dto.getSalt());
		return namedParameters;
	}
}
