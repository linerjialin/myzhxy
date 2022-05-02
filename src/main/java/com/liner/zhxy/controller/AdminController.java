package com.liner.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liner.zhxy.entity.Admin;
import com.liner.zhxy.service.AdminService;
import com.liner.zhxy.utils.MD5;
import com.liner.zhxy.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "管理员控制器")
@RequestMapping("/sms/adminController")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
	@ApiOperation("获取管理员")
	public Result getAllAdmin(@ApiParam("分页查询的页码数") @PathVariable("pageNo") int pageNo,
							  @ApiParam("分页查询的页大小") @PathVariable("pageSize") int pageSize,
                              String adminName
	) {
		Page<Admin> page = new Page(pageNo, pageSize);
		IPage<Admin> iPage = adminService.getAdminByOpr(page, adminName);
		return Result.ok(iPage);
	}


	@PostMapping("/saveOrUpdateAdmin")
	@ApiOperation("新增或者修改admin，有ID属性是修改，没有则是添加")
	public Result saveOrUpdate(@RequestBody Admin admin) {
		Integer id = admin.getId();
		if (null == id || 0 == id) {
			admin.setPassword(MD5.encrypt(admin.getPassword()));
		}
		adminService.saveOrUpdate(admin);
		return Result.ok();
	}

	@DeleteMapping("/deleteAdmin")
	@ApiOperation(value = "删除Admin信息")
	public Result deleteAdmin(@RequestBody List<Integer> ids) {
		adminService.removeByIds(ids);
		return Result.ok();
	}
}
