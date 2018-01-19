package com.logpie.shopping.tool.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.PackageRepository;

@Entity(name = PackageRepository.DB_TABLE_PACKAGE)
public class Package extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = PackageRepository.DB_KEY_PACKAGE_ID, type = DataType.BIGINT)
	private BigInteger id;

	@ForeignKeyColumn(name = PackageRepository.DB_KEY_PACKAGE_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	@ForeignKeyColumn(name = PackageRepository.DB_KEY_PACKAGE_EXPRESS_ID, referencedEntityClass = Express.class)
	private Express express;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_TRACKING_NUMBER, type = DataType.STRING)
	private String trackingNumber;

	@ForeignKeyColumn(name = PackageRepository.DB_KEY_PACKAGE_CLIENT_ID, referencedEntityClass = Client.class)
	private Client client;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_RECEIVER, type = DataType.STRING)
	private String receiver;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_DESTINATION, type = DataType.STRING)
	private String destination;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_IS_DIRECT_DELIVERED, type = DataType.BOOLEAN)
	private Boolean isDirectDelivered;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_DATE, type = DataType.TIMESTAMP)
	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	private Timestamp date;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_WEIGHT, type = DataType.INTEGER)
	private Integer weight;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_SHIPPING_FEE, type = DataType.FLOAT)
	private Float shippingFee;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_ADDITIONAL_FEE, type = DataType.FLOAT)
	private Float additionalFee;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_STATUS, type = DataType.STRING)
	private PackageStatus status;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_NOTE, type = DataType.STRING)
	private String note;

	public Package() {

	}

	public Package(final BigInteger id, final Express express,
			final String trackingNumber, final Client client,
			final String receiver, final String destination,
			final Boolean isDirectDelivered, final Timestamp date,
			final Integer weight, final Float shippingFee,
			final Float additionalFee, final PackageStatus status,
			final String note, final Shop shop) {
		this.id = id;
		this.express = express;
		this.trackingNumber = trackingNumber;
		this.client = client;
		this.receiver = receiver;
		this.destination = destination;
		this.isDirectDelivered = isDirectDelivered;
		this.date = date;
		this.weight = weight;
		this.shippingFee = shippingFee;
		this.additionalFee = additionalFee;
		this.status = status;
		this.note = note;
		this.shop = shop;
	}

	public BigInteger getId() {
		return id;
	}

	public Express getExpress() {
		return express;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public Client getClient() {
		return client;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getDestination() {
		return destination;
	}

	public Boolean getIsDirectDelivered() {
		return isDirectDelivered;
	}

	public Timestamp getDate() {
		return date;
	}

	public Integer getWeight() {
		return weight;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public Float getAdditionalFee() {
		return additionalFee;
	}

	public PackageStatus getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setIsDirectDelivered(Boolean isDirectDelivered) {
		this.isDirectDelivered = isDirectDelivered;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public void setAdditionalFee(Float additionalFee) {
		this.additionalFee = additionalFee;
	}

	public void setStatus(PackageStatus status) {
		this.status = status;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public enum PackageStatus {
		TO_BE_SHIPPED("1", "国际待发"), INT_SHIPPING("2", "国际在途"), DOM_SHIPPING(
				"3", "国内在途"), DELIVERED("4", "已到包裹");

		private final String code;
		private final String text;

		private PackageStatus(final String code, final String text) {
			this.text = text;
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}

		public String getText() {
			return text;
		}

		public static PackageStatus fromCode(String code) {
			for (PackageStatus status : PackageStatus.values()) {
				if (status.toString().equals(code)) {
					return status;
				}
			}
			return null;
		}
	}
}
