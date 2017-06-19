package cn.net.ibingo.core.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Default;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.DefaultQueryBean;
import cn.net.ibingo.core.service.DefaultService;

@Controller
@RequestMapping("/default")
public class DefaultController extends BaseController{
	
	@Autowired
	private DefaultService defaultService;
	
	@RequestMapping(value = "/list")
	public String list(DefaultQueryBean queryBean, ModelMap modelMap){
		PaginationList<Default> pageDataList = defaultService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/default/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,DefaultQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/default/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,DefaultQueryBean queryBean) {
		Default record = defaultService.get(id);
		modelMap.put(ConstantConfig.DEFAULT, record);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/default/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(Default record, ModelMap modelMap) throws UnsupportedEncodingException {
		defaultService.saveOrUpdate(record);
		AdvertisersQueryBean queryBean = new AdvertisersQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "redirect:/default/list?keyword="+(java.net.URLEncoder.encode(record.getKeyword(),"UTF-8"))+
				"&status="+(record.getStatusBean()==null?"":record.getStatusBean())+
				"&currentPage="+record.getCurrentPage()+"&pageSize="+record.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,DefaultQueryBean queryBean) 
			throws UnsupportedEncodingException {
		defaultService.delete(id);
		return "redirect:/default/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+
				"&status="+(queryBean.getStatus()==null?"":queryBean.getStatus())+
				"&currentPage="+queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/enable/{id}")
	public String enable(@PathVariable Integer id, ModelMap modelMap,DefaultQueryBean queryBean) 
			throws UnsupportedEncodingException {
		defaultService.enable(id);
		return "redirect:/default/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+
				"&status="+(queryBean.getStatus()==null?"":queryBean.getStatus())+
				"&currentPage="+queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
}
