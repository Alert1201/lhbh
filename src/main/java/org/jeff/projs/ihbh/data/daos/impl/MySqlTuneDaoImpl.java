package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.TunesDAO;
import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.jeff.projs.ihbh.data.domains.TuneDto;
import org.jeff.projs.ihbh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("tuneDaoImpl")
public class MySqlTuneDaoImpl implements TunesDAO {

	static Logger log = Logger.getLogger(MySqlTuneDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(TuneDto dto) {
		String sql = "Insert into tunes (name, meter_id, composition_year, author_id, nationality) "
				+ "values (:name, :meter_id, :composition_year, :author_id, :nationality)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(TuneDto dto) {
		String sql = "update tunes SET name = :name, " + "meter_id = :meter_id, "
				+ "composition_year = :composition_year, " + "author_id = :author_id, " + "nationality = :nationality "
				+ "where id = " + dto.getId();
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	private MapSqlParameterSource setNamedParameter(TuneDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", dto.getName());
		namedParameters.addValue("meter_id", dto.getMeter().getId());
		namedParameters.addValue("composition_year", dto.getCompositionYear());
		namedParameters.addValue("author_id", dto.getAuthor().getId());
		namedParameters.addValue("nationality", dto.getNationality());
		return namedParameters;
	}

	@Override
	public int delete(int id) {
		try {
			String sql = "DELETE from TUNES where id = :id";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id", id);
			return namedParameterJdbcTemplate.update(sql, namedParameters);
		} catch (DataIntegrityViolationException e) {
			return Constants.DB_DATAINTEGRITYVIOLATIONEXCEPTION_RETVALUE;
		}
	}

	@Override
	public TuneDto getTuneById(int id) {
		try {
			String sql = "Select * from tunes where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new TunesMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;
	}

	@Override
	public TuneDto getTuneByName(String name) {
		try {
			String sql = "Select * from tunes where name = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { name }, new TunesMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getUserByFullName returns null");
		}
		return null;

	}

	@Override
	public List<TuneDto> getTunesByMeter(int meterId) {
		String sql = "Select * from tunes where meter_id = :meter_id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("meter_id", meterId);
		return namedParameterJdbcTemplate.query(sql, namedParameters, new TunesMapper());
	}

	@Override
	public List<TuneDto> getTunesByAuthor(int authorid) {
		String sql = "Select * from tunes where author_id = :author_id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("author_id", authorid);
		return namedParameterJdbcTemplate.query(sql, namedParameters, new TunesMapper());
	}

	@Override
	public LinkedHashMap<Integer, String> getTunesLookup() {
		return jdbcTemplate.query("select id, name from tunes order by name",
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

	@Override
	public List<TuneDto> getAll() {
		String sql = "Select * from tunes";
		return jdbcTemplate.query(sql, new TunesMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from tunes";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM TUNES";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	@Override
	public List<TuneDto> getWholeTunes() {
		String sql = "select a.name, a.id, a.nationality, " + "a.composition_year, b.meter, "
				+ "b.description, b.id as meter_id, c.id as author_id, " + "c.first_name, c.last_name, "
				+ "c.DOB_month, c.DOB_year, c.DOD_month, "
				+ "c.DOD_year, c.nationality as author_nationality, c.DOD_circa, "
				+ "c.DOB_circa, c.bio from tunes a, meter b, authors c " + "where a.meter_id = b.id and a"
				+ ".author_id =c.id";
		return jdbcTemplate.query(sql, new TunesWholeMapper());
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class TunesMapper implements RowMapper<TuneDto> {
		// Use column names from table for rs.getXXX() methods.
		@Override
		public TuneDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			TuneDto tune = new TuneDto();
			tune.setAuthor(new AuthorDto(rs.getInt("author_id")));
			tune.setCompositionYear(rs.getString("composition_year"));
			tune.setId(rs.getInt("id"));
			tune.setMeter(new MeterDto(rs.getInt("meter_id")));
			tune.setName(rs.getString("name"));
			tune.setNationality(rs.getString("nationality"));
			return tune;
		}
	}

	private static final class TunesWholeMapper implements RowMapper<TuneDto> {
		// Use column names from table for rs.getXXX() methods.
		@Override
		public TuneDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			TuneDto tune = new TuneDto();
			tune.setAuthor(new AuthorDto(rs));
			tune.setCompositionYear(rs.getString("composition_year"));
			tune.setId(rs.getInt("id"));
			tune.setMeter(new MeterDto(rs));
			tune.setName(rs.getString("name"));
			tune.setNationality(rs.getString("nationality"));
			return tune;
		}
	}

	private static final class MeterMapper implements RowMapper<MeterDto> {

		@Override
		public MeterDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

	}
}