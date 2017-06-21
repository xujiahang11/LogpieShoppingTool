package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.ShippingRecord;

@Repository
public class ShippingRecordRepository extends
		JDBCTemplateRepository<ShippingRecord> implements
		ResultSetExtractor<List<ShippingRecord>> {

	public static final String DB_TABLE_SHIPPING_RECORD = "ShippingRecord";

	public static final String DB_KEY_SHIPPING_RECORD_ID = "id";
	public static final String DB_KEY_SHIPPING_RECORD_PACKAGE_ID = "packageId";
	public static final String DB_KEY_SHIPPING_RECORD_TRANSACTION_ID = "transactionId";

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private PackageRepository packageRepository;

	public ShippingRecordRepository() {
		super(ShippingRecord.class);
	}

	public List<ShippingRecord> queryByPackageId(final Long packageId)
			throws DataAccessException {
		Parameter param = new WhereParam(ShippingRecord.class,
				DB_KEY_SHIPPING_RECORD_PACKAGE_ID, packageId);

		return (List<ShippingRecord>) super.queryBy(param);
	}

	public List<ShippingRecord> queryByTransactionId(final Long tranId)
			throws DataAccessException {
		Parameter param = new WhereParam(ShippingRecord.class,
				DB_KEY_SHIPPING_RECORD_TRANSACTION_ID, tranId);

		return (List<ShippingRecord>) super.queryBy(param);
	}

	@Override
	public ShippingRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShippingRecord record = new ShippingRecord();
		record.setId(rs.getLong(DB_KEY_SHIPPING_RECORD_ID));
		record.setPack(packageRepository.mapRow(rs, rowNum));
		record.setTransaction(transactionRepository.mapRow(rs, rowNum));
		return record;
	}

	@Override
	public List<ShippingRecord> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<ShippingRecord> recordList = new ArrayList<ShippingRecord>();
		while (rs.next()) {
			ShippingRecord record = new ShippingRecord();
			record.setId(rs.getLong(DB_KEY_SHIPPING_RECORD_ID));
			record.setPack(packageRepository.mapRow(rs, rs.getRow()));
			record.setTransaction(transactionRepository.mapRow(rs, rs.getRow()));
			recordList.add(record);
		}
		return recordList;
	}

}
