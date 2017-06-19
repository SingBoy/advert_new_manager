package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.FristPromotion;
import cn.net.ibingo.core.query.FristPromotionQueryBean;

public interface FristPromotionService {
	
	public PaginationList<FristPromotion> list(FristPromotionQueryBean queryBean);
	
	public boolean save(Integer pid);

	public boolean delete(Integer id);
}
