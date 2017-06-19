package cn.net.ibingo.core.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.query.MncQueryBean;
import cn.net.ibingo.core.service.MncSevice;

@Controller
@RequestMapping("/mnc")
public class MncController extends BaseController{
	
	@Autowired
	private MncSevice mncSevice;
	
	@RequestMapping(value = "/list")
	public String list(MncQueryBean queryBean, ModelMap modelMap){
		PaginationList<Mnc> pageDataList = mncSevice.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/mcc/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listMnc")
	public List<Mnc> listMnc(String country){
		List<Mnc> listMnc = new ArrayList<Mnc>();
		if(StringUtils.isNotBlank(country)){
			listMnc = mncSevice.selectByCountry(country);
		}
		return listMnc;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listMncName/{countryName}")
	public List<String> listMncByCountryName(@PathVariable String countryName){
		return mncSevice.selectAllName(countryName);
	}
}
