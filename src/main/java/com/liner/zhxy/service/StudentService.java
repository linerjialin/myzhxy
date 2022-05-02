package com.liner.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liner.zhxy.entity.LoginForm;
import com.liner.zhxy.entity.Student;

public interface StudentService extends IService<Student> {
	Student login(LoginForm loginForm);

	Student getStudentById(Long userId);

	IPage<Student> getStudentByOpr(Page<Student> page, Student student);
}
