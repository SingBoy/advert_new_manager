package cn.net.ibingo.core.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.AnalysisChannel;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.AnalysisChannelQueryBean;
import cn.net.ibingo.core.service.AnalysisChannelService;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.TwoChannelService;

@Controller
@RequestMapping("/analysisChannel")
public class AnalysisChannelController extends BaseController{
	
	@Autowired
	private AnalysisChannelService analysisChannelService;
	
	@Autowired
	private FristChannelService fristChannelService;
	
	@Autowired
	private TwoChannelService twoChannelService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,AnalysisChannelQueryBean queryBean, ModelMap modelMap){
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
		//二级渠道
		List<TwoChannel> listTwoChannel = new ArrayList<TwoChannel>();
		if(StringUtils.isNotBlank(queryBean.getFristCode())){
			FristChannel fristChannel = fristChannelService.selectByCode(queryBean.getFristCode());
			if(fristChannel != null){
				listTwoChannel = twoChannelService.selectByPid(fristChannel.getId());
			}
		}
		modelMap.addAttribute(ConstantConfig.LISTTWOCHANNEL,listTwoChannel);
		
		PaginationList<AnalysisChannel> pageDataList = analysisChannelService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		
		//链接ID
		List<AnalysisChannel> listLinkCodes = analysisChannelService.selectLinkCode();
		modelMap.addAttribute(ConstantConfig.LISTLINKCODES,listLinkCodes);
		return "pager/analysisChannel/list";
	}

}
