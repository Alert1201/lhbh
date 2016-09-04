package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.MeterDAO;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("meterDaoImpl")
public class MySqlMeterDaoImpl implements MeterDAO {

	private DataSource dataSource;
	static Logger log = Logger.getLogger(MySqlMeterDaoImpl.class);

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(MeterDto dto) {
		String sql = "Insert into meter (meter, description) " + "values (:meter, :description)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(MeterDto dto) {
		String sql = "update meter SET meter = :meter " + ", description = :description" + " where id = " + dto.getId();
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) throws DataIntegrityViolationException {
			String sql = "DELETE from meter where id = :id";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id", id);
			return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public MeterDto getMeterById(int id) {
		try {
			String sql = "Select * from meter where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new MeterMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public MeterDto getMeterByMeter(String meter) {
		try {
			String sql = "Select * from meter where meter = ? ";
			return jdbcTemplate.queryForObject(sql, new Object[] { meter }, new MeterMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public List<MeterDto> getAll() {
		String sql = "Select * from meter";
		return jdbcTemplate.query(sql, new MeterMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from meter";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM meter";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class MeterMapper implements RowMapper<MeterDto> {
		// Use column names from table for rs.getXXX() methods.
		public MeterDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			MeterDto meter = new MeterDto();
			meter.setId(rs.getInt("id"));
			meter.setMeter(rs.getString("meter"));
			meter.setDescription(rs.getString("description"));
			return meter;
		}
	}

	private MapSqlParameterSource setNamedParameter(MeterDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("meter", dto.getMeter());
		namedParameters.addValue("description", dto.getDescription());
		return namedParameters;
	}
}
