package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AdvertisersStatisticsMapper;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.service.AdvertisersStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisersStatisticsServiceImpl implements AdvertisersStatisticsService {

    @Autowired
    private AdvertisersStatisticsMapper advertisersStatisticsMapper;

    @Override
    public PaginationList<AdvertisersStatistics> list(AdvertisersStatisticsQueryBean queryBean) {
        queryBean.setPageSize(50);
        List<Integer> totalCount = advertisersStatisticsMapper.selectCountByQueryBean(queryBean);
        List<AdvertisersStatistics> list = advertisersStatisticsMapper.selectByQueryBean(queryBean);
        SimplePaginatedList<AdvertisersStatistics> pList = new SimplePaginatedList<AdvertisersStatistics>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount.size());
        return pList;
    }


}
