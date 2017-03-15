// Copyright 2017 logpie.com. All rights reserved.
package com.logpie.framework.db.util;

import org.junit.Test;

import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Client;

import junit.framework.TestCase;

public class SQLUtilTests extends TestCase
{
    private Brand mTestBrand;
    private Client mTestClient;
    private Address mTestAddress;

    @Override
    public void setUp()
    {
        mTestBrand = new Brand("test_brand");
        mTestClient = new Client(1L, "wechat_directed_id", "test_client_name", "test_phone",
                "test_wechat_id", "test_wechat_name", "test_taobao_name", "test_client_name", null);
        mTestAddress = new Address("test_address", "test_address_recipent", "test_recipent_phone",
                mTestClient);
    }

    @Test
    public void testInsertSQL()
    {
        String insertBrandSQL = SQLUtil.insertSQL(mTestBrand);
        String insertAddressSQL = SQLUtil.insertSQL(mTestAddress);
        assertEquals("insert into Brand(BrandName) values ('test_brand')", insertBrandSQL);
        assertEquals(
                "insert into Address(AddressRecipientName, AddressClientId, Address, AddressRecipientPhone) values ('test_address_recipent', '1', 'test_address', 'test_recipent_phone')",
                insertAddressSQL);
    }
}
