package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.PageRequest;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.Sort;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Package.PackageStatus;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class PackageRepository extends JDBCTemplateRepository<Package> {

	public static final Integer PAGE_SIZE = 20;

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
	public static final String DB_KEY_PACKAGE_SHOP_ID = "PackageShopId";

	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ShopRepository shopRepository;

	private Sort sort;

	public PackageRepository() {
		super(Package.class);
		sort = new Sort(Sort.Direction.DESC, DB_KEY_PACKAGE_DATE);
	}

	public Page<Package> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Package.class, DB_KEY_PACKAGE_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Package> queryByClientId(final int pageNumber,
			final Long clientId) throws DataAccessException {
		Parameter param = new WhereParam(Package.class,
				DB_KEY_PACKAGE_CLIENT_ID, clientId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Package> queryByIntDeliveryId(final int pageNumber,
			final Long deliveryId) throws DataAccessException {
		Parameter param = new WhereParam(Package.class,
				DB_KEY_PACKAGE_INT_DELIVERY_ID, deliveryId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Package> queryByDomDeliveryId(final int pageNumber,
			final Long deliveryId) throws DataAccessException {
		Parameter param = new WhereParam(Package.class,
				DB_KEY_PACKAGE_DOM_DELIVERY_ID, deliveryId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Package> queryByStatus(final int pageNumber, final Long shopId,
			final PackageStatus status) throws DataAccessException {
		Parameter param_shop = new WhereParam(Package.class,
				DB_KEY_PACKAGE_SHOP_ID, shopId);
		Parameter param_status = new WhereParam(Package.class,
				DB_KEY_PACKAGE_STATUS, status.toString());
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param_shop, param_status);
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
		Shop shop = shopRepository.mapRow(rs, rowNum);

		return new Package(id, intDelivery, intTracking, domDelivery,
				domTracking, client, receiver, destination, isDirect, date,
				weight, shippingFee, customFee, insuranceFee, status, note,
				shop);
	}
}
