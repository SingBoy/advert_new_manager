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

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.query.MccQueryBean;
import cn.net.ibingo.core.service.MccService;
import cn.net.ibingo.core.service.MncSevice;

@Controller
@RequestMapping("/mcc")
public class MccController extends BaseController{
	
	@Autowired
	private MccService mccService;
	
	@Autowired
	private MncSevice mncSevice;
	
	@RequestMapping(value = "/list")
	public String list(MccQueryBean queryBean, ModelMap modelMap){
		PaginationList<Mcc> pageDataList = mccService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/mcc/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,MccQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/mcc/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,MccQueryBean queryBean) {
		Mcc mcc = mccService.get(id);
		modelMap.addAttribute(ConstantConfig.MCC,mcc);
		List<Mnc> listMnc = mncSevice.selectByPid2(mcc.getId());
		modelMap.addAttribute(ConstantConfig.LISTMNC,listMnc);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/mcc/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(Mcc mcc, ModelMap modelMap) throws UnsupportedEncodingException {
		mccService.saveOrUpdate(mcc);
		MccQueryBean queryBean = new MccQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "redirect:/mnc/list?keyword="+(java.net.URLEncoder.encode(mcc.getKeyword(),"UTF-8"))+"&currentPage="+
			mcc.getCurrentPage()+"&pageSize="+mcc.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,MccQueryBean queryBean) 
			throws UnsupportedEncodingException {
		mccService.delete(id);
		return "redirect:/mnc/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	

	@RequestMapping(value = "/doBean")
	public void doBean(Mcc mcc, HttpServletResponse response) {
		try {
			Boolean	b = mccService.selectByMcc(mcc);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
	}
}
