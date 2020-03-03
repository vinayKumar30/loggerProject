package com.org.register.dto;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.org.register.controller.RegisterAndLoginController;

import lombok.Data;
@Component
@Data
public class ChangePasswordDTO {
	
	private static Logger logger = Logger.getLogger(ChangePasswordDTO.class);
	private String email;
	private String password;
	private String confirmPassword;
	
	public ChangePasswordDTO() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());
	}

}
