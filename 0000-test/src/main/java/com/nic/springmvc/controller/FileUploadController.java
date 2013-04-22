package com.nic.springmvc.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping("/file-upload")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView view = new ModelAndView("page1");
		LOGGER.debug("request: {}", request);
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("map.size: " + parameterMap.size());
		Iterator<Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
		while(iterator.hasNext()){
		    Entry<String, String[]> entry = iterator.next();
		    String key = entry.getKey();
		    String[] values = entry.getValue();
		    LOGGER.debug("key:{} \n values: {}", key, values);
		}
		return view;
	}
}
