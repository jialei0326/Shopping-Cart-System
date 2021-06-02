package com.sam.mum.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.sam.mum.model.User;
import com.sam.mum.service.OrderService;
import com.sam.mum.service.impl.HistorySearchServiceImpl;


import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/reports")

public class SearchHistoryController {
	
	@Autowired
	OrderService orderService;
	@Autowired
    HistorySearchServiceImpl service;
	
	@GetMapping("/{format}")
	@ResponseBody

	private String exportReport(@PathVariable String format) throws FileNotFoundException, JRException {
		
        
//		File file = new File("C:\\Users\\atakl\\Downloads\\Product-Vendor copy\\Product-Vendor copy\\src\\main\\resources\\static\\pdf\\searchHistory.pdf");
//    	ResponseBuilder  response = Response.ok((Object)file);
//		response.header("Content-Dispostion", "attachment; filename=DisplayName-demoFile.pdf");
//		
//		return "searchHistory";
		return service.exportReport(format);
//		return response.build();
		  
	}
	
	@GetMapping
    @Path("/download")
    @Produces("Application/pdf")
    public Response downloadFileWithGet() throws FileNotFoundException {
        System.out.println("Download file ");
        File fileDownload = ResourceUtils.getFile("classpath:templates/static/pdf/searchHistory.pdf");
        ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=DisplayName-demoFile.pdf");
        return response.build();
    }
//	@RequestMapping(value = "/pdfFile", method = RequestMethod.POST, produces = {"application/pdf"})
//	@ResponseBody
//	public FileSystemResource getFile(@ModelAttribute PdfPTable pdfFile) {
//		
//	    com.itextpdf.text.pdf.PdfPTable pdfFileGenerator = new com.itextpdf.text.pdf.PdfPTable();
//	    File file = pdfFileGenerator.generatePdf(pdfFile);
//	    return new FileSystemResource(file);
//	}

	@GetMapping("/user")
	@ResponseBody
	public List<User> getUser() throws FileNotFoundException, JRException {
		
		return service.getUserList();

//		return service.exportReportUser();
	}
	


}
