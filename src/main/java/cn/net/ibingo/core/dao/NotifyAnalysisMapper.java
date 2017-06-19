package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.NotifyAnalysis;
import cn.net.ibingo.core.query.NotifyAnalysisQueryBean;
import cn.net.ibingo.core.query.NotifyChannelAnalysisQueryBean;

public interface NotifyAnalysisMapper {
	
    int selectCountByQueryBean(NotifyAnalysisQueryBean queryBean);
    
    List<NotifyAnalysis> selectByQueryBean(NotifyAnalysisQueryBean queryBean);
    
    NotifyAnalysis selectSum(NotifyAnalysis record);

    int insertSelective(NotifyAnalysis record);
    
}