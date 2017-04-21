package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.PackageRepository;

@Entity(name = PackageRepository.DB_TABLE_PACKAGE)
public class Package extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = PackageRepository.DB_KEY_PACKAGE_ID, type = DataType.LONG)
	private Long id;

	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_INT_DELIVERY_ID, referencedTable = Delivery.class, referencedTableAlias = "intDelivery")
	private Delivery intDelivery;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_INT_TRACKING_NUMBER, type = DataType.STRING)
	private String intTrackingNumber;

	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_DOM_DELIVERY_ID, referencedTable = Delivery.class, referencedTableAlias = "domDelivery")
	private Delivery domDelivery;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_DOM_TRACKING_NUMBER, type = DataType.STRING)
	private String domTrackingNumber;

	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_CLIENT_ID, referencedTable = Client.class, referencedTableAlias = "client")
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

	@Column(name = PackageRepository.DB_KEY_PACKAGE_ADDITIONAL_CUSTOM_TAX_FEE, type = DataType.FLOAT)
	private Float additionalCustomTaxFee;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_ADDITIONAL_INSURANCE_FEE, type = DataType.FLOAT)
	private Float additionalInsuranceFee;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_STATUS, type = DataType.STRING)
	private PackageStatus status;

	@Column(name = PackageRepository.DB_KEY_PACKAGE_NOTE, type = DataType.STRING)
	private String note;

	public Package() {

	}

	/**
	 * 
	 * @param id
	 * @param intDelivery
	 * @param intTrackingNumber
	 * @param domDelivery
	 * @param domTrackingNumber
	 * @param client
	 * @param receiver
	 * @param destination
	 * @param isDirectDelivered
	 * @param date
	 * @param weight
	 * @param shippingFee
	 * @param additionalCustomTaxFee
	 * @param additionalInsuranceFee
	 * @param status
	 * @param note
	 * @param shop
	 */
	public Package(final Long id, final Delivery intDelivery,
			final String intTrackingNumber, final Delivery domDelivery,
			final String domTrackingNumber, final Client client,
			final String receiver, final String destination,
			final Boolean isDirectDelivered, final Timestamp date,
			final Integer weight, final Float shippingFee,
			final Float additionalCustomTaxFee,
			final Float additionalInsuranceFee, final PackageStatus status,
			final String note, final Shop shop) {
		this.id = id;
		this.intDelivery = intDelivery;
		this.intTrackingNumber = intTrackingNumber;
		this.domDelivery = domDelivery;
		this.domTrackingNumber = domTrackingNumber;
		this.client = client;
		this.receiver = receiver;
		this.destination = destination;
		this.isDirectDelivered = isDirectDelivered;
		this.date = date;
		this.weight = weight;
		this.shippingFee = shippingFee;
		this.additionalCustomTaxFee = additionalCustomTaxFee;
		this.additionalInsuranceFee = additionalInsuranceFee;
		this.status = status;
		this.note = note;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public Delivery getIntDelivery() {
		return intDelivery;
	}

	public String getIntTrackingNumber() {
		return intTrackingNumber;
	}

	public Delivery getDomDelivery() {
		return domDelivery;
	}

	public String getDomTrackingNumber() {
		return domTrackingNumber;
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

	public Float getAdditionalCustomTaxFee() {
		return additionalCustomTaxFee;
	}

	public Float getAdditionalInsuranceFee() {
		return additionalInsuranceFee;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setIntDelivery(Delivery intDelivery) {
		this.intDelivery = intDelivery;
	}

	public void setIntTrackingNumber(String intTrackingNumber) {
		this.intTrackingNumber = intTrackingNumber;
	}

	public void setDomDelivery(Delivery domDelivery) {
		this.domDelivery = domDelivery;
	}

	public void setDomTrackingNumber(String domTrackingNumber) {
		this.domTrackingNumber = domTrackingNumber;
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

	public void setAdditionalCustomTaxFee(Float additionalCustomTaxFee) {
		this.additionalCustomTaxFee = additionalCustomTaxFee;
	}

	public void setAdditionalInsuranceFee(Float additionalInsuranceFee) {
		this.additionalInsuranceFee = additionalInsuranceFee;
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
