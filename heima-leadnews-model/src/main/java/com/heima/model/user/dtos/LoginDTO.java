package com.heima.model.user.dtos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDTO {
	@ApiModelProperty(name = "手机号", required = true)
	private String phone;
	@ApiModelProperty(name = "密码", required = true)
	private String password;
}
