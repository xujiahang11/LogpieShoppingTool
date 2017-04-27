package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.model.Size;

@Repository
public class SizeRepository extends JDBCTemplateRepository<Size> {

	public static final String DB_TABLE_SIZE = "Size";

	public static final String DB_KEY_SIZE_ID = "SizeId";
	public static final String DB_KEY_SIZE_NAME = "SizeName";
	public static final String DB_KEY_SIZE_SHOP_ID = "SizeShopId";

	@Autowired
	private ShopRepository shopRepository;

	public SizeRepository() {
		super(Size.class);
	}

	public List<Size> queryByShopId(final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Size.class, DB_KEY_SIZE_SHOP_ID,
				shopId);
		return (List<Size>) super.queryBy(param);
	}

	@Override
	public Size mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}

		Long id = rs.getLong(DB_KEY_SIZE_ID);
		String name = rs.getString(DB_KEY_SIZE_NAME);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new Size(id, name, shop);
	}

}
