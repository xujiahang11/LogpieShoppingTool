package com.logpie.shopping.tool.repository;

import java.util.List;

public interface LogpieRepository {

	public <T> T queryByPrimaryKey(String[] args);

	public List<?> queryAll();
}
