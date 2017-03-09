package com.logpie.shopping.tool.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.logpie.framework.db.util.DatabaseUtil;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.LogpieModel;

public abstract class LogpieRepository<T extends LogpieModel> implements
		RowMapper<T> {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(LogpieRepository.class);

	/**
	 * insert a record of the model into database
	 * 
	 * @param model
	 * @return id of this record in the database
	 */
	public void create(T model) {
		String sql = SQLUtil.insertSQL(model);

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

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<T> queryAll(Class<T> c) {
		logger.trace("Database query started...");
		String sql = SQLUtil.querySQL(c);
		logger.debug("'QueryAll' SQL: " + sql);
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * 
	 * @param args
	 * @return model
	 */
	public T queryByPrimaryKey(Class<T> c, String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(DatabaseUtil.getTableName(c),
				DatabaseUtil.getPrimaryKeyName(c));
		String sql = SQLUtil.querySQLByKey(c, params);
		return jdbcTemplate.queryForObject(sql, args, this);
	}

}