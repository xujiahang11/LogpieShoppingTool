// Copyright 2017 logpie.com. All rights reserved.
package com.logpie.framework.db.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.logpie.framework.db.basic.SQLClause;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.SubCategory;

public class SQLUtilTests extends TestCase {
	private Address mTestAddress;
	private Brand mTestBrand;
	private Category mTestCategory;
	private Client mTestClient;
	private Delivery mTestIntDelivery;
	private Delivery mTestDomDelivery;
	private Package mTestPackage;
	private SubCategory mTestSubcategory;

	@Override
	public void setUp() {
		mTestBrand = new Brand("test_brand");
		mTestCategory = new Category(1L, "test_category");
		mTestSubcategory = new SubCategory("test_subcategory", mTestCategory);
		mTestClient = new Client(1L, "wechat_directed_id", "test_client_name",
				"test_phone", "test_wechat_id", "test_wechat_name",
				"test_taobao_name", "test_client_name", null);
		mTestIntDelivery = new Delivery(1L, "test_int_delivery", true);
		mTestDomDelivery = new Delivery(2L, "test_dom_delivery", false);
		mTestAddress = new Address("test_address", "test_address_recipent",
				"test_recipent_phone", mTestClient);
		mTestPackage = new Package(1L, mTestIntDelivery, null,
				mTestDomDelivery, null, mTestClient, "test_recipient",
				"test_destination", false, null, 500, null, null, null, null,
				null);
	}

	@Test
	public void testInsertSQL() {
		String insertBrandSQL = SQLUtil.insertSQL(mTestBrand);
		String insertCategorySQL = SQLUtil.insertSQL(mTestCategory);
		String insertSubcategorySQL = SQLUtil.insertSQL(mTestSubcategory);
		String insertClientSQL = SQLUtil.insertSQL(mTestClient);
		String insertAddressSQL = SQLUtil.insertSQL(mTestAddress);

		assertEquals("insert into Brand(BrandName) values ('test_brand')",
				insertBrandSQL);
		assertEquals(
				"insert into Category(CategoryName) values ('test_category')",
				insertCategorySQL);
		assertEquals(
				"insert into SubCategory(SubCategoryName, CategoryId) values ('test_subcategory', 1)",
				insertSubcategorySQL);
		assertEquals(
				"insert into Client(ClientWechatName, ClientWechatId, ClientName, ClientTaobaoName, ClientPhone, ClientNote, ClientWechatDirectedId) values ('test_wechat_name', 'test_wechat_id', 'test_client_name', 'test_taobao_name', 'test_phone', 'test_client_name', 'wechat_directed_id')",
				insertClientSQL);
		assertEquals(
				"insert into Address(AddressRecipientName, AddressClientId, Address, AddressRecipientPhone) values ('test_address_recipent', 1, 'test_address', 'test_recipent_phone')",
				insertAddressSQL);
	}

	@Test
	public void testUpdateSQL() {
		List<SQLClause> updateArgs = new ArrayList<SQLClause>();
		updateArgs.add(SQLClause.createUpdateClause("BrandName",
				"update_brand_name"));
		List<SQLClause> conditionArgs = new ArrayList<SQLClause>();
		conditionArgs.add(SQLClause.createWhereClause("BrandId", 1L));

		String updateBrandSQL = SQLUtil.updateSQL(Brand.class, updateArgs,
				conditionArgs);

		assertEquals(
				"update Brand set BrandName = 'update_brand_name' where Brand.BrandId = 1",
				updateBrandSQL);
	}
}
