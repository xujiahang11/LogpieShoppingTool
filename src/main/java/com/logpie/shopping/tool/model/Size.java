package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.SizeRepository;

@Table(name = SizeRepository.DB_TABLE_SIZE)
public class Size extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = SizeRepository.DB_KEY_SIZE_ID, type = DataType.LONG)
	private Long sizeId;

	@Column(name = SizeRepository.DB_KEY_SIZE_NAME, type = DataType.STRING)
	private String sizeName;

	public Size(String sizeName) {
		this(null, sizeName);
	}

	public Size(Long sizeId, String sizeName) {
		this.sizeId = sizeId;
		this.sizeName = sizeName;
	}

	public Long getSizeId() {
		return sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

}
