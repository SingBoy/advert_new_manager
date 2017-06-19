package cn.net.ibingo.core.model;

import java.util.Date;

/**
 * 渠道对应offer（广告）的分配比例
 * @author yuxiangjie 2017-06-08
*/
public class DistributionRate {

    private int id;

    private String voluumTrafficSourceId;
    
    private String voluumOfferId;

    private Float subscriptionRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoluumTrafficSourceId() {
        return voluumTrafficSourceId;
    }

    public void setVoluumTrafficSourceId(String voluumTrafficSourceId) {
        this.voluumTrafficSourceId = voluumTrafficSourceId;
    }

    public String getVoluumOfferId() {
        return voluumOfferId;
    }

    public void setVoluumOfferId(String voluumOfferId) {
        this.voluumOfferId = voluumOfferId;
    }

    public Float getSubscriptionRate() {
        return subscriptionRate;
    }

    public void setSubscriptionRate(Float subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }
}