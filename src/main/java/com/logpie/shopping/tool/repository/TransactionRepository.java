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
import com.logpie.shopping.tool.model.Transaction;

@Repository
public class TransactionRepository extends JDBCTemplateRepository<Transaction>
		implements ResultSetExtractor<List<Transaction>> {

	public static final String DB_TABLE_TRANSACTION = "Transaction";

	public static final String DB_KEY_TRANSACTION_ID = "id";
	public static final String DB_KEY_TRANSACTION_PRODUCT_ID = "productId";
	public static final String DB_KEY_TRANSACTION_QUANTITY = "quantity";
	public static final String DB_KEY_TRANSACTION_UNIT_PRICE = "unitPrice";
	public static final String DB_KEY_TRANSACTION_PAYMENT = "payment";
	public static final String DB_KEY_TRANSACTION_ORDER_ID = "orderId";
	public static final String DB_KEY_TRANSACTION_IS_RETURNED = "isReturned";

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ShippingRecordRepository recordRepository;

	public TransactionRepository() {
		super(Transaction.class);
	}

	public List<Transaction> queryByOrderId(final Long orderId)
			throws DataAccessException {
		Parameter param = new WhereParam(Transaction.class,
				DB_KEY_TRANSACTION_ORDER_ID, orderId);

		return (List<Transaction>) super.queryBy(param);
	}

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction = new Transaction();

		transaction.setId(rs.getLong(DB_KEY_TRANSACTION_ID));
		transaction.setProduct(productRepository.mapRow(rs, rs.getRow()));
		transaction.setQuantity(rs.getInt(DB_KEY_TRANSACTION_QUANTITY));
		transaction.setUnitPrice(rs.getFloat(DB_KEY_TRANSACTION_UNIT_PRICE));
		transaction.setPayment(rs.getFloat(DB_KEY_TRANSACTION_PAYMENT));
		transaction.setOrder(orderRepository.mapRow(rs, rs.getRow()));
		transaction
				.setIsReturned(rs.getBoolean(DB_KEY_TRANSACTION_IS_RETURNED));
		transaction.setRecords(recordRepository.extractData(rs));

		return transaction;
	}

	@Override
	public List<Transaction> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		while (rs.next()) {
			Transaction transaction = new Transaction();
			transaction.setId(rs.getLong(DB_KEY_TRANSACTION_ID));
			transaction.setProduct(productRepository.mapRow(rs, rs.getRow()));
			transaction.setQuantity(rs.getInt(DB_KEY_TRANSACTION_QUANTITY));
			transaction
					.setUnitPrice(rs.getFloat(DB_KEY_TRANSACTION_UNIT_PRICE));
			transaction.setPayment(rs.getFloat(DB_KEY_TRANSACTION_PAYMENT));
			transaction.setOrder(orderRepository.mapRow(rs, rs.getRow()));
			transaction.setIsReturned(rs
					.getBoolean(DB_KEY_TRANSACTION_IS_RETURNED));
			transaction.setRecords(recordRepository.extractData(rs));
			transactionList.add(transaction);
		}
		return transactionList;
	}

}
