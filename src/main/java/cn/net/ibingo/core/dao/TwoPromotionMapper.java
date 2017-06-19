package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.TwoPromotion;
import cn.net.ibingo.core.query.TwoPromotionQueryBean;

public interface TwoPromotionMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TwoPromotion record);

    int insertSelective(TwoPromotion record);

    TwoPromotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TwoPromotion record);

    int updateByPrimaryKey(TwoPromotion record);
    
    int selectCountByQueryBean(TwoPromotionQueryBean queryBean);
    
    List<TwoPromotion> selectByQueryBean(TwoPromotionQueryBean queryBean);
    
    String selectMaxCode();
    
    int deleteByPid(Integer pid);
}