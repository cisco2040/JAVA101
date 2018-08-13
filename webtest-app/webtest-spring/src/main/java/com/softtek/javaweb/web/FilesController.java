package com.softtek.javaweb.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.service.FilesService;

@Controller
@RequestMapping("/files")
public class FilesController {
	
	static final String UPLOAD_PATH = "uploads";
	static final String EDIT_FORM = "files/edit";
	static final String LIST_FORM = "files/list";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	private FilesService filesService;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String filesListController (Model model, HttpSession session) {

		LOGGER.info("getRealPath: {}", servletContext.getRealPath(FilesController.UPLOAD_PATH));
		LOGGER.info("getContextPath: {}", servletContext.getContextPath());
		LOGGER.info("getContextPath: {}", servletContext.getAttributeNames());
		

		model.addAttribute("files", filesService.getAllFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH)));
		return FilesController.LIST_FORM;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "home")
	public String filesHomeController () {
		return "redirect:/";
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String filesNewController () {
		return FilesController.EDIT_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public String filesCancelController (Model model, HttpSession session) {
		model.addAttribute("files", filesService.getAllFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH)));
		return FilesController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "upload")
	public String filesUploadController (@RequestParam CommonsMultipartFile file, Model model) {
		// use context instead of HttpSession
		ResponseStatus responseStatus = filesService.uploadFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH), file);
		
		if (responseStatus.isValid()) {
			model.addAttribute("files", filesService.getAllFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH)));
			return FilesController.LIST_FORM;	
		} else {
			model.addAttribute("frmValMsgs", responseStatus.getServiceMsg());
			return FilesController.EDIT_FORM;			
		}		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"delete", "filename"})
	public String filesDeleteController (@RequestParam String filename, Model model) {
		ResponseStatus responseStatus = filesService.deleteFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH), filename);
		
		if (responseStatus.isValid()) {
			model.addAttribute("files", filesService.getAllFiles(servletContext.getRealPath(FilesController.UPLOAD_PATH)));
			return FilesController.LIST_FORM;	
		} else {
			model.addAttribute("frmValMsgs", responseStatus.getServiceMsg());
			return FilesController.EDIT_FORM;			
		}		
	}
}
