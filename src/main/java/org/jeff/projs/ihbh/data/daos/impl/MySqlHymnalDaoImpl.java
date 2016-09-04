package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.HymnalDAO;
import org.jeff.projs.ihbh.data.domains.HymnalDto;
import org.jeff.projs.ihbh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("hymnalDaoImpl")
public class MySqlHymnalDaoImpl implements HymnalDAO {

	static Logger log = Logger.getLogger(MySqlHymnalDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(HymnalDto dto) {
		String sql = "Insert into hymnals (name, abbreviation) " + "values (:name, :abbreviation)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(HymnalDto dto) {
		String sql = "update hymnals SET name = :name, " + "abbreviation = :abbreviation " + " where id = "
				+ dto.getId() + ";";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		try {
			String sql = "Delete from hymnals where id = :id";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id", id);
			return namedParameterJdbcTemplate.update(sql, namedParameters);
		} catch (DataIntegrityViolationException e) {
			return Constants.DB_DATAINTEGRITYVIOLATIONEXCEPTION_RETVALUE;
		} 

	}

	@Override
	public HymnalDto getHymnalById(int id) {
		try {
			String sql = "Select * from hymnals where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new HymnalMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	public HymnalDto getHymnalByName(String name) {
		try {
			String sql = "Select * from hymnals where name = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { name }, new HymnalMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	public HymnalDto getHymnalByAbbreviation(String abbreviation) {
		try {
			String sql = "Select * from hymnals where abbreviation = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { abbreviation }, new HymnalMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public List<HymnalDto> getAll() {
		try {
			String sql = "Select * from hymnals";
			return jdbcTemplate.query(sql, new HymnalMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from hymnals";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM Hymnals";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	private MapSqlParameterSource setNamedParameter(HymnalDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("abbreviation", dto.getAbbreviation());
		namedParameters.addValue("name", dto.getName());
		return namedParameters;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class HymnalMapper implements RowMapper<HymnalDto> {
		// Use column names from table for rs.getXXX() methods.
		@Override
		public HymnalDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			HymnalDto hymnal = new HymnalDto();
			hymnal.setName(rs.getString("name"));
			hymnal.setAbbreviation(rs.getString("abbreviation"));
			hymnal.setId(rs.getInt("id"));
			return hymnal;
		}

	}

}
