package com.org.register.service;

import com.org.register.dto.EmailDTO;

public interface EmailService {

	public boolean validateFormFields(EmailDTO mailDTO);
	public String validateEmail(EmailDTO dto);
}
