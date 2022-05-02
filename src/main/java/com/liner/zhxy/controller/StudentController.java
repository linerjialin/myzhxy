package com.liner.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liner.zhxy.entity.Student;
import com.liner.zhxy.service.StudentService;
import com.liner.zhxy.utils.MD5;
import com.liner.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "学生控制器")
@RequestMapping("/sms/studentController")
public class StudentController {
	@Autowired
	private StudentService studentService;

	//http://localhost:9001/sms/studentController/delStudentById
	@DeleteMapping("/delStudentById")
	@ApiOperation(value = "删除学生信息")
	public Result deleteStudent(@RequestBody List<Integer> Ids) {
		studentService.removeByIds(Ids);
		return Result.ok();
	}

	//http://localhost:9001/sms/studentController/addOrUpdateStudent
	@PostMapping("/addOrUpdateStudent")
	@ApiOperation("新增或者修改学生，有ID属性是修改，没有则是添加")
	public Result addOrUpdateStudent(@RequestBody Student student) {
		Integer id = student.getId();
		if (null == id || 0 == id) {
			student.setPassword(MD5.encrypt(student.getPassword()));
		}
		studentService.saveOrUpdate(student);
		return Result.ok();
	}

	@GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
	@ApiOperation("分页查询")
	public Result getStudentByOpr(@ApiParam("分页查询的页码数") @PathVariable("pageNo") int pageNo,
								  @ApiParam("分页查询的页大小") @PathVariable("pageSize") int pageSize,
	                              Student student) {
		Page<Student> page = new Page(pageNo, pageSize);
		IPage<Student> studentIPage = studentService.getStudentByOpr(page, student);
		return Result.ok(studentIPage);
	}
}
