package cn.net.ibingo.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.NotifyChannelAnalysis;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.NotifyChannelAnalysisQueryBean;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.MccService;
import cn.net.ibingo.core.service.NotifyChannelAnalysisService;
import cn.net.ibingo.core.service.ResourcesService;
import cn.net.ibingo.core.service.TwoChannelService;

@Controller
@RequestMapping("/notifyChannel")
public class NotifyChannelAnalysisController extends BaseController{
	
	@Autowired
	private NotifyChannelAnalysisService notifyChannelService;
	
	@Autowired
	private FristChannelService fristChannelService;
	
	@Autowired
	private TwoChannelService twoChannelService;
	
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private MccService mccService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,NotifyChannelAnalysisQueryBean queryBean, ModelMap modelMap){
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		if(user.getUserRole().equals(3)){
			FristChannel fristChannel = fristChannelService.get(user.getUserRoleId());
			if(fristChannel == null){
				queryBean.setFristCode("0");
			}else{
				queryBean.setFristCode(fristChannel.getCode());
			}

		}else if(user.getUserRole().equals(4)){
			TwoChannel twoChannel = twoChannelService.get(user.getUserRoleId());
			if(twoChannel == null){
				queryBean.setTwoCode("0");
			}else{
				queryBean.setTwoCode(twoChannel.getCode());
			}
		}
		//一级渠道
		List<FristChannel> listFristChannel = fristChannelService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
		Map<String,Float> fristChannelMap = null;
		//当用户账号为以及渠道账号或二级渠道账号时，
		if(user.getUserRole().equals(3) || user.getUserRole().equals(4)){
			//将一级渠道的渠道编号和订阅量分配比例保存到Map中，以便于下面计算订阅量时乘以订阅量分配比例
			fristChannelMap = new HashMap<String,Float>();
			if(listFristChannel != null && listFristChannel.size() > 0){
				for(FristChannel fc : listFristChannel){
					fristChannelMap.put(fc.getCode(), fc.getSubscriptionRate());
				}
			}
		}
		
		//二级渠道
		List<TwoChannel> listTwoChannel = new ArrayList<TwoChannel>();
		if(StringUtils.isNotBlank(queryBean.getFristCode())){
			FristChannel fristChannel = fristChannelService.selectByCode(queryBean.getFristCode());
			if(fristChannel != null){
				listTwoChannel = twoChannelService.selectByPid(fristChannel.getId());
			}
		}
		modelMap.addAttribute(ConstantConfig.LISTTWOCHANNEL,listTwoChannel);
		
		PaginationList<NotifyChannelAnalysis> pageDataList = null ;
		//此处当用户账号为一级渠道账号和二级渠道账号时，广告数据的订阅数量显示时需要乘以渠道广告订阅量分配比例
		if(StringUtils.isNotBlank(queryBean.getTwoCode())){
			pageDataList = notifyChannelService.list(queryBean);
			countDyl(fristChannelMap,pageDataList);
		}else if(StringUtils.isNotBlank(queryBean.getFristCode())){
			pageDataList = notifyChannelService.list1(queryBean);
			countDyl(fristChannelMap,pageDataList);
		}else{
			pageDataList = notifyChannelService.list2(queryBean);
		}
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		
		//资源名称
		List<Resources> listResources = resourcesService.selectAll(null);
		modelMap.addAttribute(ConstantConfig.LISTRESOURCES,listResources);
		//国家
		List<Mcc> listMcc = mccService.selectAllName();
		modelMap.addAttribute(ConstantConfig.LISTMCC,listMcc);
		return "pager/notifyChannel/list";
	}
	
	/**
	 * 根据渠道订阅量的分配比例计算订阅量
	 */
	public void countDyl(Map<String,Float> fristChannelMap,PaginationList<NotifyChannelAnalysis> pageDataList){
		if(fristChannelMap != null && pageDataList != null && pageDataList.getTotalSize()>0){
			//获取查询出来的数据list
			List<NotifyChannelAnalysis> pageDatas  = pageDataList.getPageRecords();
			//遍历数据将订阅量与对应的订阅量分配比例相乘，得到结果
			for(NotifyChannelAnalysis nca : pageDatas){
				Float rate = fristChannelMap.get(nca.getFirstchannelid());
				//计算按比例分配之后的订阅量且向上取整
				Long count = (long)(Math.ceil(nca.getCount()*rate));
				nca.setCount(count);
			}
		}
		
	} 

}
