package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.ColorRepository;

@Table(name = ColorRepository.DB_TABLE_COLOR)
public class Color extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ColorRepository.DB_KEY_COLOR_ID, type = DataType.LONG)
	private Long colorId;

	@Column(name = ColorRepository.DB_KEY_COLOR_NAME, type = DataType.STRING)
	private String colorName;

	/**
	 * Constructor for creating a color
	 *
	 * @param colorName
	 */
	public Color(final String colorName) {
		this(null, colorName);
	}

	/**
	 * 
	 * @param colorId
	 * @param colorName
	 */
	public Color(final Long colorId, final String colorName) {
		this.colorId = colorId;
		this.colorName = colorName;
	}

	public Long getColorId() {
		return colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(final String colorName) {
		this.colorName = colorName;
	}

}
