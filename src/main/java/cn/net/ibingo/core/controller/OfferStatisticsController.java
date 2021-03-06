package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.HttpUtil;
import cn.net.ibingo.core.model.*;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.OfferStatisticsQueryBean;
import cn.net.ibingo.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/offerStatistics")
public class OfferStatisticsController extends BaseController{
	private static Logger log = Logger.getLogger(String.valueOf(OfferStatisticsController.class));

	@Autowired
	private OfferStatisticsService offerStatisticsService;

	@Autowired
	private AdvertisersService advertisersService;

	@Autowired
	private ResourcesService resourcesService;

	@Autowired
	private MccService mccService;

	@Autowired
	private FristChannelService fristChannelService;


	@RequestMapping(value = "/list")
	public String list(HttpSession session, OfferStatisticsQueryBean queryBean, ModelMap modelMap){
		List<Mcc> mccList = mccService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTMCC,mccList);

		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		List<Resources> listResources = resourcesService.selectAll(null);

		modelMap.addAttribute(ConstantConfig.LISTRESOURCES,listResources);
		PaginationList<OfferStatistics> pageDataList = offerStatisticsService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/offerStatistics/list";
	}


}
