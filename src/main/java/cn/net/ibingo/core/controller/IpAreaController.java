package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.core.model.IpArea;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.query.IpAreaQueryBean;
import cn.net.ibingo.core.service.IpAreaService;
import cn.net.ibingo.core.service.MccService;
import cn.net.ibingo.core.service.MncSevice;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/ipArea")
public class IpAreaController extends BaseController{

	private static Logger log = Logger.getLogger(String.valueOf(IpAreaController.class));
	@Autowired
	private IpAreaService ipAreaService;

	@Autowired
	private MccService mccService;

	@Autowired
	private MncSevice mncSevice;


	@RequestMapping(value = "/list")
	public String list(IpAreaQueryBean queryBean, ModelMap modelMap){
		PaginationList<IpArea> pageDataList = ipAreaService.list(queryBean);
		modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/ipArea/list";
	}

	@RequestMapping(value = "/doSave")
	public String doSave(ModelMap modelMap,IpAreaQueryBean queryBean) {
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		List<Mcc> mccs = mccService.selectAllName();
		modelMap.addAttribute("mccs",mccs);
		List<String> mncs= mncSevice.selectAllName(null);
		modelMap.addAttribute("mncs",mncs);
		return "pager/ipArea/form";
	}

	@RequestMapping(value = "/doUpdate/{id}")
	public String doUpdate(@PathVariable Integer id, ModelMap modelMap,IpAreaQueryBean queryBean) {
		IpArea ipArea = ipAreaService.get(id);
		IpArea i = new IpArea();
		i.setCountry(ipArea.getCountry());
		i.setOperator(ipArea.getOperator());
		List<IpArea> list = ipAreaService.selectByArea(i);
		ipArea.setList(list);
		modelMap.addAttribute(ConstantConfig.IPAREA,ipArea);

		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		List<Mcc> mccs = mccService.selectAllName();
		modelMap.addAttribute("mccs",mccs);
		List<String> mncs= mncSevice.selectAllName(null);
		modelMap.addAttribute("mncs",mncs);
		return "pager/ipArea/form";
	}

	@RequestMapping(value = "/doImport")
	public String doImport(ModelMap modelMap) {
		IpAreaQueryBean queryBean = new IpAreaQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "pager/ipArea/import";
	}

	@RequestMapping(value = {"/save", "/update"})
	public String save(IpArea ipArea, ModelMap modelMap) throws UnsupportedEncodingException {
		ipAreaService.saveOrUpdate(ipArea);
		IpAreaQueryBean queryBean = new IpAreaQueryBean();
		modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
		return "redirect:/ipArea/list?country="+(java.net.URLEncoder.encode(ipArea.getCountryBean(),"UTF-8"))+
				"&operator="+(java.net.URLEncoder.encode(ipArea.getOperatorBean(),"UTF-8"))+
				"&currentPage="+ipArea.getCurrentPage()+"&pageSize="+ipArea.getPageSize();
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,IpAreaQueryBean queryBean) throws UnsupportedEncodingException {
		ipAreaService.delete(id);
		return "redirect:/ipArea/list?country="+(java.net.URLEncoder.encode(queryBean.getCountry(),"UTF-8"))+
				"&operator="+(java.net.URLEncoder.encode(queryBean.getOperator(),"UTF-8"))+
				"&currentPage="+queryBean.getCurrentPage()+"&pageSize="+queryBean.getPageSize();
	}

	@RequestMapping(value = "/doBean")
	public void doBean(IpArea ipArea, HttpServletResponse response) {
		try {
			Boolean	b = ipAreaService.selectByIpArea(ipArea);
			response.getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/doUpload")
	public void doUpload(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		String path = request.getSession().getServletContext().getRealPath("/upload/ipArea.xlsx");
		File file = new File(path);// path是根据日志路径和文件名拼接出来的
		String filename = file.getName();// 获取日志文件名称
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			os.write(buffer);// 输出文件
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/import")
	public String importRecord(@RequestParam(value = "fileName",required = false) MultipartFile file,
							   HttpServletRequest request,HttpServletResponse response) {
		try {
			InputStream input  = file.getInputStream();
			@SuppressWarnings("resource")
			Workbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
			//遍历每一行
			for (int r = 1; r < rowCount; r++) {
				IpArea ipArea = new IpArea();
				Row row = sheet.getRow(r);
				Cell c1 = row.getCell(0);
				ipArea.setCountry(c1.getStringCellValue());
				Cell c2 = row.getCell(1);
				ipArea.setIso(c2.getStringCellValue());
				Cell c3 = row.getCell(2);
				ipArea.setOperator(c3.getStringCellValue());
				Cell c4 = row.getCell(3);
				ipArea.setStart(c4.getStringCellValue());
				Cell c5 = row.getCell(4);
				ipArea.setEnd(c5.getStringCellValue());
				if(StringUtils.isNotBlank(ipArea.getCountry()) && StringUtils.isNotBlank(ipArea.getOperator())
						&& StringUtils.isNotBlank(ipArea.getStart()) && StringUtils.isNotBlank(ipArea.getEnd())
						&& StringUtils.isNotBlank(ipArea.getIso())){
					List<IpArea> listIpArea = ipAreaService.selectByArea(ipArea);
					if(listIpArea.size() == 0){
						ipAreaService.save(ipArea);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "redirect:/ipArea/list";
	}

}
