package cn.net.ibingo.core.model;

import java.util.Date;

/**
 * @author  yxj
 * 访问成功日志
 */
public class NotifySuccessRawData {

	private String ip;
	private String ipCountry;
	private String ipOperator;
	private String fristChannelId;
	private String twoChannelId;
	private String type;
	private String orderId;
	private String userId;
	private String imsi;
	private String advertisersCode;
	private String advertisersName;
	private String resourcesId;
	private String resourcesName;
	private String callBackUrl;
	private String price;
	private String hashCode;
	private String status;
	private Date datetime;



	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDatetime(Date date) {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getIpCountry() {
		return ipCountry;
	}

	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}

	public String getIpOperator() {
		return ipOperator;
	}

	public void setIpOperator(String ipOperator) {
		this.ipOperator = ipOperator;
	}

	public String getFristChannelId() {
		return fristChannelId;
	}

	public void setFristChannelId(String fristChannelId) {
		this.fristChannelId = fristChannelId;
	}

	public String getTwoChannelId() {
		return twoChannelId;
	}

	public void setTwoChannelId(String twoChannelId) {
		this.twoChannelId = twoChannelId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getAdvertisersCode() {
		return advertisersCode;
	}

	public void setAdvertisersCode(String advertisersCode) {
		this.advertisersCode = advertisersCode;
	}

	public String getAdvertisersName() {
		return advertisersName;
	}

	public void setAdvertisersName(String advertisersName) {
		this.advertisersName = advertisersName;
	}

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
