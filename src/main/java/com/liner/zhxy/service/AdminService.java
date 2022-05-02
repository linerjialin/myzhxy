package com.liner.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liner.zhxy.entity.Admin;
import com.liner.zhxy.entity.LoginForm;

public interface AdminService extends IService<Admin> {
	Admin login(LoginForm loginForm);

	Admin getAdminById(Long userId);

	IPage<Admin> getAdminByOpr(Page<Admin> page, String adminName);
}
