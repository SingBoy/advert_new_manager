package cn.net.ibingo.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.net.ibingo.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.query.ResourcesQueryBean;

@Controller
@RequestMapping("/resources")
public class ResourcesController extends BaseController{
	
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private MccService mccService;
	
	@Autowired
	private MncSevice mncSevice;
	
	@Autowired
	private AdvertisersService advertisersService;
	
	@Autowired
	private VoluumOfferService voluumOfferServiceImpl;

	@Autowired
	private VoluumAffiliateNetworkService voluumAffiliateNetworkServiceImpl;
	
	@RequestMapping(value = "/list")
	public String list(ResourcesQueryBean queryBean, ModelMap modelMap){
		PaginationList<Resources> pageDataList = resourcesService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		//国家
		List<Mcc> listMcc = mccService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTMCC,listMcc);
		//运营商
		List<Mnc> listMnc = new ArrayList<Mnc>();
		if(queryBean.getCountry() != null){
			listMnc = mncSevice.selectByCountry(queryBean.getCountry());
		}
		modelMap.addAttribute(ConstantConfig.LISTMNC,listMnc);
		//广告主
		List<Advertisers> listAdvertisers = advertisersService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
		return "pager/resources/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,ResourcesQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		//国家和运营商
		List<Mcc> listMcc = mccService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTMCC,listMcc);
		//广告主
		List<Advertisers> listAdvertisers = advertisersService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
		return "pager/resources/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,ResourcesQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		Resources resources = resourcesService.get(id);
		modelMap.addAttribute(ConstantConfig.RESOURCES,resources);
		//国家和运营商
		List<Mcc> listMcc = mccService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTMCC,listMcc);
		//广告主
		List<Advertisers> listAdvertisers = advertisersService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
		return "pager/resources/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(Resources resources, ModelMap modelMap,
			@RequestParam(value = "flie",required = false)MultipartFile flie) 
					throws UnsupportedEncodingException {
		if(flie != null){
			try {
				// 文件原名称
				String fileName=flie.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//自定义文件名称
				String trueFileName=String.valueOf(System.currentTimeMillis())+suffix;
				String filePath = ConstantConfig.IMGPATH + "/" + trueFileName;
				flie.transferTo(new File(filePath));
				resources.setImageUrl(fileName);
				resources.setDownloadUrl(filePath);
			} catch (Exception e) {
				System.out.println("图片上传失败，信息："+e.getMessage());
			}
		}
		resourcesService.saveOrUpdate(resources);
		ResourcesQueryBean queryBean = new ResourcesQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		
		return "redirect:/resources/list?keyword="+(java.net.URLEncoder.encode(resources.getKeyword(),"UTF-8"))+"&country="+
		(java.net.URLEncoder.encode(resources.getCountryBean(),"UTF-8"))+"&operator="+
		(java.net.URLEncoder.encode(resources.getOperatorBean(),"UTF-8"))+
		"&adsId="+(resources.getAdsIdBean()==null?"":resources.getAdsIdBean())+
		"&status="+(resources.getStatusBean()==null?"":resources.getStatusBean())+"&currentPage="+resources.getCurrentPage()+
		"&pageSize="+resources.getPageSize();
	}
	
	@RequestMapping(value = "/deleImg") 
	public void deleImg(Integer id, HttpServletResponse response){
		try {
			Resources resources = resourcesService.get(id);
			File file = new File(resources.getDownloadUrl());
			file.delete();
			resources.setImageUrl("");
			resources.setDownloadUrl("");
			resourcesService.update(resources);
		} catch (Exception e) {
			System.out.println("图片删除失败，信息："+e.getMessage());
		}

	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,ResourcesQueryBean queryBean) 
			throws UnsupportedEncodingException {
		resourcesService.delete(id);
		
		return "redirect:/resources/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&country="+
			(java.net.URLEncoder.encode(queryBean.getCountry(),"UTF-8"))+
			"&adsId="+(queryBean.getAdsId()==null?"":queryBean.getAdsId())+
			"&currentPage="+queryBean.getCurrentPage()+
			"&pageSize="+queryBean.getPageSize();
	}
	
	@ResponseBody
	@RequestMapping(value = "/byAdsId")
	public List<Resources> byAdsId(Integer adsId, HttpServletResponse response) {
		List<Resources> listResources = resourcesService.selectByAdsId(adsId);
		return listResources;
	}
	
	@RequestMapping(value = "/doBean")
	public void doBean(Resources resources, HttpServletResponse response) {
		try {
			Boolean	b = resourcesService.selectByResources(resources);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/syncVoluumOffer")
	public String syncVoluumOffer(HttpServletResponse response)throws UnsupportedEncodingException {
		try {
			//同步资源之前 先同步广告主
			voluumAffiliateNetworkServiceImpl.getAffiliateNetworksAndInsert();
			voluumOfferServiceImpl.getOffersAndInsert();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "redirect:/resources/list";
	}
	
}
