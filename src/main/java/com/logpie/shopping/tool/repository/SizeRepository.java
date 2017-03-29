package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Size;

@Repository
public class SizeRepository extends LogpieRepository<Size> {
	public static final String DB_TABLE_SIZE = "Size";
	public static final String DB_KEY_SIZE_ID = "SizeId";
	public static final String DB_KEY_SIZE_NAME = "SizeName";

	@Override
	public Size mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long sizeId = rs.getLong(DB_KEY_SIZE_ID);
		String sizeName = rs.getString(DB_KEY_SIZE_NAME);
		return new Size(sizeId, sizeName);
	}

}
