package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.SaveStateDAO;
import org.jeff.projs.ihbh.data.domains.SaveStateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("saveStateDaoImpl")
public class MySqlSaveStateDaoImpl implements SaveStateDAO {

	static Logger log = Logger.getLogger(MySqlSaveStateDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(SaveStateDto dto) {
		String sql = "Insert into SAVE_STATE (user_id, current_hymn_id, hymn_volume, hymn_tempo) "
				+ "values (:userId, :currentHymnId, :hymnVolume, :hymnTempo)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(SaveStateDto dto, int id) {
		String sql = "update SAVE_STATE SET  "
				+ "user_id = :userId, " + "current_hymn_id = :currentHymnId, "
				+ "hymn_volume = :hymnVolume, " + "hymn_tempo = :hymnTempo "
				+ "where id = " + id + ";";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE from SAVE_STATE where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public SaveStateDto getSaveStateById(int id) {
		String sql = "Select * from SAVE_STATE where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				new SaveStateMapper());
	}

	@Override
	public SaveStateDto getSaveStateByUserId(int userId) {
		String sql = "Select * from SAVE_STATE where user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userId },
				new SaveStateMapper());
	}

	@Override
	public List<SaveStateDto> getAll() {
		String sql = "Select * from SAVE_STATE";
		return jdbcTemplate.query(sql, new SaveStateMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from SAVE_STATE";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM SAVE_STATE";
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

	private static final class SaveStateMapper implements
			RowMapper<SaveStateDto> {
		// Use column names from table for rs.getXXX() methods.
		public SaveStateDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			SaveStateDto saveStateDto = new SaveStateDto();
			saveStateDto.setUserId(rs.getInt("user_id"));
			saveStateDto.setId(rs.getInt("id"));
			saveStateDto.setCurrentHymnId(rs.getInt("current_hymn_id"));
			saveStateDto.setHymnVolume(rs.getInt("hymn_volume"));
			saveStateDto.setHymnTempo(rs.getInt("hymn_tempo"));
			return saveStateDto;
		}
	}

	private MapSqlParameterSource setNamedParameter(SaveStateDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("userId", dto.getUserId());
		namedParameters.addValue("currentHymnId", dto.getCurrentHymnId());
		namedParameters.addValue("hymnVolume", dto.getHymnVolume());
		namedParameters.addValue("hymnTempo", dto.getHymnTempo());
		return namedParameters;
	}
}
