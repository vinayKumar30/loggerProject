package com.org.register.dto;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.org.register.controller.RegisterAndLoginController;

import lombok.Data;

@Component
@Data
public class LoginDTO {
	
	private static Logger logger = Logger.getLogger(LoginDTO.class);
	private String userName;
	private String password;

	public LoginDTO() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());

	}
}
