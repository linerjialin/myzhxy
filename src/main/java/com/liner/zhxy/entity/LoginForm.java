package com.liner.zhxy.entity;

import lombok.Data;

/**
 * 用户表
 */

@Data
public class LoginForm {
	private String username;	//用户名
	private String password;	//密码
	private String verifiCode;	//验证码
	private int userType;	//用户角色

}
