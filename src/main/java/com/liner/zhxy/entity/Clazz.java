package com.liner.zhxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_clazz")
public class Clazz {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;		//班级编号
	private String name;	//班级名称
	private Integer number;	//班级人数
	private String introducation;	//班级介绍
	private String headmaster;	//班主任名称
	private String email;		//班主任邮箱
	private String telephone;  //班主任电话
	private String gradeName; //班级所属年级
}
