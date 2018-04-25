package com.g_box.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g_box.springboot.mapper.UserMapper;
import com.g_box.springboot.pojo.User;

@Controller
public class RegisterController {

	@Autowired 
	UserMapper userMapper;
	
	@RequestMapping("/register")
    public String register(Model m) throws Exception {
        return "register";
    }
	
	@RequestMapping("/register_handle")
	public String register_handle(User user,Model m) {
		System.out.println(user.getPhonenumber());
		try {
			int Phonenumber_result = userMapper.checkexistphonenumber(user.getPhonenumber());
			int Name_result = userMapper.checkexistname(user.getName());
			
			if(user.getPhonenumber()==""||user.getPassword()==""||user.getName()=="") {
				m.addAttribute("check","账号或密码或用户名不能为空！");
				return "register_jump";
			}
			else if(user.getPhonenumber().length()!=11){
				m.addAttribute("check","手机号码格式错误，请返回重新输入！");
				return "register_jump";
			}
			else if(Name_result!=0) {
				m.addAttribute("check","该用户名已存在，请返回重新输入！");
				return "register_jump";
			}
			else if(Phonenumber_result!=0) {
				m.addAttribute("check","手机号码已注册，请返回重新输入！");
				return "register_jump";
			}
			else if(Name_result==0&&Phonenumber_result==0&&user.getPhonenumber().length()==11) {
				userMapper.add(user);
				int ID = userMapper.getId(user.getPhonenumber());
				//userMapper.get(user.getId());
				//System.out.println("id === "+ID);  ===ok
				m.addAttribute("id", ID);
				m.addAttribute("phonenumber", user.getPhonenumber());
				m.addAttribute("name",user.getName());
				return "register_success";
			}
			else {
				m.addAttribute("check","系统错误！");
				return "register_jump";
			}
		}
		catch(Exception e) {
			m.addAttribute("check","输入错误！请重新输入.");
			return "register_jump";
		}
		
	}
	
}
