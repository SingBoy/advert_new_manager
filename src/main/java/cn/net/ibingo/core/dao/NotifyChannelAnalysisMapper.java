package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.NotifyChannelAnalysis;
import cn.net.ibingo.core.query.NotifyChannelAnalysisQueryBean;

public interface NotifyChannelAnalysisMapper {
	
    int selectCountByQueryBean(NotifyChannelAnalysisQueryBean queryBean);
    
    List<NotifyChannelAnalysis> selectByQueryBean1(NotifyChannelAnalysisQueryBean queryBean);

    int selectCountByQueryBean1(NotifyChannelAnalysisQueryBean queryBean);

    List<NotifyChannelAnalysis> selectByQueryBean2(NotifyChannelAnalysisQueryBean queryBean);

    int selectCountByQueryBean2(NotifyChannelAnalysisQueryBean queryBean);

    List<NotifyChannelAnalysis> selectByQueryBean(NotifyChannelAnalysisQueryBean queryBean);

    Integer selectSum(NotifyChannelAnalysis record);

}