package cn.net.ibingo.core.dao;

  import cn.net.ibingo.common.pagination.model.PaginationList;
  import cn.net.ibingo.core.model.OfferStatistics;
  import cn.net.ibingo.core.model.VoluumNotify;
import cn.net.ibingo.core.query.VoluumNotifyQueryBean;
  import org.apache.ibatis.annotations.Param;

  import java.util.List;

public interface VoluumNotifyMapper {
	

    int insertSelective(VoluumNotify record);

    List<VoluumNotify> selectByQueryBean(VoluumNotifyQueryBean queryBean);

    int selectCountByQueryBean(VoluumNotifyQueryBean queryBean);

    int updateDataType(String clickId);

    int delete(int id);

    public int selectCountByClickId(String clickId);

    public List<VoluumNotify> selectOfferStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate,@Param("country")String country);

    public List<VoluumNotify> selectTrafficSourceStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate,@Param("country")String country);

    public List<VoluumNotify> selectAdvertisersStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate,@Param("country")String country);
}