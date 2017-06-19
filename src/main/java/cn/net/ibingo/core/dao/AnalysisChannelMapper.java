package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.AnalysisChannel;
import cn.net.ibingo.core.query.AnalysisChannelQueryBean;

public interface AnalysisChannelMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(AnalysisChannel record);

    int insertSelective(AnalysisChannel record);

    AnalysisChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnalysisChannel record);

    int updateByPrimaryKey(AnalysisChannel record);
    
    int selectCountByQueryBean(AnalysisChannelQueryBean queryBean);
    
    List<AnalysisChannel> selectByQueryBean(AnalysisChannelQueryBean queryBean);
    
    List<AnalysisChannel> selectLinkCode();
    
    int deleteByFristCode(String fristCode);
    
    int deleteByTwoCode(String twoCode);
    
    AnalysisChannel selectSum (AnalysisChannel record);
    
}