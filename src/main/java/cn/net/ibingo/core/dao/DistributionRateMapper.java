package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistributionRateMapper {
	

    List<DistributionRate> selectByTrafficeId(String trafficId);
    
    int insertSelective(DistributionRate dr);

    int deleteDistributionByTrafficId(String trafficId);

    Float selectByTrafficIdAndOfferId(@Param("voluumTrafficSourceId")String voluumTrafficSourceId, @Param("voluumOfferId")String voluumOfferId);

    List<DistributionRate> selectAll();
}