package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Color;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class ColorRepository extends LogpieRepository<Color> {

	public static final String DB_TABLE_COLOR = "Color";

	public static final String DB_KEY_COLOR_ID = "ColorId";
	public static final String DB_KEY_COLOR_NAME = "ColorName";
	public static final String DB_KEY_COLOR_SHOP_ID = "ColorShopId";

	@Autowired
	private ShopRepository shopRepository;

	public List<Color> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Color.class, DB_KEY_COLOR_SHOP_ID,
				shopId, null);
	}

	@Override
	public Color mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long id = rs.getLong(DB_KEY_COLOR_ID);
		String name = rs.getString(DB_KEY_COLOR_NAME);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new Color(id, name, shop);
	}

}
