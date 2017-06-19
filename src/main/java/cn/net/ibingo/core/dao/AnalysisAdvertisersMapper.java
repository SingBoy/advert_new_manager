package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.AnalysisAdvertisers;
import cn.net.ibingo.core.query.AnalysisAdvertisersQueryBean;

public interface AnalysisAdvertisersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisAdvertisers record);

    int insertSelective(AnalysisAdvertisers record);

    AnalysisAdvertisers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisAdvertisers record);

    int updateByPrimaryKey(AnalysisAdvertisers record);
    
    int selectCountByQueryBean(AnalysisAdvertisersQueryBean queryBean);
    
    List<AnalysisAdvertisers> selectByQueryBean(AnalysisAdvertisersQueryBean queryBean);
    
    int deleteByAdsId(Integer adsId);
    
    int deleteByResId(Integer resId);
    
    AnalysisAdvertisers selectSum(AnalysisAdvertisers record);
    
}