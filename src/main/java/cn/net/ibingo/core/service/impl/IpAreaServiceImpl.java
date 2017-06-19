package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.IpAreaMapper;
import cn.net.ibingo.core.dao.MccMapper;
import cn.net.ibingo.core.model.IpArea;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.query.IpAreaQueryBean;
import cn.net.ibingo.core.service.IpAreaService;

@Service
public class IpAreaServiceImpl implements IpAreaService {

	@Autowired
	private IpAreaMapper ipAreaMapper;
	
	@Autowired
	private MccMapper mccMapper;
	
	@Override
	public PaginationList<IpArea> list(IpAreaQueryBean queryBean) {
		queryBean.setPageSize(150);
		Integer totalCount = ipAreaMapper.selectCountByQueryBean(queryBean);
		List<IpArea> list = ipAreaMapper.selectByQueryBean(queryBean);
		// 根据运营商集合进行排序
		Collections.sort(list, new Comparator<IpArea>(){
			@Override	
			public int compare(IpArea i1, IpArea i2) {
				if(i1.getCountry().equals(i2.getCountry())){
					return i1.getOperator().compareTo(i2.getOperator());
				}else{
					return 0;
				}
			}
		});
		SimplePaginatedList<IpArea> pList = new SimplePaginatedList<IpArea>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public IpArea get(Integer id) {
		return ipAreaMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(IpArea ipArea) {
		if(ipArea.getId() != null){
			ipAreaMapper.deleteByPrimaryKey(ipArea.getId());
		}
		
		Mcc mcc = mccMapper.selectByPrimaryKey(Integer.valueOf(ipArea.getCountry()));
		int num = ipArea.getStart().split(",").length;
		for(int i = 0; i < num; i++){
			String start = ipArea.getStart().split(",")[i];
			String end = ipArea.getEnd().split(",")[i];
			IpArea ip = new IpArea();
			ip.setCountry(mcc.getCountry());
			ip.setIso(mcc.getIso().toUpperCase());
			ip.setOperator(ipArea.getOperator());
			ip.setStart(start);
			ip.setEnd(end);
			ip.setCreateDate(new Date());
			ip.setModifyDate(new Date());
			ipAreaMapper.insertSelective(ip);
		}
	}

	@Override
	public boolean delete(Integer id) {
		return ipAreaMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean selectByIpArea(IpArea ipArea) {
		List<IpArea> list = new ArrayList<IpArea>();
		if(ipArea.getId() != null){
			IpArea record = ipAreaMapper.selectByPrimaryKey(ipArea.getId());
			if(ipArea.getCountry().equals(record.getCountry()) && ipArea.getOperator().equals(record.getOperator())
				&& ipArea.getStart().equals(record.getStart()) && ipArea.getEnd().equals(record.getEnd())){
				return true;
			}else{
				list = ipAreaMapper.selectByIpArea(ipArea);
			}
		}else{
			list = ipAreaMapper.selectByIpArea(ipArea);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int selectByCO(IpArea ipArea) {
		List<IpArea> list = ipAreaMapper.selectByIpArea(ipArea);
		int id = 0;
		if(list != null && list.size() != 0){
			id = list.get(0).getId();
		}
		return id;
	}

	@Override
	public List<IpArea> selectByArea(IpArea ipArea) {
		return ipAreaMapper.selectByIpArea(ipArea);
	}

	@Override
	public void save(IpArea ipArea) {
		ipArea.setIso(ipArea.getIso().toUpperCase());
		ipArea.setCreateDate(new Date());
		ipArea.setModifyDate(new Date());
		ipAreaMapper.insertSelective(ipArea);
	}

}
