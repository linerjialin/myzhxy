package com.liner.zhxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_teacher")
public class Teacher {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;		//教师编号
	private String tno;
	private String name;	//教师姓名
	private Character gender;	//性别
	private String password;       //密码
	private String email;       //邮箱
	private String telephone;   //电话
	private String address;     //地址
	private String portraitPath; //头像路径
	private String clazzName;	//班级名称
}
