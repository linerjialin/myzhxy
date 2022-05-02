package com.liner.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liner.zhxy.entity.LoginForm;
import com.liner.zhxy.entity.Student;
import com.liner.zhxy.mapper.StudentMapper;
import com.liner.zhxy.service.StudentService;
import com.liner.zhxy.utils.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

@Service("studentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

	/**
	 * 学生登录
	 * @param loginForm
	 * @return
	 */
	@Override
	public Student login(LoginForm loginForm) {
		QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", loginForm.getUsername());
		queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
		Student student = baseMapper.selectOne(queryWrapper);
		return student;
	}

	/**
	 * 获取学生信息
	 * @param userId
	 * @return
	 */
	@Override
	public Student getStudentById(Long userId) {
		QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", userId);
		return baseMapper.selectOne(queryWrapper);
	}

	@Override
	public IPage<Student> getStudentByOpr(Page<Student> page, Student student) {
		QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(student.getName())) {
			queryWrapper.like("name", student.getName());
		}
		if (!StringUtils.isEmpty(student.getClazzName())) {
			queryWrapper.like("clazz_name", student.getClazzName());
		}
		queryWrapper.orderByDesc("id");
		Page<Student> studentPage = baseMapper.selectPage(page, queryWrapper);
		return studentPage;
	}
}
