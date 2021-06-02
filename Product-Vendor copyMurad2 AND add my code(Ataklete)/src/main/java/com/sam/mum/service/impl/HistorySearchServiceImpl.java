package com.sam.mum.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;



import com.sam.mum.model.Order;
import com.sam.mum.model.Role;
import com.sam.mum.model.User;
import com.sam.mum.repository.OrderRepository;
import com.sam.mum.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class HistorySearchServiceImpl {
 
	
	@Autowired
	OrderRepository hisRepo;
	
	@Autowired
	UserRepository hisUser;
	
	
	public List<Order> listHisReport() {

		return hisRepo.findAll();
	}

	public List<User> getUsers() {

		return getUserList();
	}

	public List<User> getUserList() {
	

		List<User> users =  hisUser.findAll();
							
		return users;
	}

	public String exportReport(String format) throws JRException, FileNotFoundException{
		List<Order> OrderList = this.listHisReport();

		String createdURL = System.getProperty("user.dir")  + "/src/main/resources/static/pdf";

		File file = ResourceUtils.getFile("classpath:templates/searchHistory.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(OrderList);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("createdBy","Ati");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper,parameters,ds);
		if(format.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,createdURL + "/searchHistory.pdf");

		}
		if(format.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,createdURL + "/searchHistory.html");

		}

		return "report generated in path "+createdURL;
	}
	public String exportReportUser() throws FileNotFoundException, JRException{
		List<User> userList =  this.getUserList();

		String createdURL = System.getProperty("user.dir")  + "/src/main/resources/static/pdf";

		File file = ResourceUtils.getFile("classpath:templates/users.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(userList);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("createdBy","Ati");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper,parameters,ds);

			JasperExportManager.exportReportToPdfFile(jasperPrint,createdURL + "/user.html");

		return "report generated in path "+createdURL;
	}

	
}