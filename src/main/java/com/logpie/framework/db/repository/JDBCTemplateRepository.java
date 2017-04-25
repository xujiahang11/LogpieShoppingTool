package com.logpie.framework.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.SimplePage;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.support.SqlUtil;
import com.logpie.framework.db.support.TableUtil;

public abstract class JDBCTemplateRepository<T extends Model> implements
		SimpleRepository<T, Long>, PagingAndSortingRepository<T, Long> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Class<T> c;

	/**
	 * initiate repository
	 * 
	 * @param c
	 */
	public JDBCTemplateRepository(Class<T> c) {
		this.c = c;
	}

	@Override
	public Long insert(T model) {
		String sql = SqlUtil.insertSQL(model);
		if (sql == null) {
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
		return holder.getKey() == null ? null : new Long(holder.getKey()
				.longValue());
	}

	@Override
	public void update(T model) {
		String sql = SqlUtil.updateSQL(model);
		if (sql == null) {
			return;
		}
		jdbcTemplate.execute(sql);
	}

	@Override
	public T queryOne(Long primaryKey) {
		String sql = SqlUtil.queryAllSQL(c);
		if (sql == null) {
			return null;
		}
		Parameter cond = new WhereParam(c, TableUtil.getId(c), primaryKey);
		sql += SqlUtil.whereSQL(c, cond);
		return jdbcTemplate.queryForObject(sql, this);
	}

	@Override
	public Iterable<T> queryAll() {
		String sql = SqlUtil.queryAllSQL(c);
		if (sql == null) {
			return null;
		}
		return jdbcTemplate.query(sql, this);
	}

	@Override
	public Page<T> queryAll(Pageable pageable) {
		Assert.notNull(pageable, "Paging information must not be null");

		String sql = SqlUtil.queryBySQL(c, pageable);
		List<T> contents = jdbcTemplate.query(sql, this);

		return new SimplePage<>(pageable, contents, count());
	}

	@Override
	public Iterable<T> queryBy(Parameter... params) {
		String sql = SqlUtil.queryBySQL(c, params);
		if (sql == null) {
			return null;
		}
		return jdbcTemplate.query(sql, this);
	}

	@Override
	public Page<T> queryBy(Pageable pageable, Parameter... params) {
		Assert.notNull(pageable, "Paging information must not be null");
		Assert.notNull(params, "Parameter must not be null");

		String sql = SqlUtil.queryBySQL(c, pageable, params);
		System.out.println("SQL --- " + sql);
		List<T> contents = jdbcTemplate.query(sql, this);

		return new SimplePage<>(pageable, contents, count());
	}

	@Override
	public boolean exists(Long primaryKey) {
		return queryOne(primaryKey) == null ? true : false;
	}

	@Override
	public int count() {
		String sql = SqlUtil.countSQL(c, null);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public void delete(T model) {
		// TODO Auto-generated method stub

	}

	public Iterable<T> query(String sql) {
		if (sql == null || sql.isEmpty()) {
			return null;
		}
		return jdbcTemplate.query(sql, this);
	}
}
