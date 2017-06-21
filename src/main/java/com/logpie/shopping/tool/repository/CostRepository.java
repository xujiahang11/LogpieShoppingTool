package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Cost;
import com.logpie.shopping.tool.model.Cost.CostType;

@Repository
public class CostRepository extends JDBCTemplateRepository<Cost> {

	public static final String DB_TABLE_COST = "Cost";

	public static final String DB_KEY_COST_ID = "id";
	public static final String DB_KEY_COST_NAME = "name";
	public static final String DB_KEY_COST_TYPE = "type";
	public static final String DB_KEY_COST_DESC = "desc";
	public static final String DB_KEY_COST_VALUE = "value";
	public static final String DB_KEY_COST_CREATION_TIME = "creationTime";
	public static final String DB_KEY_COST_SHOP_ID = "shopId";

	@Autowired
	private ShopRepository shopRepository;

	public CostRepository() {
		super(Cost.class);
	}

	public List<Cost> queryByPeriod() {

		return null;
	}

	@Override
	public Cost mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cost cost = new Cost();
		cost.setId(rs.getLong(DB_KEY_COST_ID));
		cost.setCreationTime(rs.getTimestamp(DB_KEY_COST_CREATION_TIME));
		cost.setName(rs.getString(DB_KEY_COST_NAME));
		cost.setType(CostType.fromCode(rs.getString(DB_KEY_COST_TYPE)));
		cost.setDesc(rs.getString(DB_KEY_COST_DESC));
		cost.setValue(rs.getFloat(DB_KEY_COST_VALUE));
		cost.setShop(shopRepository.mapRow(rs, rowNum));

		return cost;
	}
}
