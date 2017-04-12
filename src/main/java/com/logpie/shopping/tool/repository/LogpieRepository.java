package com.logpie.shopping.tool.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.SqlClause;
import com.logpie.framework.db.basic.SqlOperator;
import com.logpie.framework.db.util.SqlUtil;
import com.logpie.framework.db.util.TableUtil;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;

public abstract class LogpieRepository<T extends Model> implements RowMapper<T> {
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
	public Long insert(final T model) throws SQLException, DataAccessException {
		String sql = SqlUtil.insertSQL(model);
		if (sql == null) {
			logger.error("cannot get INSERT sql");
			return null;
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
		return holder.getKey() == null ? null : holder.getKey().longValue();
	}

	/**
	 * update a existing record of model
	 * 
	 * @param model
	 */
	public void update(final T model) throws DataAccessException {
		String sql = SqlUtil.updateSQL(model);
		if (sql == null) {
			logger.error("cannot get UPDATE sql");
			return;
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
	public void update(final Class<T> c, final List<SqlClause> updateArgs,
			final List<SqlClause> whereArgs) throws DataAccessException {
		String sql = SqlUtil.updateSQL(c, updateArgs, whereArgs);
		if (sql == null) {
			logger.error("cannot get UPDATE sql");
			return;
		}

		jdbcTemplate.execute(sql);
	}

	/**
	 * query a T record by id from table c
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T queryById(final Class<T> c, final Long id)
			throws DataAccessException {
		if (id == null) {
			logger.error("cannot get id");
			return null;
		}
		String sql = SqlUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
			return null;
		}

		List<SqlClause> conditionArgs = new ArrayList<SqlClause>();
		conditionArgs.add(SqlClause.createWhereClause(c, null, null,
				TableUtil.getId(c), null, SqlOperator.EQUAL));
		String whereSql = SqlUtil.whereSQL(c, conditionArgs);
		if (whereSql == null) {
			logger.error("cannot get WHERE CLAUSE sql");
			return null;
		}
		sql += whereSql;
		return jdbcTemplate.queryForObject(sql, new Long[] { id }, this);
	}

	public List<T> query(final String sql) throws DataAccessException {
		if (sql == null || sql.isEmpty()) {
			logger.error("cannot get SQL");
			return null;
		}
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * query all <T> records from table c
	 * 
	 * @param c
	 * @return result set
	 */
	public List<T> queryAll(final Class<T> c) throws DataAccessException {
		String sql = SqlUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
		}
		return jdbcTemplate.query(sql, this);
	}

	/**
	 * query all <T> records from table c in order
	 * 
	 * @param c
	 * @param orderBySql
	 *            sort in ascending or descending order
	 * @return result set
	 */
	public List<T> queryAll(final Class<T> c, final String orderBySql)
			throws DataAccessException {
		String sql = SqlUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
			return null;
		}
		if (orderBySql != null && !orderBySql.isEmpty()) {
			sql += orderBySql;
		}
		return jdbcTemplate.query(sql, this);
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
			final Long arg, final String orderBySql) throws DataAccessException {
		logger.trace("Database queryByForeignKey started...");
		if (foreignKey == null) {
			logger.error("cannot get foreign key");
			return null;
		}
		String sql = SqlUtil.querySQL(c);
		if (sql == null) {
			logger.error("cannot get QUERY sql");
			return null;
		}

		List<SqlClause> conditionArgs = new ArrayList<SqlClause>();
		conditionArgs.add(SqlClause.createWhereClause(c, null, null,
				foreignKey, null, SqlOperator.EQUAL));
		String whereSql = SqlUtil.whereSQL(c, conditionArgs);
		if (whereSql == null) {
			logger.error("cannot get WHERE CLAUSE sql");
			return null;
		}
		sql += whereSql;

		if (orderBySql != null && !orderBySql.isEmpty()) {
			sql += orderBySql;
		}
		logger.debug("sql: " + sql);
		return jdbcTemplate.query(sql, new Long[] { arg }, this);
	}
}