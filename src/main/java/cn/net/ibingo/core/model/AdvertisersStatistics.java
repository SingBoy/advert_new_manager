package cn.net.ibingo.core.model;

import java.util.Date;

/**
 * 按广告主统计数据
 */
public class AdvertisersStatistics {

    private Integer id;

    private String country;

    private String offerId;//广告id

    private String offerName;//广告名称

    private String offerNameAlias;//广告别名

    private String affiliateNetworkId;//广告主id

    private String affiliateNetworkName;//广告主名称

    private String trafficSourceId;//渠道id

    private String trafficSourceName;//渠道名称

    private int visitsNum;//访问数

    private int conversNum;//转化数

    private float conversRate;//转化率

    private Date date;//日期

    private Date createDate;

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

    public String getOfferNameAlias() {
        return offerNameAlias;
    }

    public void setOfferNameAlias(String offerNameAlias) {
        this.offerNameAlias = offerNameAlias;
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

    public int getVisitsNum() {
        return visitsNum;
    }

    public void setVisitsNum(int visitsNum) {
        this.visitsNum = visitsNum;
    }

    public int getConversNum() {
        return conversNum;
    }

    public void setConversNum(int conversNum) {
        this.conversNum = conversNum;
    }

    public float getConversRate() {
        return conversRate;
    }

    public void setConversRate(float conversRate) {
        this.conversRate = conversRate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}