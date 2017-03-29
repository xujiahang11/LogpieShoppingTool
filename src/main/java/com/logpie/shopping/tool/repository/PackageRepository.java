package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.SQLClause;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Package.PackageStatus;

@Repository
public class PackageRepository extends LogpieRepository<Package> {
	public static final String DB_TABLE_PACKAGE = "Package";

	public static final String DB_KEY_PACKAGE_ID = "PackageId";
	public static final String DB_KEY_PACKAGE_INT_DELIVERY_ID = "PackageIntDeliveryId";
	public static final String DB_KEY_PACKAGE_INT_TRACKING_NUMBER = "PackageIntTrackingNumber";
	public static final String DB_KEY_PACKAGE_DOM_DELIVERY_ID = "PackageDomDeliveryId";
	public static final String DB_KEY_PACKAGE_DOM_TRACKING_NUMBER = "PackageDomTrackingNumber";
	public static final String DB_KEY_PACKAGE_CLIENT_ID = "PackageClientId";
	public static final String DB_KEY_PACKAGE_RECEIVER = "PackageReceiver";
	public static final String DB_KEY_PACKAGE_DESTINATION = "PackageDestination";
	public static final String DB_KEY_PACKAGE_IS_DIRECT_DELIVERED = "PackageIsDirectDelivered";
	public static final String DB_KEY_PACKAGE_DATE = "PackageDate";
	public static final String DB_KEY_PACKAGE_WEIGHT = "PackageWeight";
	public static final String DB_KEY_PACKAGE_SHIPPING_FEE = "PackageShippingFee";
	public static final String DB_KEY_PACKAGE_ADDITIONAL_CUSTOM_TAX_FEE = "PackageAdditionalCustomTaxFee";
	public static final String DB_KEY_PACKAGE_ADDITIONAL_INSURANCE_FEE = "PackageAdditionalInsuranceFee";
	public static final String DB_KEY_PACKAGE_STATUS = "PackageStatus";
	public static final String DB_KEY_PACKAGE_NOTE = "PackageNote";

	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ClientRepository clientRepository;

	public List<Package> queryByClientId(final Long arg) {

		return super.queryByForeignKey(Package.class, DB_KEY_PACKAGE_CLIENT_ID,
				arg, getOrderByDateSQL(false));
	}

	public List<Package> queryByIntDeliveryId(final Long arg) {
		return super.queryByForeignKey(Package.class,
				DB_KEY_PACKAGE_INT_DELIVERY_ID, arg, getOrderByDateSQL(false));
	}

	public List<Package> queryByDomDeliveryId(final Long arg) {
		return super.queryByForeignKey(Package.class,
				DB_KEY_PACKAGE_DOM_DELIVERY_ID, arg, getOrderByDateSQL(false));
	}

	public List<Package> queryByPackageStatus(final PackageStatus arg,
			final boolean isAscendingDate) {
		List<SQLClause> whereArgs = new ArrayList<SQLClause>();
		whereArgs.add(SQLClause.createWhereClause(DB_KEY_PACKAGE_STATUS,
				arg.toString()));

		String sql = SQLUtil.querySQL(Package.class)
				+ getOrderByDateSQL(isAscendingDate)
				+ SQLUtil.whereSQL(Package.class, whereArgs);
		return super.query(sql);
	}

	public List<Package> queryAll(final boolean isAscendingDate) {
		return super
				.queryAll(Package.class, getOrderByDateSQL(isAscendingDate));
	}

	@Override
	public Package mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long id = rs.getLong(DB_KEY_PACKAGE_ID);
		Delivery intDelivery = deliveryRepository.mapRow(rs, rowNum);
		String intTracking = rs.getString(DB_KEY_PACKAGE_INT_TRACKING_NUMBER);
		Delivery domDelivery = deliveryRepository.mapRow(rs, rowNum);
		String domTracking = rs.getString(DB_KEY_PACKAGE_DOM_TRACKING_NUMBER);
		Client client = clientRepository.mapRow(rs, rowNum);
		String receiver = rs.getString(DB_KEY_PACKAGE_RECEIVER);
		String destination = rs.getString(DB_KEY_PACKAGE_DESTINATION);
		Boolean isDirect = rs.getBoolean(DB_KEY_PACKAGE_IS_DIRECT_DELIVERED);
		Timestamp date = rs.getTimestamp(DB_KEY_PACKAGE_DATE);
		Integer weight = rs.getInt(DB_KEY_PACKAGE_WEIGHT);
		Float shippingFee = rs.getFloat(DB_KEY_PACKAGE_SHIPPING_FEE);
		Float customFee = rs.getFloat(DB_KEY_PACKAGE_ADDITIONAL_CUSTOM_TAX_FEE);
		Float insuranceFee = rs
				.getFloat(DB_KEY_PACKAGE_ADDITIONAL_INSURANCE_FEE);
		PackageStatus status = PackageStatus.fromCode(rs
				.getString(DB_KEY_PACKAGE_STATUS));
		String note = rs.getString(DB_KEY_PACKAGE_NOTE);

		return new Package(id, intDelivery, intTracking, domDelivery,
				domTracking, client, receiver, destination, isDirect, date,
				weight, shippingFee, customFee, insuranceFee, status, note);
	}

	private String getOrderByDateSQL(final boolean isAscendingDate) {
		List<SQLClause> orderByArgs = new ArrayList<SQLClause>();
		orderByArgs.add(SQLClause.createOrderByClause(DB_KEY_PACKAGE_DATE,
				isAscendingDate));
		return SQLUtil.orderBySQL(orderByArgs);
	}
}
