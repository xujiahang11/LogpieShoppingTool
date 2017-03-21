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
import com.logpie.framework.db.util.LogpieModel;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;

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
	public Long insert(T model) {
		String sql = SQLUtil.insertSQL(model);
		if (sql == null) {
			logger.error("cannot get INSERT sql");
		}

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				return connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
			}
		};
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);
		return holder.getKey().longValue();
	}

	public Long updateByID(Class<T> c, String[] args) {
		String[] params = { DatabaseUtil.getID(c) };
		String sql = SQLUtil.updateSQL(c, args, params);
		if (sql == null) {
			logger.error("cannot get UPDATE sql");
		}

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				return connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
			}
		};
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);
		return holder.getKey().longValue();
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<T> queryAll(Class<T> c) {
		logger.trace("Database queryAll started...");
		String sql = SQLUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
		}
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * 
	 * @param args
	 * @return model
	 */
	public T queryByID(final Class<T> c, final Long arg) {
		logger.trace("Database queryById started...");
		String[] params = { DatabaseUtil.getID(c) };
		String sql = SQLUtil.querySQL(c)
				+ SQLUtil.whereConditionSQL(c, params, true);
		if (sql == "") {
			logger.error("cannot get QUERY sql");
		}
		return jdbcTemplate.queryForObject(sql, new Long[] { arg }, this);
	}

	/**
	 * 
	 * @param c
	 * @param foreignKey
	 * @param arg
	 * @return
	 */
	List<T> queryByForeignKey(final Class<T> c, final String foreignKey,
			final Long arg) {
		logger.trace("Database queryByForeignKey started...");
		String[] params = { foreignKey };
		String sql = SQLUtil.querySQL(c)
				+ SQLUtil.whereConditionSQL(c, params, true);
		if (sql == "") {
			logger.error("cannot get QUERY sql");
		}
		return jdbcTemplate.query(sql, new Long[] { arg }, this);
	}
}