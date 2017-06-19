package cn.net.ibingo.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.TimezoneCountry;
import cn.net.ibingo.core.query.TimezoneCountryQueryBean;
import cn.net.ibingo.core.service.MccService;
import cn.net.ibingo.core.service.TimezoneCountryService;

@Controller
@RequestMapping("/timezoneCountry")
public class TimezoneCountryController {
	
	@Autowired
	private TimezoneCountryService timezoneCountryService;
	
	@Autowired
	private MccService mccService;
	
	@RequestMapping(value = "/list")
	public String list(TimezoneCountryQueryBean queryBean, ModelMap modelMap){
		PaginationList<TimezoneCountry> pageDataList = timezoneCountryService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/timezoneCountry/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,TimezoneCountryQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		List<Mcc> mccs = mccService.selectAllName();
		modelMap.addAttribute("mccs",mccs);
		return "pager/timezoneCountry/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,TimezoneCountryQueryBean queryBean) {
		TimezoneCountry timezoneCountry = timezoneCountryService.get(id);
		modelMap.put(ConstantConfig.TIMEZONECOUNTRY, timezoneCountry);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		List<Mcc> mccs = mccService.selectAllName();
		modelMap.addAttribute("mccs",mccs);
		return "pager/timezoneCountry/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(TimezoneCountry timezoneCountry, ModelMap modelMap) 
			throws UnsupportedEncodingException {
		timezoneCountryService.saveOrUpdate(timezoneCountry);
		return "redirect:/timezoneCountry/list?keyword="+(java.net.URLEncoder.encode(timezoneCountry.getKeyword(),"UTF-8"))+"&currentPage="+
		timezoneCountry.getCurrentPage()+"&pageSize="+timezoneCountry.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,TimezoneCountryQueryBean queryBean) 
			throws UnsupportedEncodingException {
		timezoneCountryService.delete(id);
		return "redirect:/timezoneCountry/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/doIso")
	public void doBean(TimezoneCountry timezoneCountry, HttpServletResponse response) {
		try {
			Boolean	b = timezoneCountryService.selectByISO(timezoneCountry);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
