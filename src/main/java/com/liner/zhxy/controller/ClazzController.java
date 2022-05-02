package com.liner.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liner.zhxy.entity.Clazz;
import com.liner.zhxy.service.ClazzService;
import com.liner.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "班级控制器")
@RequestMapping("/sms/clazzController")
public class ClazzController {
	//http://localhost:9001/sms/clazzController/getClazzsByOpr/1/3
	//http://localhost:9001/sms/clazzController/getClazzsByOpr/1/3?gradeName name
	@Autowired
	private ClazzService clazzService;

	@GetMapping("/getClazzs")
	@ApiOperation("获取全部班级")
	public Result getClazzs() {
		List<Clazz> clazzes = clazzService.getClazzs();
		return Result.ok(clazzes);
	}

	@DeleteMapping("/deleteClazz")
	@ApiOperation(value = "删除Clazz信息")
	public Result deleteClazz(@RequestBody List<Integer> ids) {
		clazzService.removeByIds(ids);
		return Result.ok();
	}

	@PostMapping("/saveOrUpdateClazz")
	@ApiOperation("新增或者修改Clazz，有ID属性是修改，没有则是添加")
	public Result saveOrUpdateClazz(@RequestBody Clazz clazz) {
		clazzService.saveOrUpdate(clazz);
		return Result.ok();
	}

	@ApiOperation("分页带条件查询班级信息")
	@GetMapping("getClazzsByOpr/{pageNo}/{pageSize}")
	public Result getClazzByOpr(
			@ApiParam("分页查询的页码数") @PathVariable("pageNo") int pageNo,
			@ApiParam("分页查询的页大小") @PathVariable("pageSize") int pageSize,
		Clazz clazz
	) {
		Page<Clazz> page = new Page<>(pageNo, pageSize);
		IPage<Clazz> iPage = clazzService.getClazzByOpe(page, clazz);
		return Result.ok(iPage);
	}
}
