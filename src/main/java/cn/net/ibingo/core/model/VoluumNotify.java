package cn.net.ibingo.core.model;

import java.util.Date;

public class VoluumNotify {
    private Integer id;

    private String country;

    private String trafficSourceId;//渠道id

    private String trafficSourceName;//渠道名称

    private String offerId;//广告id

    private String offerName;//广告名称

    private String affiliateNetworkId;//广告主id

    private String affiliateNetworkName;//广告主名称

    private String campaignId;

    private String clickId;//点击id

    private Float payout;//价格

    private String p1;//参数1

    private String p2;//参数2

    private String p3;//参数3

    private String p4;//参数4

    private String p5;//参数5

    private Date createDate;

    private int dataType;//0表示总数据。1表示总数据且成功下发渠道的数据

    private int conversNum;//统计总数（转化数）此字段只接收查询数据

    private String offerNameAlias;//广告别名   此字段只接收查询数据

    private Float subscriptionRate;//广告和渠道之间的分配比例   此字段只接收查询数据

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getTrafficSourceId() {
        return trafficSourceId;
    }

    public void setTrafficSourceId(String trafficSourceId) {
        this.trafficSourceId = trafficSourceId;
    }

    public String getTrafficSourceName() {
        return trafficSourceName;
    }

    public void setTrafficSourceName(String trafficSourceName) {
        this.trafficSourceName = trafficSourceName;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getAffiliateNetworkId() {
        return affiliateNetworkId;
    }

    public void setAffiliateNetworkId(String affiliateNetworkId) {
        this.affiliateNetworkId = affiliateNetworkId;
    }

    public String getAffiliateNetworkName() {
        return affiliateNetworkName;
    }

    public void setAffiliateNetworkName(String affiliateNetworkName) {
        this.affiliateNetworkName = affiliateNetworkName;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public Float getPayout() {
        return payout;
    }

    public void setPayout(Float payout) {
        this.payout = payout;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getConversNum() {
        return conversNum;
    }

    public void setConversNum(int conversNum) {
        this.conversNum = conversNum;
    }

    public String getOfferNameAlias() {
        return offerNameAlias;
    }

    public void setOfferNameAlias(String offerNameAlias) {
        this.offerNameAlias = offerNameAlias;
    }

    public Float getSubscriptionRate() {
        return subscriptionRate;
    }

    public void setSubscriptionRate(Float subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }
}