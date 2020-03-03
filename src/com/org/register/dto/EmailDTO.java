package com.org.register.dto;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.org.register.controller.RegisterAndLoginController;

import lombok.Data;

@Component
@Data
public class EmailDTO {
	
	private static Logger logger = Logger.getLogger(EmailDTO.class);
	
	private String email;
	private String password;
	private String comfirmPassword;
	
	public EmailDTO() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());
	}

}
