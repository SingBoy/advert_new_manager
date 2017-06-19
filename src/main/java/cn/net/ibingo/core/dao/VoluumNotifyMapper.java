package cn.net.ibingo.core.dao;

  import cn.net.ibingo.common.pagination.model.PaginationList;
  import cn.net.ibingo.core.model.VoluumNotify;
import cn.net.ibingo.core.query.VoluumNotifyQueryBean;

import java.util.List;

public interface VoluumNotifyMapper {
	

    int insertSelective(VoluumNotify record);

    List<VoluumNotify> selectByQueryBean(VoluumNotifyQueryBean queryBean);

    int selectCountByQueryBean(VoluumNotifyQueryBean queryBean);

    int updateDateType(String clickId);

    int delete(int id);
}