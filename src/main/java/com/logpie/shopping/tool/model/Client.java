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
import com.logpie.shopping.tool.repository.ClientRepository;

@Entity(name = ClientRepository.DB_TABLE_CLIENT)
public class Client extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ClientRepository.DB_KEY_CLIENT_ID, type = DataType.LONG)
	private Long id;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_DIRECTED_ID, type = DataType.STRING)
	private String wechatDirectedId;

	@Column(name = ClientRepository.DB_KEY_CLIENT_NAME, type = DataType.STRING)
	private String name;

	@Column(name = ClientRepository.DB_KEY_CLIENT_PHONE, type = DataType.STRING)
	private String phone;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_ID, type = DataType.STRING)
	private String wechatId;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_NAME, type = DataType.STRING)
	private String wechatName;

	@Column(name = ClientRepository.DB_KEY_CLIENT_NOTE, type = DataType.STRING)
	private String note;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ClientRepository.DB_KEY_CLIENT_REGISTER_TIME, type = DataType.TIMESTAMP)
	private Timestamp registerTime;

	@ForeignKeyColumn(name = ClientRepository.DB_KEY_CLIENT_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Client() {

	}

	/**
	 * 
	 * @param id
	 * @param wechatDirectedId
	 * @param name
	 * @param phone
	 * @param wechatId
	 * @param wechatName
	 * @param taobaoName
	 * @param note
	 * @param registerTime
	 * @param shop
	 */
	public Client(Long id, String wechatDirectedId, String name, String phone,
			String wechatId, String wechatName, String note,
			Timestamp registerTime, Shop shop) {
		this.id = id;
		this.wechatDirectedId = wechatDirectedId;
		this.name = name;
		this.phone = phone;
		this.wechatId = wechatId;
		this.wechatName = wechatName;
		this.note = note;
		this.registerTime = registerTime;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getWechatDirectedId() {
		return wechatDirectedId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getWechatId() {
		return wechatId;
	}

	public String getWechatName() {
		return wechatName;
	}

	public String getNote() {
		return note;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWechatDirectedId(String wechatDirectedId) {
		this.wechatDirectedId = wechatDirectedId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
