package com.liner.zhxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 管理员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_admin")
public class Admin {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;     //管理员编号
    private String name;        //管理员名字
    private Character gender;       //性别
    private String password;       //密码
    private String email;       //邮箱
    private String telephone;   //电话
    private String address;     //地址
    private String portraitPath; //头像路径
}
