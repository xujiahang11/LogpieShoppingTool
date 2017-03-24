package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Color;

@Repository
public class ColorRepository extends LogpieRepository<Color> {

	public static final String DB_TABLE_COLOR = "Color";
	public static final String DB_KEY_COLOR_ID = "ColorId";
	public static final String DB_KEY_COLOR_NAME = "ColorName";

	@Override
	public Color mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long colorId = rs.getLong(DB_KEY_COLOR_ID);
		String colorName = rs.getString(DB_KEY_COLOR_NAME);
		return new Color(colorId, colorName);
	}

}
