package cn.net.ibingo.core.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.NotifyAnalysis;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.NotifyAnalysisQueryBean;
import cn.net.ibingo.core.service.AdvertisersService;
import cn.net.ibingo.core.service.NotifyAnalysisService;
import cn.net.ibingo.core.service.ResourcesService;

@Controller
@RequestMapping("/notifyAnalysis")
public class NotifyAnalysisController extends BaseController{

	@Autowired
	private NotifyAnalysisService notifyAnalysisService;
	
	@Autowired
	private AdvertisersService advertisersService;
	
	@Autowired
	private ResourcesService resourcesService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,NotifyAnalysisQueryBean queryBean, ModelMap modelMap){
		//广告主
		List<Advertisers> listAdvertisers = advertisersService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		if(user.getUserRole().equals(2)){
			Advertisers ad = advertisersService.get(user.getUserRoleId());
			if(ad != null){
				queryBean.setAdvertisersName(ad.getName());
			}else{
				queryBean.setAdvertisersName("000");
			}
			//资源名称
			List<Resources> listResources = resourcesService.selectAll(user.getUserRoleId());
			modelMap.addAttribute(ConstantConfig.LISTRESOURCES,listResources);
		}else{
			//资源名称
			List<Resources> listResources = resourcesService.selectAll(null);
			modelMap.addAttribute(ConstantConfig.LISTRESOURCES,listResources);
		}
		PaginationList<NotifyAnalysis> pageDataList = notifyAnalysisService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/notifyAnalysis/list";
	}
	
}
