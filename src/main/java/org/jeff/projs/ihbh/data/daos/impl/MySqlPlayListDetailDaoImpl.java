package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.PlayListDetailDao;
import org.jeff.projs.ihbh.data.domains.PlayListDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("playListDetailImpl")
public class MySqlPlayListDetailDaoImpl implements PlayListDetailDao {

	static Logger log = Logger.getLogger(MySqlPlayListDetailDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int add(PlayListDetailDto dto) {
		// For values use the dto property names, not the sql column names.
		String sql = "Insert into play_list_detail (play_list_id, hymn_id, part, type) "
				+ " values (:playListId, :hymnId, :part, :type) ";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from play_list_detail";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int deleteByPlayList(int playListId) {
		String sql = "DELETE from play_list_detail where play_list_id = :playListId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("playListId", playListId);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int deleteSingleEntry(int id) {
		String sql = "DELETE from play_list_detail where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public List<PlayListDetailDto> getAll() {
		String sql = "Select * from play_list_detail";
		return jdbcTemplate.query(sql, new PlayListDetailMapper());
	}

	@Override
	public List<PlayListDetailDto> getByPlayList(int playListId) {
		try {
			String sql = "Select * from play_list_detail where play_list_id = :playListId";
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("playListId", playListId);
			return namedParameterJdbcTemplate.query(sql, namedParameters, new PlayListDetailMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getAllByUser returns null");
		}
		return null;
	}

	@Override
	public PlayListDetailDto getSingleEntry(int id) {
		try {
			String sql = "Select * from play_list_detail where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new PlayListDetailMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getSingleEntry returns null");
		}
		return null;
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM play_list_detail";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	@Override
	public int getCountByPlayList(int playListId) {
		String sql = "SELECT COUNT(*) FROM play_list_detail where play_list_id = ?";
		int num = jdbcTemplate.queryForObject(sql, new Object[] { playListId }, Integer.class);
		return num;
	}

	@Override
	public int update(PlayListDetailDto dto) {
		String sql = "update play_list_detail SET play_list_id = :playListId," + "hymn_id = :hymnId, "
				+ "part = :part, " + "type = :type " + "where id = " + dto.getId();
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final class PlayListDetailMapper implements RowMapper<PlayListDetailDto> {
		// Use column names from table for rs.getXXX() methods.
		public PlayListDetailDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlayListDetailDto playListDetail = new PlayListDetailDto();
			playListDetail.setId(rs.getInt("id"));
			playListDetail.setPlayListId(rs.getInt("play_list_id"));
			playListDetail.setHymnId(rs.getInt("hymn_id"));
			playListDetail.setPart(rs.getInt("part"));
			playListDetail.setType(rs.getInt("type"));
			return playListDetail;
		}
	}

	private MapSqlParameterSource setNamedParameter(PlayListDetailDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("playListId", dto.getPlayListId());
		namedParameters.addValue("hymnId", dto.getHymnId());
		namedParameters.addValue("part", dto.getPart());
		namedParameters.addValue("type", dto.getType());
		return namedParameters;
	}

}
