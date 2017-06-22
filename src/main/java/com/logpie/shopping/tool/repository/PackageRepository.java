package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Package.PackageStatus;

@Repository
public class PackageRepository extends JDBCTemplateRepository<Package> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_PACKAGE = "Package";

	public static final String DB_KEY_PACKAGE_ID = "packageId";
	public static final String DB_KEY_PACKAGE_EXPRESS_ID = "packageExpressId";
	public static final String DB_KEY_PACKAGE_TRACKING_NUMBER = "trackingNumber";
	public static final String DB_KEY_PACKAGE_CLIENT_ID = "packageClientId";
	public static final String DB_KEY_PACKAGE_RECEIVER = "receiver";
	public static final String DB_KEY_PACKAGE_DESTINATION = "destination";
	public static final String DB_KEY_PACKAGE_IS_DIRECT_DELIVERED = "isDirectDelivered";
	public static final String DB_KEY_PACKAGE_DATE = "packagePostDate";
	public static final String DB_KEY_PACKAGE_WEIGHT = "packageWeight";
	public static final String DB_KEY_PACKAGE_SHIPPING_FEE = "shippingFee";
	public static final String DB_KEY_PACKAGE_ADDITIONAL_FEE = "additionalFee";
	public static final String DB_KEY_PACKAGE_STATUS = "packageStatus";
	public static final String DB_KEY_PACKAGE_NOTE = "packageNote";
	public static final String DB_KEY_PACKAGE_SHOP_ID = "packageShopId";

	@Autowired
	private ExpressRepository expressRepository;
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

	public Page<Package> queryByExpressId(final int pageNumber,
			final Long expressId) throws DataAccessException {
		Parameter param = new WhereParam(Package.class,
				DB_KEY_PACKAGE_EXPRESS_ID, expressId);
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
		Package pack = new Package();
		pack.setId(rs.getLong(DB_KEY_PACKAGE_ID));
		pack.setExpress(expressRepository.mapRow(rs, rowNum));
		pack.setTrackingNumber(rs.getString(DB_KEY_PACKAGE_TRACKING_NUMBER));
		pack.setClient(clientRepository.mapRow(rs, rowNum));
		pack.setReceiver(rs.getString(DB_KEY_PACKAGE_RECEIVER));
		pack.setDestination(rs.getString(DB_KEY_PACKAGE_DESTINATION));
		pack.setIsDirectDelivered(rs
				.getBoolean(DB_KEY_PACKAGE_IS_DIRECT_DELIVERED));
		pack.setDate(rs.getTimestamp(DB_KEY_PACKAGE_DATE));
		pack.setWeight(rs.getInt(DB_KEY_PACKAGE_WEIGHT));
		pack.setShippingFee(rs.getFloat(DB_KEY_PACKAGE_SHIPPING_FEE));
		pack.setAdditionalFee(rs.getFloat(DB_KEY_PACKAGE_ADDITIONAL_FEE));
		pack.setStatus(PackageStatus.fromCode(rs
				.getString(DB_KEY_PACKAGE_STATUS)));
		pack.setNote(rs.getString(DB_KEY_PACKAGE_NOTE));
		pack.setShop(shopRepository.mapRow(rs, rowNum));

		return pack;
	}
}
