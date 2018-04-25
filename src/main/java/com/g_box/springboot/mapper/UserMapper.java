package com.g_box.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.g_box.springboot.pojo.User;

@Mapper
public interface UserMapper {
	
	@Insert(" insert into user ( name,password,phonenumber) values (#{name},#{password},#{phonenumber}) ")
	public int add(User user);
	
	@Update("update user set name=#{name},password=#{password} where id=#{id}")
	public int update(User user);
	
	@Select("select * from user where id= #{id} ")
	public User get(int id);
	
	@Select("select id from user where phonenumber=#{phonenumber}")
	public  int getId(String phonenumber);
	
	@Select("select count(*) from user where phonenumber=#{phonenumber}")
	public int checkexistphonenumber(String phonenumber);
	
	@Select("select count(*) from user where name=#{name}")
	public int checkexistname(String name);
	
}
