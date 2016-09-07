/**
 * 
 */
package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.PlayListDAO;
import org.jeff.projs.ihbh.data.domains.PlayListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Jeff Sr
 *
 */
@Repository("playListDaoImpl")
public class MySqlPlayListDaoImpl implements PlayListDAO {

	private DataSource dataSource;
	static Logger log = Logger.getLogger(MySqlMeterDaoImpl.class);

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jeff.projs.ihbh.data.daos.PlayListDAO#add(org.jeff.projs.ihbh.data.
	 * domains.PlayListDto)
	 */
	@Override
	public int add(PlayListDto dto) {
		try {
			String sql = "Insert into play_list (name, description, default_play, volume, speed, repeat_play, user_id) "
					+ "values (:name, :description, :defaultPlay, :volume, :speed, :repeatPlay, :userId)";
			MapSqlParameterSource namedParameters = setNamedParameter(dto);
			return namedParameterJdbcTemplate.update(sql, namedParameters);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jeff.projs.ihbh.data.daos.PlayListDAO#update(org.jeff.projs.ihbh.data
	 * .domains.PlayListDto)
	 */
	@Override
	public int update(PlayListDto dto) {
		String sql = "update play_list SET name = :name, " 
				+ "description = :description, "
				+ "default_play = :defaultPlay, " 
				+ "volume = :volume, " 
				+ "speed = :speed, "
				+ "repeat_play = :repeatPlay, "
				+ "user_id = :userId "
				+ "where id = " + dto.getId();
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayListDAO#getAll()
	 */
	@Override
	public List<PlayListDto> getAll() {
		String sql = "Select * from play_list";
		return jdbcTemplate.query(sql, new PlayListMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayListDAO#getAllByPlayList(int)
	 */
	@Override
	public List<PlayListDto> getAllByUser(int userId) {
		try {
			String sql = "Select * from play_list where user_id = :userId";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("userId", userId);
			return namedParameterJdbcTemplate.query(sql, namedParameters, new PlayListMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getAllByUser returns null");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayListDAO#deleteAll()
	 */
	@Override
	public int deleteAll() {
		String sql = "delete FROM play_list";
		return jdbcTemplate.update(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayListDAO#deleteAllByPlayList(int)
	 */
	@Override
	public int deleteAllByUser(int userId) throws DataIntegrityViolationException {
		String sql = "delete FROM play_list where user_id = :userId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("userId", userId);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayListDAO#getCount()
	 */
	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM play_list";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jeff.projs.ihbh.data.daos.PlayLisnutDAO#getCountByPlayList(int)
	 */
	@Override
	public int getCountByUser(int userId) {
		String sql = "SELECT COUNT(*) FROM play_list where user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
	}

	@Override
	public PlayListDto getPlayListById(int id) {
		try {
			String sql = "Select * from play_list where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new PlayListMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getPlayListById returns null");
		}
		return null;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class PlayListMapper implements RowMapper<PlayListDto> {
		// Use column names from table for rs.getXXX() methods.
		public PlayListDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlayListDto playListDto = new PlayListDto();
			playListDto.setId(rs.getInt("id"));
			playListDto.setName(rs.getString("name"));
			playListDto.setDescription(rs.getString("description"));
			playListDto.setLastUsed(rs.getDate("last_used"));
			playListDto.setRepeat(rs.getBoolean("repeat_play"));
			playListDto.setSpeed(rs.getInt("speed"));
			playListDto.setVolume(rs.getInt("volume"));
			playListDto.setDef(rs.getBoolean("default_play"));
			playListDto.setUserId(rs.getInt("user_id"));
			return playListDto;
		}
	}

	private MapSqlParameterSource setNamedParameter(PlayListDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", dto.getName());
		namedParameters.addValue("description", dto.getDescription());
		namedParameters.addValue("repeatPlay", dto.isRepeat());
		namedParameters.addValue("speed", dto.getSpeed());
		namedParameters.addValue("volume", dto.getVolume());
		namedParameters.addValue("defaultPlay", dto.isDef());
		namedParameters.addValue("userId", dto.getUserId());
		return namedParameters;
	}
}
