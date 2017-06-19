package cn.net.ibingo.core.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.TwoPromotion;
import cn.net.ibingo.core.query.TwoPromotionQueryBean;
import cn.net.ibingo.core.service.TwoPromotionService;

@Controller
@RequestMapping("/twoPromotion")
public class TwoPromotionController extends BaseController{
	
	@Autowired
	private TwoPromotionService twoPromotionService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,TwoPromotionQueryBean queryBean, ModelMap modelMap){
		PaginationList<TwoPromotion> pageDataList = twoPromotionService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/twoPromotion/list";
	}
	
	@RequestMapping(value = "/save")
	public String save(Integer pid, ModelMap modelMap,TwoPromotionQueryBean queryBean) {
		twoPromotionService.save(pid);
		return "redirect:/twoPromotion/list?pid="+pid+"&currentPage="+
		queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id, Integer pid,TwoPromotionQueryBean queryBean) {
		twoPromotionService.delete(id);
		return "redirect:/twoPromotion/list?pid="+pid+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
}
