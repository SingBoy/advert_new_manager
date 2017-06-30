package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AdvertisersStatisticsMapper;
import cn.net.ibingo.core.dao.OfferStatisticsMapper;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.OfferStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.query.OfferStatisticsQueryBean;
import cn.net.ibingo.core.service.AdvertisersStatisticsService;
import cn.net.ibingo.core.service.OfferStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferStatisticsServiceImpl implements OfferStatisticsService {

    @Autowired
    private OfferStatisticsMapper offerStatisticsMapper;

    @Override
    public PaginationList<OfferStatistics> list(OfferStatisticsQueryBean queryBean) {
        queryBean.setPageSize(50);
        List<Integer> totalCount= offerStatisticsMapper.selectCountByQueryBean(queryBean);
        List<OfferStatistics> list = offerStatisticsMapper.selectByQueryBean(queryBean);
        SimplePaginatedList<OfferStatistics> pList = new SimplePaginatedList<OfferStatistics>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount.size());
        return pList;
    }


}
