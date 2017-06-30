package cn.net.ibingo.core.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Index;
//import cn.net.ibingo.core.model.NotifyAnalysis;
//import cn.net.ibingo.core.model.SubscribeSuccessAnalysis;
import cn.net.ibingo.core.service.AdvertisersService;
import cn.net.ibingo.core.service.FristChannelService;
//import cn.net.ibingo.core.service.NotifyAnalysisService;
import cn.net.ibingo.core.service.ResourcesMccService;
import cn.net.ibingo.core.service.ResourcesMncService;
import cn.net.ibingo.core.service.ResourcesService;
//import cn.net.ibingo.core.service.SubscribeSuccessAnalysisService;


@Controller
@RequestMapping("/welcome")
public class IndexController extends BaseController{
	
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private AdvertisersService advertisersServicel;
	
	@Autowired
	private ResourcesMccService resourcesMccService;
	
	@Autowired
	private ResourcesMncService resourcesMncService;
	
	@Autowired
	private FristChannelService fristChannelService;
	

	//@Autowired
	//private NotifyAnalysisService notifyAnalysisService;
	

	//@Autowired
	//private SubscribeSuccessAnalysisService SubscribeSuccessAnalysisService;
	
/*	@Autowired
	private AnalysisChannelService analysisChannelService;
	
	@Autowired
	private AnalysisAdvertisersService analysisAdvertisersService;*/
	
