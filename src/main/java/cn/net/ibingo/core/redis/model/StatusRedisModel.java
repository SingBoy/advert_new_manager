package cn.net.ibingo.core.redis.model;

import cn.net.ibingo.common.utils.ConstantConfig;

import java.util.Set;

public class StatusRedisModel {
	private String offerId;

	private String trafficId;

    private Set<Integer> statusSet;

    public String getKey() {
        return ConstantConfig.PROJECT_NAME +"_status_"+offerId+"_"+trafficId;
    }

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTrafficId() {
		return trafficId;
	}

	public void setTrafficId(String trafficId) {
		this.trafficId = trafficId;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public void setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
	}
}