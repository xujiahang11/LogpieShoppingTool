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
import com.logpie.shopping.tool.repository.CostRepository;

@Entity(name = CostRepository.DB_TABLE_COST)
public class Cost extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = CostRepository.DB_KEY_COST_ID, type = DataType.BIGINT)
	private BigInteger id;

	@Column(name = CostRepository.DB_KEY_COST_NAME, type = DataType.STRING)
	private String name;

	@Column(name = CostRepository.DB_KEY_COST_TYPE, type = DataType.STRING)
	private CostType type;

	@Column(name = CostRepository.DB_KEY_COST_DESC, type = DataType.STRING)
	private String desc;

	@Column(name = CostRepository.DB_KEY_COST_VALUE, type = DataType.FLOAT)
	private Float value;

	@Column(name = CostRepository.DB_KEY_COST_CREATION_TIME, type = DataType.TIMESTAMP)
	private Timestamp creationTime;

	@ForeignKeyColumn(name = CostRepository.DB_KEY_COST_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Cost() {

	}

	public Cost(final BigInteger id, final String name, final CostType type,
			final String desc, final Float value, final Timestamp creationTime,
			final Shop shop) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.value = value;
		this.creationTime = creationTime;
		this.shop = shop;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public CostType getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public Float getValue() {
		return value;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(CostType type) {
		this.type = type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public enum CostType {

		PRODUCT_COST("0", ""), PERIOD_COST("1", "");

		private String code;
		private String text;

		private CostType(String code, String text) {
			this.code = code;
			this.text = text;
		}

		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return code;
		}

		public static CostType fromCode(String code) {
			for (CostType type : CostType.values()) {
				if (type.toString().equals(code)) {
					return type;
				}
			}
			return null;
		}
	}
}
