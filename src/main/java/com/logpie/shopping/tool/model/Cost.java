package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.CostRepository;

@Entity(name = CostRepository.DB_TABLE_COST)
public class Cost extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = CostRepository.DB_KEY_COST_ID, type = DataType.LONG)
	private Long id;

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

	public Cost(final Long id, final String name, final CostType type,
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

	public Long getId() {
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

	public void setId(Long id) {
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
