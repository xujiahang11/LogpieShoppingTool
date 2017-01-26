package com.logpie.shopping.tool.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.logpie.framework.db.util.DatabaseUtil;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.shopping.tool.model.LogpieModel;

public abstract class LogpieRepository<T extends LogpieModel> implements
		RowMapper<T> {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/**
	 * insert a record of the model into database
	 * 
	 * @param model
	 * @return id of this record in the database
	 */
	public void create(T model) {
		String sql = SQLUtil.insertSQL(model);
		System.out.println("SQL: " + sql);

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				return connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
			}
		};
		KeyHolder holder = new GeneratedKeyHolder();
		System.out.println(jdbcTemplate == null);
		jdbcTemplate.update(psc, holder);
		String key = holder.getKey().toString();
		System.out.println("Key: " + key);
	}

	public List<T> queryAll(Class<T> c) {
		String sql = SQLUtil.queryAllSQL(c);
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * 
	 * @param args
	 * @return model
	 */
	public T queryByPrimaryKey(Class<T> c, String[] args) {
		String sql = "select * from " + DatabaseUtil.getTableName(c)
				+ " where " + DatabaseUtil.getPrimaryKey(c) + " = ?";
		return jdbcTemplate.queryForObject(sql, args, this);
	}

}