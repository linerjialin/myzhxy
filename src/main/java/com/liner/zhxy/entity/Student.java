package com.liner.zhxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_student")
public class Student {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;		//学生编号
	private String sno;
	private String name;		//学生名字
	private Character gender;	//性别
	private String password;       //密码
	private String email;       //邮箱
	private String telephone;   //电话
	private String address;     //地址
	private String portraitPath; //头像路径
	private String introducation;	//学生介绍
	private String clazzName;	//班级名称
}
