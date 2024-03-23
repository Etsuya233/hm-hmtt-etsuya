package com.heima.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDTO;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/login")
@Api("app端登录接口")
public class ApUserLoginController {

	private final ApUserService apUserService;

	public ApUserLoginController(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

	@PostMapping("/login_auth")
	@ApiOperation("app端用户登录")
	public ResponseResult login(@RequestBody LoginDTO loginDTO){
		return apUserService.login(loginDTO);
	}
}
