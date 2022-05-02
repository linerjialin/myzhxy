package com.liner.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liner.zhxy.entity.Teacher;
import com.liner.zhxy.service.TeacherService;
import com.liner.zhxy.utils.MD5;
import com.liner.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "教师控制器")
@RequestMapping("/sms/teacherController")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	//http://localhost:9001/sms/teacherController/getTeachers/1/3
	@GetMapping("/getTeachers/{pageNo}/{pageSize}")
	@ApiOperation("分页查询")
	public Result getTeachers(@ApiParam("分页查询的页码数") @PathVariable("pageNo") int pageNo,
							  @ApiParam("分页查询的页大小") @PathVariable("pageSize") int pageSize,
							  Teacher teacher) {
		Page<Teacher> page = new Page<>(pageNo, pageSize);
		IPage<Teacher> iPage = teacherService.getTeacherByOpr(page, teacher);
		return Result.ok(iPage);
	}

	@PostMapping("/saveOrUpdateTeacher")
	@ApiOperation("新增或者修改教师，有ID属性是修改，没有则是添加")
	public Result saveOrUpdateTeacher(@RequestBody Teacher teacher) {
		if (teacher.getId() == null || teacher.getId() == 0) {
			teacher.setPassword(MD5.encrypt(teacher.getPassword()));
		}
		teacherService.saveOrUpdate(teacher);
		return Result.ok();
	}

	@DeleteMapping("/deleteTeacher")
	@ApiOperation(value = "删除教师信息")
	public Result deleteTeacher(@RequestBody List<Integer> ids) {
		teacherService.removeByIds(ids);
		return Result.ok();
	}
}
