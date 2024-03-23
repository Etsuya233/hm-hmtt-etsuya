package com.heima.model.user.dtos;

import com.heima.model.user.pojos.ApUser;
import lombok.Data;

@Data
public class LoginVO {
	private ApUser user;
	private String token;
}
