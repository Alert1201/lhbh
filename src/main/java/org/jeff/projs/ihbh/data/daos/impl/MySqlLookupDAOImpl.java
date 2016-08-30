package org.jeff.projs.ihbh.data.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jeff.projs.ihbh.data.daos.LookupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("lookupDAOImpl")
public class MySqlLookupDAOImpl implements LookupDAO{

	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Map<Integer, String> getLookup(String sql) {
		return jdbcTemplate
				.query(sql, new ResultSetExtractor<LinkedHashMap<Integer, String>>() {
							@Override
							public LinkedHashMap<Integer, String> extractData(ResultSet rs)
									throws SQLException, DataAccessException {

								LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
								while (rs.next()) {
									map.put(rs.getInt("id"),
											rs.getString("name"));
								}
								return map;
							}
						});
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}
	
}
