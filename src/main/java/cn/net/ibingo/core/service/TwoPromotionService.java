package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.TwoPromotion;
import cn.net.ibingo.core.query.TwoPromotionQueryBean;

public interface TwoPromotionService {
	
	public PaginationList<TwoPromotion> list(TwoPromotionQueryBean queryBean);
	
	public boolean save(Integer pid);

	public boolean delete(Integer id);
}
