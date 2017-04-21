package com.logpie.framework.db.repository;

import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;

import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.Parameter;

public interface SimpleRepository<T extends Model, ID extends Serializable>
		extends RowMapper<T> {

	public ID insert(T model);

	public void update(T model);

	public T queryOne(ID primaryKey);

	public Iterable<T> queryAll();

	public Iterable<T> queryBy(Parameter... conditions);

	public boolean exists(ID primaryKey);

	public int count();

	public void delete(T model);
}