	@RequestMapping("/index")
	public String welcome(HttpSession session,ModelMap modelMap) {
		/*IndexQueryBean queryBean = new IndexQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		User user = (User) session.getAttribute(ConstantConfig.USER);
		//累计广告资源数
		int resourcesCount = 0;
		//累计广告主数
		int advertisersCount = 0;
		//累计渠道数
		int channelCount = 0;
		//累计访问数
		int uv = 0; 
		//累计订阅成功数
	    int ss = 0;
		//累计国家数
		int mccCount = 0;
		//累计运营商数
		int mncCount = 0;
		//昨日访问数
		int yesterdayUv = 0; 
		//昨日订阅成功数
		int yesterdaySs = 0;  
		//昨日预计收入
		double yesterdayPrice = 0; 
		
		//广告主
		if(user != null && user.getUserRole().equals(2)){
			ResourcesMcc mcc = new ResourcesMcc();
			mcc.setAdsId(user.getUserRoleId());
			mccCount = resourcesMccService.selectCount(mcc);
			
			ResourcesMnc mnc = new ResourcesMnc();
			mnc.setAdsId(user.getUserRoleId());
			mncCount = resourcesMncService.selectCount(mnc);
			
			Advertisers advertisers = advertisersServicel.get(user.getUserRoleId());
			if(advertisers != null){
				SubscribeSuccessAnalysis successAnalysis = new SubscribeSuccessAnalysis();
				successAnalysis.setAdvertisersname(advertisers.getName());
				successAnalysis.setDate(DateUtils.getYesterday());
				successAnalysis = SubscribeSuccessAnalysisService.selectSum(successAnalysis);
				if(successAnalysis != null){
					yesterdayUv = Integer.parseInt(successAnalysis.getCount().toString());
				}
				NotifyAnalysis notifyAnalysis = new NotifyAnalysis();
				notifyAnalysis.setAdvertisersname(advertisers.getName());
				notifyAnalysis.setDate(DateUtils.getYesterday());
				notifyAnalysis = notifyAnalysisService.selectSum(notifyAnalysis);
				if(notifyAnalysis != null){
					yesterdaySs = Integer.parseInt(notifyAnalysis.getCount().toString());
					yesterdayPrice = Double.parseDouble(notifyAnalysis.getPrice().toString());
				}
			}
		//一级渠道	
		}else if(user != null && user.getUserRole().equals(3)){
			FristChannel fristChannel = fristChannelService.get(user.getUserRoleId());
			if(fristChannel != null){
				SubscribeSuccessAnalysis successAnalysis = new SubscribeSuccessAnalysis();
				successAnalysis.setFirstchannelid(fristChannel.getCode());
				successAnalysis.setDate(DateUtils.getYesterday());
				successAnalysis = SubscribeSuccessAnalysisService.selectSum(successAnalysis);
				if(successAnalysis != null){
					yesterdayUv = Integer.parseInt(successAnalysis.getCount().toString());
				}
				NotifyAnalysis notifyAnalysis = new NotifyAnalysis();
				notifyAnalysis.setFirstchannelid(fristChannel.getCode());
				notifyAnalysis.setDate(DateUtils.getYesterday());
				notifyAnalysis = notifyAnalysisService.selectSum(notifyAnalysis);
				if(notifyAnalysis != null){
					yesterdaySs = Integer.parseInt(notifyAnalysis.getCount().toString());
					yesterdayPrice = Double.parseDouble(notifyAnalysis.getPrice().toString());
				}
			}
		//二级渠道
		}else if(user != null && user.getUserRole().equals(4)){
			TwoChannel twoChannel = twoChannelService.get(user.getUserRoleId());
			if(twoChannel != null){
				SubscribeSuccessAnalysis successAnalysis = new SubscribeSuccessAnalysis();
				successAnalysis.setSecchannelid(twoChannel.getCode());
				successAnalysis.setDate(DateUtils.getYesterday());
				successAnalysis = SubscribeSuccessAnalysisService.selectSum(successAnalysis);
				if(successAnalysis != null){
					yesterdayUv = Integer.parseInt(successAnalysis.getCount().toString());
				}
				NotifyAnalysis notifyAnalysis = new NotifyAnalysis();
				notifyAnalysis.setSecchannelid(twoChannel.getCode());
				notifyAnalysis.setDate(DateUtils.getYesterday());
				notifyAnalysis = notifyAnalysisService.selectSum(notifyAnalysis);
				if(notifyAnalysis != null){
					yesterdaySs = Integer.parseInt(notifyAnalysis.getCount().toString());
					yesterdayPrice = Double.parseDouble(notifyAnalysis.getPrice().toString());
				}
			}
		}else{
			mccCount = resourcesMccService.selectCount(null);
			mncCount = resourcesMncService.selectCount(null);
			
			SubscribeSuccessAnalysis successAnalysis = new SubscribeSuccessAnalysis();
			successAnalysis.setDate(DateUtils.getYesterday());
			successAnalysis = SubscribeSuccessAnalysisService.selectSum(successAnalysis);
			if(successAnalysis != null){
				yesterdayUv = Integer.parseInt(successAnalysis.getCount().toString());
			}
			NotifyAnalysis notifyAnalysis = new NotifyAnalysis();
			notifyAnalysis.setDate(DateUtils.getYesterday());
			notifyAnalysis = notifyAnalysisService.selectSum(notifyAnalysis);
			if(notifyAnalysis != null){
				yesterdaySs = Integer.parseInt(notifyAnalysis.getCount().toString());
				yesterdayPrice = Double.parseDouble(notifyAnalysis.getPrice().toString());
			}
			//累计广告资源数
			resourcesCount = resourcesService.selectCount();
			//累计广告主数
			advertisersCount = advertisersServicel.selectCount();
			//累计渠道数
			channelCount = fristChannelService.selectCount();
			//累计访问数
			SubscribeSuccessAnalysis s = SubscribeSuccessAnalysisService.selectSum(null);
			if(s != null){
				uv = Integer.parseInt(s.getCount().toString());
			}
			//累计订阅成功数
			NotifyAnalysis n =  notifyAnalysisService.selectSum(null); 
			if(n != null){
				ss = Integer.parseInt(n.getCount().toString());
			}
		}
		//封装
		Index index = new Index();
		index.setResourcesCount(resourcesCount);
		index.setAdvertisersCount(advertisersCount);
		index.setAdvertisersCount(advertisersCount);
		index.setMccCount(mccCount);
		index.setMncCount(mncCount);
		index.setChannelCount(channelCount);
		index.setYesterdayUv(yesterdayUv);
		index.setYesterdaySs(yesterdaySs);
		index.setYesterdayPrice(yesterdayPrice);
		index.setUv(uv);
		index.setSs(ss);*/

		Index index = new Index();
		modelMap.put(ConstantConfig.INDEX, index);
		return "index";
	}
}
