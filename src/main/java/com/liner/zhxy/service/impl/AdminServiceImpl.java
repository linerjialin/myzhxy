package com.liner.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liner.zhxy.entity.Admin;
import com.liner.zhxy.entity.LoginForm;
import com.liner.zhxy.mapper.AdminMapper;
import com.liner.zhxy.service.AdminService;
import com.liner.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

	/**
	 * 管理员登录
	 * @param loginForm
	 * @return
	 */
	@Override
	public Admin login(LoginForm loginForm) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", loginForm.getUsername());
		queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
		Admin admin = baseMapper.selectOne(queryWrapper);
		return admin;
	}

	/**
	 * 获取管理员信息
	 * @param userId
	 * @return
	 */
	@Override
	public Admin getAdminById(Long userId) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", userId);
		return baseMapper.selectOne(queryWrapper);
	}


	@Override
	public IPage<Admin> getAdminByOpr(Page<Admin> page, String adminName) {
		QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(adminName)) {
			queryWrapper.like("name", adminName);
		}
		queryWrapper.orderByDesc("id");
		Page<Admin> adminPage = baseMapper.selectPage(page, queryWrapper);
		return adminPage;
	}
}
