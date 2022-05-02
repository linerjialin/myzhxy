package com.liner.zhxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liner.zhxy.entity.Admin;
import com.liner.zhxy.entity.LoginForm;
import com.liner.zhxy.entity.Student;
import com.liner.zhxy.entity.Teacher;
import com.liner.zhxy.service.AdminService;
import com.liner.zhxy.service.StudentService;
import com.liner.zhxy.service.TeacherService;
import com.liner.zhxy.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 除 增删改查 外的 其余操作
 */

@RestController
@Api(tags = "总控制器")
@RequestMapping("/sms/system")
public class SystemController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;

	//http://localhost:9001/sms/system/updatePwd/1111/111
	@PostMapping("updatePwd/{oldPwd}/{newPwd}")
	@ApiOperation("修改密码")
	public Result updatePwd(@ApiParam("旧密码") @PathVariable("oldPwd") String oldPwd,
							@ApiParam("新密码") @PathVariable("newPwd") String newPwd,
							@RequestHeader("token") String token) {
		boolean expiration = JwtHelper.isExpiration(token);
		if (expiration) {
			return Result.fail().message("token失效，请重新登陆后重试");
		}
		Long userId = JwtHelper.getUserId(token);
		int userType = JwtHelper.getUserType(token);
		oldPwd = MD5.encrypt(oldPwd);
		newPwd = MD5.encrypt(newPwd);

		switch (userType) {
			case 1:
				QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("id", userId);
				queryWrapper.eq("password", oldPwd);
				Admin one = adminService.getOne(queryWrapper);
				if (one != null) {
					one.setPassword(newPwd);
					adminService.saveOrUpdate(one);
				} else {
					return Result.fail().message("输入原密码错误");
				}
				break;
			case 2:
				QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
				queryWrapper1.eq("id", userId);
				queryWrapper1.eq("password", oldPwd);
				Student student = studentService.getOne(queryWrapper1);

				if (student != null) {
					student.setPassword(newPwd);
					studentService.saveOrUpdate(student);
				} else {
					return Result.fail().message("输入原密码错误");
				}
				break;
			case 3:
				QueryWrapper<Teacher> queryWrapper2 = new QueryWrapper<>();
				queryWrapper2.eq("id", userId);
				queryWrapper2.eq("password", oldPwd);
				Teacher teacher = teacherService.getOne(queryWrapper2);

				if (teacher != null) {
					teacher.setPassword(newPwd);
					teacherService.saveOrUpdate(teacher);
				} else {
					return Result.fail().message("输入原密码错误");
				}
				break;
		}
		return Result.ok();
	}


	/**
	 * 图片上传方法
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation("文件上传的统一入口")
	@PostMapping("headerImgUpload")
	public Result headerImgUpload(@ApiParam("头像文件")@RequestPart("multipartFile") MultipartFile multipartFile) {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();	//生成唯一uuid
		String originalFilename = multipartFile.getOriginalFilename();//获取原视类名
		int i = originalFilename.lastIndexOf(".");//找到最后一个点
		String newFileName = uuid + originalFilename.substring(i);//新的文件名用来保存
		//保存文件  真实开发环境将文件发送给第三方/独立的图片服务器保存
		//找到upload的真实存在位置
		String portraitPath = "E:/work/myzhxy/target/classes/public/upload/" + newFileName;
		try {
			multipartFile.transferTo(new File(portraitPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = "upload/" + newFileName;
		return Result.ok(path);
	}


	@GetMapping("/getInfo")
	public Result getInfoByToken(@RequestHeader("token") String token) {
		boolean expiration = JwtHelper.isExpiration(token);
		if (expiration) {
			return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
		}
		Long userId = JwtHelper.getUserId(token);
		Integer userType = JwtHelper.getUserType(token);
		Map<String, Object> map = new HashMap<>();
		switch (userType) {
			case 1:
				Admin admin = adminService.getAdminById(userId);
				map.put("userType", 1);
				map.put("user", admin);
				break;
			case 2:
				Student student = studentService.getStudentById(userId);
				map.put("userType", 2);
				map.put("user", student);
				break;
			case 3:
				Teacher teacher = teacherService.getTeacherById(userId);
				map.put("userType", 3);
				map.put("user", teacher);
				break;
		}
		return Result.ok(map);
	}

	@PostMapping("/login")
	@ApiOperation("登录")
	public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionVerifiCode = (String) session.getAttribute("verifiCode");
		String loginVerifiCode = loginForm.getVerifiCode();

		if ("".equals(sessionVerifiCode) || null == sessionVerifiCode) {
			return Result.fail().message("验证码失效，请刷新后重试");
		}
		if (!sessionVerifiCode.equalsIgnoreCase(loginVerifiCode)) {
			return Result.fail().message("验证码错误，请重新输入");
		}
		session.removeAttribute("verifiCode");
		Map<String, Object> map = new HashMap<>();
		switch (loginForm.getUserType()) {
			case 1:
				try {
					Admin admin = adminService.login(loginForm);
					if (null != admin) {
						String token = JwtHelper.createToken(admin.getId().longValue(), 1);
						map.put("token", token);
					} else {
						throw new RuntimeException("用户名或密码有误");
					}
					return Result.ok(map);
				} catch (RuntimeException e) {
					e.printStackTrace();
					return Result.fail().message(e.getMessage());
				}
			case 2:
				try {
					Student student = studentService.login(loginForm);
					if (null != student) {
						String token = JwtHelper.createToken(student.getId().longValue(), 2);
						map.put("token", token);
					} else {
						throw new RuntimeException("用户名或密码有误");
					}
					return Result.ok(map);
				} catch (RuntimeException e) {
					e.printStackTrace();
					return Result.fail().message(e.getMessage());
				}
			case 3:
				try {
					Teacher teacher = teacherService.login(loginForm);
					if (null != teacher) {
						String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
						map.put("token", token);
					} else {
						throw new RuntimeException("用户名或密码有误");
					}
					return Result.ok(map);
				} catch (RuntimeException e) {
					e.printStackTrace();
					return Result.fail().message(e.getMessage());
				}
		}
		return Result.fail().message("查无此用户");
	}

	@GetMapping("/getVerifiCodeImage")
	@ApiOperation("获取验证码")
	public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
		String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
		HttpSession session = request.getSession();
		session.setAttribute("verifiCode", verifiCode);
		try {
			ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



