package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.VoluumNotify;
import cn.net.ibingo.core.query.VoluumNotifyQueryBean;

import java.util.Map;

public interface VoluumNotifyService {


	public int insertNotify(VoluumNotify noluumNotify);

	public PaginationList<VoluumNotify> list(VoluumNotifyQueryBean vb);

	public int updateDataType(String clickId);

	public int delete(int id);

	public boolean selectCountByClickId(String clickId);
}
