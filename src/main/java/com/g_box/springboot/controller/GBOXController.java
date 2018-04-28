package com.g_box.springboot.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class GBOXController {
 
    @RequestMapping("/main")
    public String g_box_main(HttpServletRequest request) throws Exception {
    	
    	//String path = request.getSession().getServletContext().getRealPath("upload"); 
    	//System.out.println(path+" "+new Date());
        return "G-BOX";
    }
    
    @RequestMapping("/")
    public String g_box(HttpServletRequest request) throws Exception {
    	
    	//String path = request.getSession().getServletContext().getRealPath("upload"); 
    	//System.out.println(path+" "+new Date());
        return "G-BOX";
    }
    
}
