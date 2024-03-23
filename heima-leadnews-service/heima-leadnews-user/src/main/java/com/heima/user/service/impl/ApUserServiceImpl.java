package com.heima.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDTO;
import com.heima.model.user.dtos.LoginVO;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
	@Override
	public ResponseResult login(LoginDTO loginDTO) {
		String phone = loginDTO.getPhone();
		String password = loginDTO.getPassword();
		LoginVO loginVO = new LoginVO();
		//正常登录
		if(StringUtils.isNotEmpty(phone) && StringUtils.isNotBlank(password)){
			ApUser user = this.lambdaQuery().eq(ApUser::getPhone, phone).one();
			if(user == null) return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在！");
			String passwordStr = loginDTO.getPassword() + user.getSalt();
			String inputPassword = DigestUtils.md5DigestAsHex(passwordStr.getBytes(StandardCharsets.UTF_8));
			if(!inputPassword.equals(user.getPassword())) return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
			//jwt
			String token = AppJwtUtil.getToken(user.getId().longValue());
			user.setSalt(""); //脱敏
			user.setPassword("");
			loginVO.setUser(user);
			loginVO.setToken(token);
			return ResponseResult.okResult(loginVO);
		}
		//游客登录
		String token = AppJwtUtil.getToken(0L);
		loginVO.setToken(token);
		return ResponseResult.okResult(loginVO);
	}
}
