package cn.net.ibingo.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.net.ibingo.common.utils.Test;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.service.DistributionRateService;
import cn.net.ibingo.core.service.ResourcesService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.FristChannelQueryBean;
import cn.net.ibingo.core.service.FristChannelService;

@Controller
@RequestMapping("/fristChannel")
public class FristChannelController extends BaseController{
	
	@Autowired
	private FristChannelService fristChannelService;

	@Autowired
	private ResourcesService resourcesService;

	@Autowired
	private DistributionRateService distributionRateService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,FristChannelQueryBean queryBean, ModelMap modelMap){
		PaginationList<FristChannel> pageDataList = fristChannelService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		if(user.getUserRole().equals(3)){
			return "redirect:/fristPromotion/list?pid="+user.getUserRoleId();
		}else{
			return "pager/fristChannel/list";
		}
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,FristChannelQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		//资源名称
		List<Resources> listResources = resourcesService.selectAll(null);
		modelMap.addAttribute(ConstantConfig.LISTRESOURCES,listResources);

		return "pager/fristChannel/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,FristChannelQueryBean queryBean) {
		FristChannel fristChannel = fristChannelService.get(id);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		modelMap.addAttribute(ConstantConfig.FRISTCHANNEL,fristChannel);
		//资源名称
		List<Resources> listResources = resourcesService.selectAll(null);
		List<DistributionRate> listDisRate =  distributionRateService.selectAll(fristChannel.getVoluumTrafficSourceId());
		Map<String,DistributionRate> rateMap = new HashMap<String,DistributionRate>();
		List<Resources> resourcesList = new ArrayList<Resources>();
		boolean flg = false;
		if(listResources != null && listResources.size()>0){
			if(listDisRate != null && listDisRate.size()>0){
				for(Resources resource : listResources) {
					if(!StringUtils.isEmpty(resource.getVoluumOfferId())) {
						flg = false;
						for (DistributionRate rate : listDisRate) {
							if (resource.getVoluumOfferId().equals(rate.getVoluumOfferId())) {
								resource.setSubscriptionRate(rate.getSubscriptionRate());
								resourcesList.add(resource);
								flg = true;
							}
						}
						if (!flg) {
							resourcesList.add(resource);
						}
					}
				}
			}else {
				for(Resources resource : listResources) {
					if(!StringUtils.isEmpty(resource.getVoluumOfferId())) {
						resourcesList.add(resource);
					}
				}
			}
		}
		modelMap.addAttribute(ConstantConfig.LISTRESOURCES,resourcesList);
		return "pager/fristChannel/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(FristChannel fristChannel, ModelMap modelMap) throws UnsupportedEncodingException {
		fristChannelService.saveOrUpdate(fristChannel);
		FristChannelQueryBean queryBean = new FristChannelQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "redirect:/fristChannel/list?keyword="+(java.net.URLEncoder.encode(fristChannel.getKeyword(),"UTF-8"))+"&currentPage="+
			fristChannel.getCurrentPage()+"&pageSize="+fristChannel.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,FristChannelQueryBean queryBean) throws UnsupportedEncodingException {
		fristChannelService.delete(id);
		return "redirect:/fristChannel/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/doBean")
	public void doBean(FristChannel fristChannel, HttpServletResponse response) {
		try {
			Boolean	b = fristChannelService.selectByName(fristChannel);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/listFristChannel")
	public List<FristChannel> listFristChannel(){
		List<FristChannel> listFristChannel = fristChannelService.selectAll();
		return listFristChannel;
	}

	@RequestMapping(value = "/test")
	public void test(ModelMap modelMap,FristChannelQueryBean queryBean) {
		final Test test =new Test();
		String [] strArray = {"efadf425-0425-4276-933e-91539abd32d7","8efbfa54-0f2e-4bd4-8369-fb1aa2532b69","ee3a6d4e-834c-4f45-babe-6655cf96bcd3"};
		String str = "";
		for(int i = 1;i < 5000;i++){
			if(i % 3 == 0){
				str = strArray[2];
			}else if(i % 2 == 0){
				str = strArray[1];
			}else{
				str = strArray[0];
			}
			final int count = i;
			final String str1 = str;
			new Thread(){
				public void run(){
					test.requestVoluum(0,str1);
				}
			}.start();
		}
	}
	@RequestMapping(value = "/channlOne")
	public void channlOne(ModelMap modelMap,FristChannelQueryBean queryBean) {
		final Test test =new Test();
		final String str = "efadf425-0425-4276-933e-91539abd32d7";
		for(int i = 1;i < 3000;i++){
			final int count = i;
			new Thread(){
				public void run(){
					test.requestVoluum(count,str);
				}
			}.start();
		}
	}
	@RequestMapping(value = "/channlTwo")
	public void channlTwo(ModelMap modelMap,FristChannelQueryBean queryBean) {
		final Test test =new Test();
		final String str = "8efbfa54-0f2e-4bd4-8369-fb1aa2532b69";
		for(int i = 1;i < 1000;i++){
			final int count = i;
			new Thread(){
				public void run(){
					test.requestVoluum(count,str);
				}
			}.start();
		}
	}
	@RequestMapping(value = "/channlThree")
	public void channlThree(ModelMap modelMap,FristChannelQueryBean queryBean) {
		final Test test =new Test();
		final String str = "ee3a6d4e-834c-4f45-babe-6655cf96bcd3";
		for(int i = 1;i < 3000;i++){
			final int count = i;
			new Thread(){
				public void run(){
					test.requestVoluum(count,str);
				}
			}.start();
		}
	}

}
