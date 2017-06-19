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
import cn.net.ibingo.core.dao.MncMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.dao.ResourcesMccMapper;
import cn.net.ibingo.core.dao.ResourcesMncMapper;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.model.ResourcesMnc;
import cn.net.ibingo.core.query.MccQueryBean;
import cn.net.ibingo.core.service.MccService;

@Service
public class MccServiceImpl implements MccService {
	
	@Autowired
	private MccMapper mccMapper;
	
	@Autowired
	private MncMapper mncMapper;
	
	@Autowired
	private ResourcesMccMapper resourcesMccMapper;
	
	@Autowired
	private ResourcesMncMapper resourcesMncMapper;
	
	@Autowired
	private IpAreaMapper ipAreaMapper;
	
	@Autowired
	private ResourcesMapper resourcesMapper;

	@Override
	public PaginationList<Mcc> list(MccQueryBean queryBean) {
		Integer totalCount = mccMapper.selectCountByQueryBean(queryBean);
		List<Mcc> list = mccMapper.selectByQueryBean(queryBean);
		List<Mcc> listMcc = new ArrayList<Mcc>();
		String mncGroup = "";
		if(list != null){
			for (Mcc mcc : list) {
				mncGroup = "";
				List<Mnc> listMnc = mncMapper.selectByPid(mcc.getId());
				if(listMnc != null){
					for (Mnc mnc : listMnc) {
						mncGroup += mnc.getOperator()+",";
					}
				}
				mcc.setMncGroup(mncGroup.substring(0,mncGroup.length()-1));
				listMcc.add(mcc);
			}
		}
		// 根据国家名称集合进行排序
		Collections.sort(listMcc, new Comparator<Mcc>(){
			@Override
			public int compare(Mcc m1, Mcc m2) {
		        return m1.getCountry().compareTo(m2.getCountry()); 
			}
		});
		SimplePaginatedList<Mcc> pList = new SimplePaginatedList<Mcc>(listMcc, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public Mcc get(Integer id) {
		return mccMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(Mcc mcc) {
		mcc.setCode(mcc.getCountryCode());
		mcc.setIso(mcc.getIso().toUpperCase());
		mcc.setModifyDate(new Date());
		if(mcc.getId() != null){
			mccMapper.updateByPrimaryKeySelective(mcc);
			this.updateMnc(mcc);
		}else{
			mcc.setCreateDate(new Date());
			mccMapper.insertSelective(mcc);
			this.addMnc(mcc);
		}
		return true;
	}
	
	public void addMnc(Mcc mcc){
		int num = mcc.getOperator().split(",").length;
		for(int i = 0; i < num; i++){
			Mnc mnc = new Mnc();
			mnc.setOperator(mcc.getOperator().split(",")[i]);
			mnc.setCode(mcc.getOperatorCode().split(",")[i]);
			mnc.setPid(mcc.getId());
			mnc.setDescription(mcc.getDescription().split(",",-1)[i]);
			mnc.setCreateDate(new Date());
			mnc.setModifyDate(new Date());
			mncMapper.insertSelective(mnc);
		}
	}
	
	public void updateMnc(Mcc mcc){
		//广告国家运营商修改
		List<ResourcesMnc> listResourcesMcc = resourcesMncMapper.selectByMccId(mcc.getId());
		resourcesMncMapper.deleteByMccId(mcc.getId());
		mncMapper.deleteByPid(mcc.getId());
		int num = mcc.getOperator().split(",").length;
		for(int i = 0; i < num; i++){
			String operator = mcc.getOperator().split(",")[i];
			String operatorCode = mcc.getOperatorCode().split(",")[i];
	
			Mnc mnc = new Mnc();
			mnc.setOperator(operator);
			mnc.setCode(operatorCode);
			mnc.setPid(mcc.getId());
			mnc.setDescription(mcc.getDescription().split(",",-1)[i]);
			mnc.setCreateDate(new Date());
			mnc.setModifyDate(new Date());
			mncMapper.insertSelective(mnc);
				
			if(listResourcesMcc != null && listResourcesMcc.size() > 0){
				for (ResourcesMnc resourcesMnc : listResourcesMcc) {
				if(resourcesMnc.getCode().equals(operatorCode) || resourcesMnc.getOperator().equals(operator)){
					ResourcesMnc rm = new ResourcesMnc();
					rm.setPid(resourcesMnc.getPid());
					rm.setMncId(mnc.getId());
					resourcesMncMapper.insertSelective(rm);
					}
				}
			}
		}
	}
	
	@Override
	public boolean delete(Integer id) {
		mncMapper.deleteByPid(id);
		resourcesMncMapper.deleteByMccId(id);
		resourcesMccMapper.deleteByMccId(id);
		resourcesMapper.deleteByMccId(id);
		return mccMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public Boolean selectByMcc(Mcc mcc) {
		List<Mcc> list = new ArrayList<Mcc>();
		if(mcc.getId() != null){
			Mcc record = mccMapper.selectByPrimaryKey(mcc.getId());
			if((mcc.getCountry() != null && mcc.getCountry().equals(record.getCountry())) 
					|| (mcc.getCode() != null && mcc.getCode().equals(record.getCode()))){
				return true;
			}else{
				list = mccMapper.selectByMcc(mcc);
			}
		}else{
			list = mccMapper.selectByMcc(mcc);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	//国家和运营商
	@Override
	public List<Mcc> selectAll() {
		List<Mcc> listMcc = mccMapper.selectAll();
		List<Mcc> list = new ArrayList<Mcc>();
		for (Mcc mcc : listMcc) {
			List<Mnc> listMnc = mncMapper.selectByCountry(mcc.getCountry());
			mcc.setListMnc(listMnc);
			list.add(mcc);
		}
		return list;
	}

	@Override
	public List<Mcc> selectAllName() {
		// TODO Auto-generated method stub
		return mccMapper.selectAll();
	}

}
