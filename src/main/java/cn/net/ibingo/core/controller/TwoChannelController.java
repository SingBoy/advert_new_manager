package cn.net.ibingo.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.TwoChannelQueryBean;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.TwoChannelService;

@Controller
@RequestMapping("/twoChannel")
public class TwoChannelController extends BaseController{

	@Autowired
	private TwoChannelService twoChannelService;
	
	@Autowired
	private FristChannelService fristChannelService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,TwoChannelQueryBean queryBean, ModelMap modelMap){		
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		if(user.getUserRole().equals(3)){
			queryBean.setPid(user.getUserRoleId());
			PaginationList<TwoChannel> pageDataList = twoChannelService.list(queryBean);
			modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
			modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
			return "pager/twoChannel/list";
		}else if(user.getUserRole().equals(4)){
			return "redirect:/twoPromotion/list?pid="+user.getUserRoleId();
		}else{
			//父渠道
			List<FristChannel> listFristChannel = fristChannelService.selectAll();
			modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
			
			PaginationList<TwoChannel> pageDataList = twoChannelService.list(queryBean);
			modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
			modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
			return "pager/twoChannel/list";
		}
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,TwoChannelQueryBean queryBean) {
		List<FristChannel> listFristChannel = fristChannelService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/twoChannel/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,TwoChannelQueryBean queryBean) {
		TwoChannel twoChannel = twoChannelService.get(id);
		modelMap.addAttribute(ConstantConfig.TWOCHANNEL,twoChannel);
		//父渠道
		List<FristChannel> listFristChannel = fristChannelService.selectAll();
		modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/twoChannel/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(HttpSession session,TwoChannel twoChannel, ModelMap modelMap) 
			throws UnsupportedEncodingException {
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		if(user.getUserRole().equals(3)){
			twoChannel.setPid(user.getUserRoleId());
		}
		twoChannelService.saveOrUpdate(twoChannel);
		TwoChannelQueryBean queryBean = new TwoChannelQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "redirect:/twoChannel/list?keyword="+(java.net.URLEncoder.encode(twoChannel.getKeyword(),"UTF-8"))
				+"&pid="+(twoChannel.getPidBean()==null?"":twoChannel.getPidBean())
				+"&currentPage="+twoChannel.getCurrentPage()+"&pageSize="+twoChannel.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,TwoChannelQueryBean queryBean) 
			throws UnsupportedEncodingException {
		twoChannelService.delete(id);
		return "redirect:/twoChannel/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))
				+"&pid="+(queryBean.getPid()==null?"":queryBean.getPid())
				+"&currentPage="+queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/doBean")
	public void doBean(TwoChannel twoChannel, HttpServletResponse response) {
		try {
			Boolean	b = twoChannelService.selectByName(twoChannel);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/byFristCode")
	public List<TwoChannel> byFristCode(String fristCode, HttpServletResponse response) {
		FristChannel fristChannel = fristChannelService.selectByCode(fristCode);
		List<TwoChannel> listTwoChannel = new ArrayList<TwoChannel>();
		if(fristChannel != null){
			listTwoChannel = twoChannelService.selectByPid(fristChannel.getId());
		}
		return listTwoChannel;
	}

}
