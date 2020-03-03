package com.org.register.service;

import com.org.register.dto.ChangePasswordDTO;

public interface ChangePassService {
	
	public boolean formFieldValidation(ChangePasswordDTO dto);
	
	public String validateEmail(ChangePasswordDTO mailDTO);

}
