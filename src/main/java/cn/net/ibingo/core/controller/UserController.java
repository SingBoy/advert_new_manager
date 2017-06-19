package cn.net.ibingo.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.UserQueryBean;
import cn.net.ibingo.core.service.AdvertisersService;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.TwoChannelService;
import cn.net.ibingo.core.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdvertisersService advertisersService;
	
	@Autowired
	private FristChannelService fristChannelService;
	
	@Autowired
	private TwoChannelService twoChannelService;
	
	@RequestMapping(value = "/list")
	public String list(HttpSession session,UserQueryBean queryBean, ModelMap modelMap){
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		queryBean.setUserRole(user.getUserRole());
		if(user.getUserRole().equals(3)){
			List<Integer> userRoleIds = new ArrayList<Integer>();
			List<TwoChannel> listTwoChannel = twoChannelService.selectByPid(user.getUserRoleId());
			if(listTwoChannel != null){
				for (TwoChannel twoChannel : listTwoChannel) {
					userRoleIds.add(twoChannel.getId());
				}
				if(userRoleIds.size() > 0){
					queryBean.setUserRoleIds(userRoleIds);	
				} else {
					queryBean.setUserRoleIds(null);
				}
			} else {
				queryBean.setUserRoleIds(null);
			}
		}
		PaginationList<User> pageDataList = userService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/user/list";
	}
	
	@RequestMapping(value = "/doSave")
	public String doSave(HttpSession session,HttpServletRequest request, ModelMap modelMap,UserQueryBean queryBean) {
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		List<TwoChannel> listTwoChannel = new ArrayList<TwoChannel>();
		if(user.getUserRole().equals(3)){
			listTwoChannel = twoChannelService.selectByPid(user.getUserRoleId());
			modelMap.addAttribute(ConstantConfig.LISTTWOCHANNEL,listTwoChannel);
		}else{
			//广告主
			List<Advertisers> listAdvertisers = advertisersService.selectAll();
			modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
			//渠道
			List<FristChannel> listFristChannel = fristChannelService.selectAll();
			modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
		}
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/user/form";
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id,HttpSession session, ModelMap modelMap,UserQueryBean queryBean) {
		User user = new User();
		user = (User) session.getAttribute(ConstantConfig.USER);
		List<TwoChannel> listTwoChannel = new ArrayList<TwoChannel>();
		if(user.getUserRole().equals(3)){
			listTwoChannel = twoChannelService.selectByPid(user.getUserRoleId());
			modelMap.addAttribute(ConstantConfig.LISTTWOCHANNEL,listTwoChannel);
		}else{
			//广告主
			List<Advertisers> listAdvertisers = advertisersService.selectAll();
			modelMap.addAttribute(ConstantConfig.LISTADVERTISERS,listAdvertisers);
			//渠道
			List<FristChannel> listFristChannel = fristChannelService.selectAll();
			modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL,listFristChannel);
		}
		
		User u = userService.get(id);
		modelMap.addAttribute(ConstantConfig.USERBEAN,u);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/user/form";
	}
	
	@RequestMapping(value = {"/save", "/update"})
	public String save(HttpSession session,User user, ModelMap modelMap) 
		throws UnsupportedEncodingException {
		userService.saveOrUpdate(user);
		return "redirect:/user/list?keyword="+(java.net.URLEncoder.encode(user.getKeyword(),"UTF-8"))+"&currentPage="+
			user.getCurrentPage()+"&pageSize="+user.getPageSize();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,UserQueryBean queryBean) 
			throws UnsupportedEncodingException {
		userService.delete(id);
		return "redirect:/user/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/enable/{id}")
	public String enable(@PathVariable Integer id, ModelMap modelMap,UserQueryBean queryBean) 
			throws UnsupportedEncodingException {
		userService.enable(id);
		return "redirect:/user/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+"&currentPage="+
				queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}
	
	@RequestMapping(value = "/doUsername")
	public void doUsername(User user, HttpServletResponse response) {
		try {
			Boolean	b = userService.selectByUsername(user);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
