package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.jeff.projs.ihbh.data.daos.UserTypesDAO;
import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.jeff.projs.ihbh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userTypeDaoImpl")
public class MySqlUserTypeDaoImpl implements UserTypesDAO {

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(UserTypeDto dto) {
		// For values use the dto property names, not the sql column names.
		String sql = "Insert into USER_TYPES (user_type, description) " + "values (:userType, :description)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(UserTypeDto dto) {
		String sql = "update user_types SET user_type = :userType " + ", description = :description" + " where id = "
				+ dto.getId();
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		try {
			String sql = "DELETE from user_types where id = :id";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id", id);
			return namedParameterJdbcTemplate.update(sql, namedParameters);
		} catch (DataIntegrityViolationException e) {
			return Constants.DB_DATAINTEGRITYVIOLATIONEXCEPTION_RETVALUE;
		}
	}

	@Override
	public UserTypeDto getUserTypeById(int id) {
		String sql = "Select * from user_types where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserTypeMapper());
	}

	@Override
	public UserTypeDto getUserTypeByUserType(String userType) {
		String sql = "Select * from USER_TYPES where user_type = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { userType }, new UserTypeMapper());
	}

	@Override
	public UserTypeDto getUserTypeByDescription(String desc) {
		String sql = "Select * from USER_TYPES where description = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { desc }, new UserTypeMapper());
	}

	@Override
	public List<UserTypeDto> getAll() {
		String sql = "Select * from user_types";
		return jdbcTemplate.query(sql, new UserTypeMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from user_types";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM user_types";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	@Override
	public LinkedHashMap<Integer, String> getUserTypesLookup() {
		return jdbcTemplate.query("select id, description from user_types order by description",
				new ResultSetExtractor<LinkedHashMap<Integer, String>>() {
					@Override
					public LinkedHashMap<Integer, String> extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
						while (rs.next()) {
							map.put(rs.getInt("id"), rs.getString("name"));

						}
						return map;
					}
				});
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class UserTypeMapper implements RowMapper<UserTypeDto> {
		// Use column names from table for rs.getXXX() methods.
		public UserTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserTypeDto userType = new UserTypeDto();
			userType.setId(rs.getInt("id"));
			userType.setDescription(rs.getString("description"));
			userType.setUserType(rs.getString("user_type"));
			return userType;
		}
	}

	private MapSqlParameterSource setNamedParameter(UserTypeDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("description", dto.getDescription());
		namedParameters.addValue("userType", dto.getUserType());
		return namedParameters;
	}

}
