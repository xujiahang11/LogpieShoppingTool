package com.logpie.framework.db.repository;

import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;

import com.logpie.framework.db.basic.Model;
import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;

public interface PagingAndSortingRepository<T extends Model, ID extends Serializable> {

	public Page<T> queryAll(Pageable pageable);

	public Page<T> queryBy(Pageable pageable, Parameter... params);

}
