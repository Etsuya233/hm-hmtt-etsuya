package com.heima.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDTO;
import com.heima.user.service.ApUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/login")
public class ApUserLoginController {

	private final ApUserService apUserService;

	public ApUserLoginController(ApUserService apUserService) {
		this.apUserService = apUserService;
	}

	@PostMapping("/login_auth")
	public ResponseResult login(LoginDTO loginDTO){
		return apUserService.login(loginDTO);
	}
}
