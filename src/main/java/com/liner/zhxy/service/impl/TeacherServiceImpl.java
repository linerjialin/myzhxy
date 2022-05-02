package com.liner.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liner.zhxy.entity.LoginForm;
import com.liner.zhxy.entity.Teacher;
import com.liner.zhxy.mapper.TeacherMapper;
import com.liner.zhxy.service.TeacherService;
import com.liner.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

	/**
	 * 教师登录
	 * @param loginForm
	 * @return
	 */
	@Override
	public Teacher login(LoginForm loginForm) {
		QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", loginForm.getUsername());
		queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
		Teacher teacher = baseMapper.selectOne(queryWrapper);
		return teacher;
	}

	/**
	 * 获取教师信息
	 * @param userId
	 * @return
	 */
	@Override
	public Teacher getTeacherById(Long userId) {
		QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", userId);
		return baseMapper.selectOne(queryWrapper);
	}

	@Override
	public IPage<Teacher> getTeacherByOpr(Page<Teacher> page, Teacher teacher) {
		QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(teacher.getName())) {
			queryWrapper.like("name", teacher.getName());
		}
		if (!StringUtils.isEmpty(teacher.getClazzName())) {
			queryWrapper.eq("clazz_name", teacher.getClazzName());
		}
		queryWrapper.orderByDesc("id");
		return baseMapper.selectPage(page, queryWrapper);
	}
}
