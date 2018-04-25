package com.g_box.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g_box.springboot.mapper.UserMapper;
import com.g_box.springboot.pojo.User;

@Controller   
public class LoginController {
	@Autowired UserMapper userMapper;

	@RequestMapping("/login")
	public String login(Model m) throws Exception {
    	return "login";
    }
	
	@RequestMapping("/login_handle")
	public String login_handle(User user,Model m) throws Exception{
		try{
			//System.out.println(user.getId()); ===ok
			String phonenumber = user.getPhonenumber();
			int id = userMapper.getId(phonenumber);
			User u = userMapper.get(id);
			int phonenumber_result = userMapper.checkexistphonenumber(phonenumber);
			
			if(user.getPhonenumber()==""|user.getPassword()=="") {
				m.addAttribute("check","账号或密码为空，请返回输入");
				return "login_jump";
			}
			else if(phonenumber.length()!=11) {
				m.addAttribute("check","账号格式错误，请重新填写");
				return "login_jump";
			}
			else if(phonenumber_result==0) {
				m.addAttribute("check","该账号名未注册，请返回注册");
				return "login_jump";
			}
			else if(!u.getPassword().equals(user.getPassword())) {
				m.addAttribute("check","密码错误，请重新登录");
				return "login_jump";
			}
			else if(u.getPassword().equals(user.getPassword())){
				return "redirect:/main";
			}
			else {
				m.addAttribute("check","系统错误，请返回重新登录");
				return "login_jump";
			}
		}
		catch(Exception e) {
			m.addAttribute("check","输入错误，请返回输入");
			return "login_jump";
		}
		
	}
	
}
