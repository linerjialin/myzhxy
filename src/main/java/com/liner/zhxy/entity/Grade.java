package com.liner.zhxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 年级表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_grade")
public class Grade {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;		//年级编号
	private String name;		//年级名称
	private String manager;		//年级长名字
	private String email;		//年级长邮箱
	private String telephone;	//年级长电话
	private String introducation;		//年级长介绍
}
