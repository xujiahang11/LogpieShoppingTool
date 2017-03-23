package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.PackageRepository;

@Table(name = PackageRepository.DB_TABLE_PACKAGE)
public class Package extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = PackageRepository.DB_KEY_PACKAGE_ID, type = DataType.LONG)
	private Long packageId;
	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_INT_DELIVERY_ID, referencedTable = Delivery.class, referencedTableAlias = "intDelivery")
	private Delivery packageIntDelivery;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_INT_TRACKING_NUMBER, type = DataType.STRING)
	private String packageIntTrackingNumber;
	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_DOM_DELIVERY_ID, referencedTable = Delivery.class, referencedTableAlias = "domDelivery")
	private Delivery packageDomDelivery;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_DOM_TRACKING_NUMBER, type = DataType.STRING)
	private String packageDomTrackingNumber;
	@ForeignEntity(name = PackageRepository.DB_KEY_PACKAGE_CLIENT_ID, referencedTable = Client.class)
	private Client packageClient;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_RECEIVER, type = DataType.STRING)
	private String packageReceiver;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_DESTINATION, type = DataType.STRING)
	private String packageDestination;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_IS_DIRECT_DELIVERED, type = DataType.BOOLEAN)
	private Boolean packageIsDirectDelivered;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_DATE, type = DataType.TIMESTAMP)
	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	private Timestamp packageDate;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_WEIGHT, type = DataType.INTEGER)
	private Integer packageWeight;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_SHIPPING_FEE, type = DataType.FLOAT)
	private Float packageShippingFee;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_ADDITIONAL_CUSTOM_TAX_FEE, type = DataType.FLOAT)
	private Float packageAdditionalCustomTaxFee;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_ADDITIONAL_INSURANCE_FEE, type = DataType.FLOAT)
	private Float packageAdditionalInsuranceFee;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_STATUS, type = DataType.STRING)
	private PackageStatus packageStatus;
	@Column(name = PackageRepository.DB_KEY_PACKAGE_NOTE, type = DataType.STRING)
	private String packageNote;

	/**
	 * constructor for creating a package
	 * 
	 * @param packageIntDelivery
	 * @param packageReceiver
	 * @param packageDestination
	 * @param packageIsDirectDelivered
	 * @param packageDate
	 * @param packageWeight
	 * @param packageStatus
	 */
	public Package(String packageReceiver, String packageDestination) {
		this(null, null, null, null, null, null, packageReceiver,
				packageDestination, new Boolean(false), null, new Integer(0),
				new Float(0), new Float(0), new Float(0),
				PackageStatus.TO_BE_SHIPPED, null);
	}

	public Package(Long packageId, Delivery packageIntDelivery,
			String packageIntTrackingNumber, Delivery packageDomDelivery,
			String packageDomTrackingNumber, Client packageClient,
			String packageReceiver, String packageDestination,
			Boolean packageIsDirectDelivered, Timestamp packageDate,
			Integer packageWeight, Float packageShippingFee,
			Float packageAdditionalCustomTaxFee,
			Float packageAdditionalInsuranceFee, PackageStatus packageStatus,
			String packageNote) {
		this.packageId = packageId;
		this.packageIntDelivery = packageIntDelivery;
		this.packageIntTrackingNumber = packageIntTrackingNumber;
		this.packageDomDelivery = packageDomDelivery;
		this.packageDomTrackingNumber = packageDomTrackingNumber;
		this.packageClient = packageClient;
		this.packageReceiver = packageReceiver;
		this.packageDestination = packageDestination;
		this.packageIsDirectDelivered = packageIsDirectDelivered;
		this.packageDate = packageDate;
		this.packageWeight = packageWeight;
		this.packageShippingFee = packageShippingFee;
		this.packageAdditionalCustomTaxFee = packageAdditionalCustomTaxFee;
		this.packageAdditionalInsuranceFee = packageAdditionalInsuranceFee;
		this.packageStatus = packageStatus;
		this.packageNote = packageNote;
	}

	public Long getPackageId() {
		return packageId;
	}

	public Delivery getPackageIntDelivery() {
		return packageIntDelivery;
	}

	public String getPackageIntTrackingNumber() {
		return packageIntTrackingNumber;
	}

	public Delivery getPackageDomDelivery() {
		return packageDomDelivery;
	}

	public String getPackageDomTrackingNumber() {
		return packageDomTrackingNumber;
	}

	public Client getPackageClient() {
		return packageClient;
	}

	public String getPackageReceiver() {
		return packageReceiver;
	}

	public String getPackageDestination() {
		return packageDestination;
	}

	public Boolean getPackageIsDirectDelivered() {
		return packageIsDirectDelivered;
	}

	public Timestamp getPackageDate() {
		return packageDate;
	}

	public Integer getPackageWeight() {
		return packageWeight;
	}

	public Float getPackageShippingFee() {
		return packageShippingFee;
	}

	public Float getPackageAdditionalCustomTaxFee() {
		return packageAdditionalCustomTaxFee;
	}

	public Float getPackageAdditionalInsuranceFee() {
		return packageAdditionalInsuranceFee;
	}

	public PackageStatus getPackageStatus() {
		return packageStatus;
	}

	public String getPackageNote() {
		return packageNote;
	}

	public void setPackageIntDelivery(Delivery packageIntDelivery) {
		this.packageIntDelivery = packageIntDelivery;
	}

	public void setPackageIntTrackingNumber(String packageIntTrackingNumber) {
		this.packageIntTrackingNumber = packageIntTrackingNumber;
	}

	public void setPackageDomDelivery(Delivery packageDomDelivery) {
		this.packageDomDelivery = packageDomDelivery;
	}

	public void setPackageDomTrackingNumber(String packageDomTrackingNumber) {
		this.packageDomTrackingNumber = packageDomTrackingNumber;
	}

	public void setPackageClient(Client packageClient) {
		this.packageClient = packageClient;
	}

	public void setPackageReceiver(String packageReceiver) {
		this.packageReceiver = packageReceiver;
	}

	public void setPackageDestination(String packageDestination) {
		this.packageDestination = packageDestination;
	}

	public void setPackageIsDirectDelivered(Boolean packageIsDirectDelivered) {
		this.packageIsDirectDelivered = packageIsDirectDelivered;
	}

	public void setPackageDate(Timestamp packageDate) {
		this.packageDate = packageDate;
	}

	public void setPackageWeight(Integer packageWeight) {
		this.packageWeight = packageWeight;
	}

	public void setPackageShippingFee(Float packageShippingFee) {
		this.packageShippingFee = packageShippingFee;
	}

	public void setPackageAdditionalCustomTaxFee(
			Float packageAdditionalCustomTaxFee) {
		this.packageAdditionalCustomTaxFee = packageAdditionalCustomTaxFee;
	}

	public void setPackageAdditionalInsuranceFee(
			Float packageAdditionalInsuranceFee) {
		this.packageAdditionalInsuranceFee = packageAdditionalInsuranceFee;
	}

	public void setPackageStatus(PackageStatus packageStatus) {
		this.packageStatus = packageStatus;
	}

	public void setPackageNote(String packageNote) {
		this.packageNote = packageNote;
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
