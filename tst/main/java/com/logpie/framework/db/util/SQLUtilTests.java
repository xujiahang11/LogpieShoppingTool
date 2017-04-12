// Copyright 2017 logpie.com. All rights reserved.
package com.logpie.framework.db.util;

import junit.framework.TestCase;

import org.junit.Test;

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
		mTestBrand = new Brand();
		mTestCategory = new Category();
		mTestSubcategory = new SubCategory();
	}

	@Test
	public void testInsertSQL() {

	}

	@Test
	public void testUpdateSQL() {

	}
}
