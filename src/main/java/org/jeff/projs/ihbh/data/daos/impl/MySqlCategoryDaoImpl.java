package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jeff.projs.ihbh.data.daos.CategoryDAO;
import org.jeff.projs.ihbh.data.domains.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("categoryDaoImpl")
public class MySqlCategoryDaoImpl implements CategoryDAO {

	static Logger log = Logger.getLogger(MySqlCategoryDaoImpl.class);
	
	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int add(CategoryDto dto) {
		String sql = "Insert into category (category, par_id, list_order, level) "
				+ "values (:category, :parId, :listOrder, :level)";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	// Update the row where id = id with the dto.
	public int update(CategoryDto dto) {
		String sql = "update CATEGORY SET category = :category, "
				+ "par_id = :parId, list_order = :listOrder , level = :level" + " where id = " + dto.getId() + ";";
		MapSqlParameterSource namedParameters = setNamedParameter(dto);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int delete(int id) {
		String sql = "Delete from CATEGORY where id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		try {
			String sql = "Select * from CATEGORY where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id },
					new CategoryMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getHymnById returns null");
		}
		return null;
	}

	@Override
	public CategoryDto getCategoryByCategory(String category) {
		try {
			String sql = "Select * from CATEGORY where category = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { category },
					new CategoryMapper());
		} catch (EmptyResultDataAccessException e) {
			log.info("getHymnById returns null");
		}
		return null;
	}

	@Override
	public List<CategoryDto> getChildrenByParentId(int parId) {
		String sql = "Select * from CATEGORY where par_id = :parId order by list_order" ;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("parId", parId);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new CategoryMapper());
	}

	@Override
	public List<CategoryDto> getChildrenByParentName(String parentName) {
		String sql = "Select * from category where par_id IN "
				+ " (SELECT id FROM category WHERE category = :category)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("category", parentName);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new CategoryMapper());
	}

	@Override
	public List<CategoryDto> getAll() {
		String sql = "Select * from category";
		return jdbcTemplate.query(sql, new CategoryMapper());
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE from category";
		return jdbcTemplate.update(sql);
	}

	@Override
	public int getCount() {
		String sql = "SELECT COUNT(*) FROM CATEGORY";
		int num = jdbcTemplate.queryForObject(sql, Integer.class);
		return num;
	}

	private MapSqlParameterSource setNamedParameter(CategoryDto dto) {
		// First value parameter is from field name of Dto.
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("category", dto.getCategory());
		namedParameters.addValue("parId", dto.getParId());
		namedParameters.addValue("listOrder", dto.getListOrder());
		namedParameters.addValue("level", dto.getLevel());
		return namedParameters;
	}

	private static final class CategoryMapper implements RowMapper<CategoryDto> {
		// Use column names from table for rs.getXXX() methods.
		@Override
		public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			CategoryDto category = new CategoryDto();
			category.setParId(rs.getInt("par_id"));
			category.setCategory(rs.getString("category"));
			category.setId(rs.getInt("id"));
			category.setListOrder(rs.getInt("list_order"));
			category.setLevel(rs.getInt("level"));
			return category;
		}

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
}
