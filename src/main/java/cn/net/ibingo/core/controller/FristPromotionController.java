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
import cn.net.ibingo.core.model.FristPromotion;
import cn.net.ibingo.core.query.FristPromotionQueryBean;
import cn.net.ibingo.core.service.FristPromotionService;

@Controller
@RequestMapping("/fristPromotion")
public class FristPromotionController extends BaseController{
	
	@Autowired
	private FristPromotionService fristPromotionService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,FristPromotionQueryBean queryBean, ModelMap modelMap){
		PaginationList<FristPromotion> pageDataList = fristPromotionService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/fristPromotion/list";
	}
	
	@RequestMapping(value = "/save")
	public String save(Integer pid, ModelMap modelMap,FristPromotionQueryBean queryBean) {
		fristPromotionService.save(pid);
		return "redirect:/fristPromotion/list?pid="+pid+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id, Integer pid,FristPromotionQueryBean queryBean) {
		fristPromotionService.delete(id);
		return "redirect:/fristPromotion/list?pid="+pid+"&currentPage="+
		queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
}
