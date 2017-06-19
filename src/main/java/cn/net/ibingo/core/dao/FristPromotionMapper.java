package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.FristPromotion;
import cn.net.ibingo.core.query.FristPromotionQueryBean;

public interface FristPromotionMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(FristPromotion record);

    int insertSelective(FristPromotion record);

    FristPromotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FristPromotion record);

    int updateByPrimaryKey(FristPromotion record);
    
    int selectCountByQueryBean(FristPromotionQueryBean queryBean);
    
    List<FristPromotion> selectByQueryBean(FristPromotionQueryBean queryBean);
    
    String selectMaxCode();
    
    int deleteByPid(Integer pid);
}