package com.logpie.shopping.tool.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.framework.db.basic.SQLClause;
import com.logpie.framework.db.util.DatabaseUtil;
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
	 * insert a record of model into database
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

	/**
	 * update a existing record of model
	 * 
	 * @param model
	 */
	public void update(T model) {
		String sql = SQLUtil.updateSQL(model);
		if (sql == null) {
			logger.error("cannot get UPDATE sql");
		}

		jdbcTemplate.execute(sql);
	}

	/**
	 * update existing records by using arguments
	 * 
	 * @param c
	 * @param updateArgs
	 * @param whereArgs
	 *            where clause arguments; can be null
	 */
	public void update(Class<T> c, List<SQLClause> updateArgs,
			List<SQLClause> whereArgs) {
		String sql = SQLUtil.updateSQL(c, updateArgs, whereArgs);
		if (sql == null) {
			logger.error("cannot get UPDATE sql");
		}

		jdbcTemplate.execute(sql);
	}

	/**
	 * query records from table c
	 * 
	 * @param c
	 * @param orderBySql
	 *            sort in ascending or descending order; can be null
	 * @return result set
	 */
	public List<T> query(Class<T> c, String orderBySql) {
		logger.trace("Database queryAll started...");
		String sql = SQLUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
		}
		if (orderBySql == null) {
			return jdbcTemplate.query(sql, this);
		}
		sql += orderBySql;
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * query a record by id from table c
	 * 
	 * @param c
	 * @param arg
	 * @return model where id is arg
	 */
	public T queryByID(final Class<T> c, final Long arg) {
		logger.trace("Database queryById started...");
		if (arg == null) {
			logger.error("cannot get id");
			return null;
		}
		String sql = SQLUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
			return null;
		}

		List<SQLClause> conditionArgs = new ArrayList<SQLClause>();
		conditionArgs.add(SQLClause.createWhereClause(DatabaseUtil.getID(c),
				null));
		String whereSql = SQLUtil.whereSQL(c, conditionArgs);
		if (whereSql == null) {
			logger.error("cannot get WHERE CLAUSE sql");
			return null;
		}
		sql += whereSql;
		return jdbcTemplate.queryForObject(sql, new Long[] { arg }, this);
	}

	/**
	 * query a record by foreign key from table c
	 * 
	 * @param c
	 * @param foreignKey
	 * @param arg
	 * @return result set
	 */
	List<T> queryByForeignKey(final Class<T> c, final String foreignKey,
			final Long arg) {
		logger.trace("Database queryByForeignKey started...");
		if (foreignKey == null) {
			logger.error("cannot get foreign key");
			return null;
		}
		String sql = SQLUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
			return null;
		}

		List<SQLClause> conditionArgs = new ArrayList<SQLClause>();
		conditionArgs.add(SQLClause.createWhereClause(foreignKey, null));
		String whereSql = SQLUtil.whereSQL(c, conditionArgs);
		if (whereSql == null) {
			logger.error("cannot get WHERE CLAUSE sql");
			return null;
		}
		sql += whereSql;
		return jdbcTemplate.query(sql, new Long[] { arg }, this);
	}
}