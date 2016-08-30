package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.HymnDAO;
import org.jeff.projs.ihbh.data.domains.HymnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("hymnDaoImpl")
public class MySqlHymnDaoImpl implements HymnDAO {

	static Logger log = Logger.getLogger(MySqlHymnDaoImpl.class);

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(HymnDto dto) {
		String sql = "Insert into HYMNS (hymnal_id, tune_id, title, "
				+ "first_line, author_id, composed_yr, number) "
				+ "values (:hymnalId, :tuneId, "
				+ ":title, :firstLine, :authorId, :composed_yr, :number)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int update(HymnDto dto, int id) {
		String sql = "update HYMNS SET hymnal_id = :hymnalId, " + 
					 " tune_Id = :tuneId, " +
					 " title = :title, " +
					 " first_line = :firstLine, " +
					 " author_id = :authorId, " +
					 " composed_yr = :composed_yr,  " +
					 " number = :number  " +
					 " where id = " + id;
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		String sql = "Delete from HYMNS where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public HymnDto getHymnById(int id) {
		try {
			String sql = "Select * from HYMNS where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id },
					new HymnMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getHymnById returns null");
		}
		return null;
	}

	@Override
	public List<HymnDto> getHymnsByAuthor(int authorId) {
		String sql = "Select * from HYMNS where author_id = :authorId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("authorId", authorId);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new HymnMapper());
	}

	@Override
	public List<HymnDto> getHymnsByCategory(int categoryId) {
/*		String sql = "Select * from HYMNS where author_id = :authorId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("categoryId", categoryId);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new HymnMapper());*/
		return null;
	}

	
	@Override
	public List<HymnDto> getHymnsByTitle(String title) {
		String sql = "Select * from HYMNS where upper(title) LIKE upper(:title)";
		String titleParam= "%" + title.toLowerCase().trim() + "%";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("title", titleParam);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new HymnMapper());
	}

	@Override
	public HymnDto getHymnByHymnalNumber(int hymnalId, int hymnNumber) {
		try {
			String sql = "Select * from HYMNS where hymnal_id = ? and number = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { hymnalId, hymnNumber },
					new HymnMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getHymnById returns null");
		}
		return null;
	}

	@Override
	public List<HymnDto> getHymnsByTune(int tuneId) {
		String sql = "Select * from HYMNS where tune_id = :tuneId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("tuneId", tuneId);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new HymnMapper());
	}

	@Override
	public List<HymnDto> getHymnsByFirstLine(String firstLine) {
		String sql = "Select * from HYMNS where upper(first_line) LIKE upper(:firstLine)";
		String firstLineParam= "%" + firstLine.toLowerCase().trim() + "%";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstLine", firstLineParam);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new HymnMapper());
	}

	@Override
	public List<HymnDto> getAll() {
		String sql = "Select * from HYMNS";
		return jdbcTemplate.query(sql, new HymnMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from HYMNS";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM HYMNS";
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

	private MapSqlParameterSource setNamedParameter(HymnDto dto) {
		// First value parameter is from field name of Dto. Does not have ID
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("hymnalId", dto.getHymnalId());
		namedParameters.addValue("tuneId", dto.getTuneId());
		namedParameters.addValue("title", dto.getTitle());
		namedParameters.addValue("firstLine", dto.getFirstLine());
		namedParameters.addValue("authorId", dto.getAuthorId());
		namedParameters.addValue("composed_yr", dto.getComposedYear());
		namedParameters.addValue("number", dto.getNumber());
		return namedParameters;
	}

	private static final class HymnMapper implements RowMapper<HymnDto> {
		// Use column names from table for rs.getXXX() methods.
		public HymnDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			HymnDto hymn = new HymnDto();
			hymn.setId(rs.getInt("id"));
			hymn.setAuthorId(rs.getInt("author_id"));
			hymn.setComposedYear(rs.getString("composed_yr"));
			;
			hymn.setFirstLine(rs.getString("first_line"));
			hymn.setHymnalId(rs.getInt("hymnal_id"));
			hymn.setNumber(rs.getInt("number"));
			hymn.setTitle(rs.getString("title"));
			hymn.setTuneId(rs.getInt("tune_id"));
			return hymn;
		}
	}

}
