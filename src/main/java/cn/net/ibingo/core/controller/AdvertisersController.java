package cn.net.ibingo.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.net.ibingo.common.utils.HttpRespons;
import cn.net.ibingo.common.utils.HttpUtil;
import cn.net.ibingo.common.utils.Test;
import cn.net.ibingo.core.model.VoluumNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.service.AdvertisersService;
import cn.net.ibingo.core.service.VoluumAffiliateNetworkService;

@Controller
@RequestMapping("/advertisers")
public class AdvertisersController extends BaseController{
	private static Logger log = Logger.getLogger(String.valueOf(AdvertisersController.class));

	@Autowired
	private AdvertisersService advertisersService;
	
	@Autowired
	private VoluumAffiliateNetworkService voluumAffiliateNetworkServiceImpl;

	@RequestMapping(value = "/list")
	public String list(AdvertisersQueryBean queryBean, ModelMap modelMap){
		PaginationList<Advertisers> pageDataList = advertisersService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/advertisers/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,AdvertisersQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/advertisers/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,AdvertisersQueryBean queryBean) {
		Advertisers advertisers = advertisersService.get(id);
		modelMap.put(ConstantConfig.ADVERTISERS, advertisers);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/advertisers/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(Advertisers advertisers, ModelMap modelMap) 
			throws UnsupportedEncodingException {
		advertisersService.saveOrUpdate(advertisers);
		return "redirect:/advertisers/list?keyword="+(java.net.URLEncoder.encode(advertisers.getKeyword(),"UTF-8"))+"&currentPage="+
		advertisers.getCurrentPage()+"&pageSize="+advertisers.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,AdvertisersQueryBean queryBean) 
			throws UnsupportedEncodingException {
		advertisersService.delete(id);
		return "redirect:/advertisers/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}

	@RequestMapping(value = "/doBean")
	public void doBean(Advertisers advertisers, HttpServletResponse response) {
		try {
			Boolean	b = advertisersService.selectByAdvertisers(advertisers);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/listAdvertisers")
	public List<Advertisers> listAdvertisers(){
		List<Advertisers> listAdvertisers = advertisersService.selectAll();
		return listAdvertisers;
	}
	
	@RequestMapping(value = "/syncVoluumAffil")
	public String syncVoluumAffil()  throws UnsupportedEncodingException {
		try {
			voluumAffiliateNetworkServiceImpl.getAffiliateNetworksAndInsert();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "redirect:/advertisers/list";
	}

	@RequestMapping(value = "/voluumRequest")
	public void voluumRequest(VoluumNotify notify,HttpServletRequest request)  throws UnsupportedEncodingException {
		try {
			String username = request.getParameter("offerName");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cid",notify.getClickId());
			HttpUtil httpUtil = new HttpUtil();
			httpUtil.sendHttpClientPost("http://t.nicegame.me/postback",params,null);


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}



}
