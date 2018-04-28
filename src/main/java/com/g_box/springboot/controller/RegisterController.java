package com.g_box.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.g_box.springboot.mapper.UserMapper;
import com.g_box.springboot.pojo.User;
import com.g_box.springboot.pojo.Information;

@Controller
public class RegisterController {
	
	private String pictureaddr;
	private String picturename;
	
	@Autowired 
	UserMapper userMapper;
	
	@RequestMapping("/register")
    public String register(Model m) throws Exception {
        return "register";
    }
	
	@RequestMapping(value="/register_handle",method=RequestMethod.POST)
	public String register_handle(User user,Model m,MultipartFile file,HttpServletRequest request) throws Exception{
		
		
		try {
			//获取文件类型，即后缀名
	        String str = file.getOriginalFilename();
	        String suffix = str.substring(str.lastIndexOf("."));
	        
	        //用 当前日期+UUID作为文件名避免重名
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        String dateStr = sdf.format(new Date()).replaceAll("-", "");
	        String uuid = dateStr +UUID.randomUUID().toString().replaceAll("-", "");
	 
			
			//String path = request.getSession().getServletContext().getRealPath("upload"); 
	        String SaveFileName  = dateStr+uuid+suffix;
	        this.picturename = SaveFileName;
	      
	        // System.out.println("path = "+path+" savename = "+savename);
			//String path = "E:\\G-BOX\\picture";
	        String path = request.getSession().getServletContext().getRealPath("/picture"); 
			this.pictureaddr = path;
	        try
	        {
	            file.transferTo(new File(path,SaveFileName));
	        } catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		}
		catch(Exception e) {
			m.addAttribute("check","图片提交出错！请重新提交.");
			return "register_jump";
		}
		
		
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
				
				
				System.out.println(ID);
				System.out.println(user.getPhonenumber());
				System.out.println(this.pictureaddr);
				System.out.println(this.picturename);
				//String id = String.valueOf(ID);
				String id = String.valueOf(ID);
				String phonenumber = user.getPhonenumber();
				String pictureaddr = this.pictureaddr;
				String picturename = this.picturename;
				m.addAttribute("id",id);
				m.addAttribute("phonenumber",user.getPhonenumber());
				m.addAttribute("name",user.getName());
				m.addAttribute("pictureaddr", this.pictureaddr);
				m.addAttribute("picturename", this.picturename);
				return "forward:/information_handle?id="+ID+"&phonenumber="+phonenumber+"&pictureaddr="+pictureaddr+"&picturename="+picturename;
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
