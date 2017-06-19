package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.MccMapper;
import cn.net.ibingo.core.dao.MncMapper;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.query.MncQueryBean;
import cn.net.ibingo.core.service.MncSevice;

@Service
public class MncSeviceImpl implements MncSevice {

	@Autowired
	private MncMapper mncMapper;
	
	@Autowired
	private MccMapper mccMapper;
	
	@Override
	public PaginationList<Mnc> list(MncQueryBean queryBean) {
		queryBean.setPageSize(150);
		Integer totalCount = mncMapper.selectCountByQueryBean(queryBean);
		List<Mnc> list = mncMapper.selectByQueryBean(queryBean);
		// 根据国家名称集合进行排序
		Collections.sort(list, new Comparator<Mnc>(){
			@Override
			public int compare(Mnc m1, Mnc m2) {
		        if(m1.getCountryCode().equals(m2.getCountryCode())){
		        	return m1.getOperator().compareTo(m2.getOperator());
		        }else{
		        	return 0; 
		        }
			}
		});
		SimplePaginatedList<Mnc> pList = new SimplePaginatedList<Mnc>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}
	
	@Override
	public List<Mnc> selectByPid(Integer pid) {
		List<Mnc> listMnc = new ArrayList<Mnc>();
		
		Mcc mcc = mccMapper.selectByPrimaryKey(pid);
		
		Mcc m = new Mcc();
		m.setCountry(mcc.getCountry());
		List<Mcc> list = mccMapper.selectByMcc(m);
		
		for (Mcc mc : list) {
			List<Mnc> listMn = mncMapper.selectByPid(mc.getId());
			listMnc.addAll(listMn);
		}
		
		return listMnc;
	}

	@Override
	public List<Mnc> selectByPid2(Integer pid) {
		Mcc mcc = mccMapper.selectByPrimaryKey(pid);
		List<Mnc> listMnc = mncMapper.selectByPid2(mcc.getId());
		return listMnc;
	}

	@Override
	public List<String> selectAllName(String countryName) {
		// TODO Auto-generated method stub
		Mcc mcc = new Mcc();
		if(countryName != null){
			mcc = mccMapper.selectByPrimaryKey(Integer.valueOf(countryName));
		}
		return mncMapper.selectNameByCountryName(mcc.getCountry());
	}

	@Override
	public List<Mnc> selectByCountry(String country) {
		// TODO Auto-generated method stub
		return mncMapper.selectByCountry(country);
	}

}
