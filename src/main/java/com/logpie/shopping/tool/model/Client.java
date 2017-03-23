package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.ClientRepository;

@Table(name = ClientRepository.DB_TABLE_CLIENT)
public class Client extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ClientRepository.DB_KEY_CLIENT_ID, type = DataType.LONG)
	private Long clientId;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_DIRECTED_ID, type = DataType.STRING)
	private String clientWechatDirectedId;

	@Column(name = ClientRepository.DB_KEY_CLIENT_NAME, type = DataType.STRING)
	private String clientName;

	@Column(name = ClientRepository.DB_KEY_CLIENT_PHONE, type = DataType.STRING)
	private String clientPhone;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_ID, type = DataType.STRING)
	private String clientWechatId;

	@Column(name = ClientRepository.DB_KEY_CLIENT_WECHAT_NAME, type = DataType.STRING)
	private String clientWechatName;

	@Column(name = ClientRepository.DB_KEY_CLIENT_TAOBAO_NAME, type = DataType.STRING)
	private String clientTaobaoName;

	@Column(name = ClientRepository.DB_KEY_CLIENT_NOTE, type = DataType.STRING)
	private String clientNote;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ClientRepository.DB_KEY_CLIENT_REGISTER_TIME, type = DataType.TIMESTAMP)
	private Timestamp clientRegisterTime;

	/**
	 * constructor for creating a client
	 * 
	 * @param ClientName
	 * @param ClientPhone
	 */
	public Client(String ClientName, String ClientPhone) {
		this(null, null, ClientName, ClientPhone, null, null, null, null, null);
	}

	/**
	 * 
	 * @param ClientId
	 * @param ClientWechatDirectedId
	 * @param ClientName
	 * @param ClientPhone
	 * @param ClientWechatId
	 * @param ClientWechatName
	 * @param ClientTaobaoName
	 * @param ClientNote
	 * @param ClientRegisterTime
	 */
	public Client(Long ClientId, String ClientWechatDirectedId,
			String ClientName, String ClientPhone, String ClientWechatId,
			String ClientWechatName, String ClientTaobaoName,
			String ClientNote, Timestamp ClientRegisterTime) {
		this.clientId = ClientId;
		this.clientWechatDirectedId = ClientWechatDirectedId;
		this.clientName = ClientName;
		this.clientPhone = ClientPhone;
		this.clientWechatId = ClientWechatId;
		this.clientWechatName = ClientWechatName;
		this.clientTaobaoName = ClientTaobaoName;
		this.clientNote = ClientNote;
		this.clientRegisterTime = ClientRegisterTime;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getClientWechatDirectedId() {
		return clientWechatDirectedId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public String getClientWechatId() {
		return clientWechatId;
	}

	public String getClientWechatName() {
		return clientWechatName;
	}

	public String getClientTaobaoName() {
		return clientTaobaoName;
	}

	public String getClientNote() {
		return clientNote;
	}

	public Timestamp getClientRegisterTime() {
		return clientRegisterTime;
	}

	public void setClientWechatDirectedId(String ClientWechatDirectedId) {
		this.clientWechatDirectedId = ClientWechatDirectedId;
	}

	public void setClientName(String ClientName) {
		this.clientName = ClientName;
	}

	public void setClientPhone(String ClientPhone) {
		this.clientPhone = ClientPhone;
	}

	public void setClientWechatId(String ClientWechatId) {
		this.clientWechatId = ClientWechatId;
	}

	public void setClientWechatName(String ClientWechatName) {
		this.clientWechatName = ClientWechatName;
	}

	public void setClientTaobaoName(String ClientTaobaoName) {
		this.clientTaobaoName = ClientTaobaoName;
	}

	public void setClientNote(String ClientNote) {
		this.clientNote = ClientNote;
	}
}
