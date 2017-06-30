package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AdvertisersStatisticsMapper;
import cn.net.ibingo.core.dao.TrafficSourceStatisticsMapper;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.TrafficSourceStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.query.TrafficSourceStatisticsQueryBean;
import cn.net.ibingo.core.service.AdvertisersStatisticsService;
import cn.net.ibingo.core.service.TrafficSourceStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;

@Service
public class TrafficSourceStatisticsServiceImpl implements TrafficSourceStatisticsService {

    @Autowired
    private TrafficSourceStatisticsMapper trafficSourceStatisticsMapper;

    @Override
    public PaginationList<TrafficSourceStatistics> list(TrafficSourceStatisticsQueryBean queryBean) {
        queryBean.setPageSize(50);
        List<Integer> totalCount = trafficSourceStatisticsMapper.selectCountByQueryBean(queryBean);
        List<TrafficSourceStatistics> list = trafficSourceStatisticsMapper.selectByQueryBean(queryBean);
        SimplePaginatedList<TrafficSourceStatistics> pList = new SimplePaginatedList<TrafficSourceStatistics>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount.size());
        return pList;
    }

    @Override
    public PaginationList<TrafficSourceStatistics> rateList(TrafficSourceStatisticsQueryBean queryBean) {
        queryBean.setPageSize(50);
        List<Integer> totalCount = trafficSourceStatisticsMapper.selectRateCountByQueryBean(queryBean);
        List<TrafficSourceStatistics> list = trafficSourceStatisticsMapper.selectRateByQueryBean(queryBean);
        SimplePaginatedList<TrafficSourceStatistics> pList = new SimplePaginatedList<TrafficSourceStatistics>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount.size());
        return pList;
    }


}
