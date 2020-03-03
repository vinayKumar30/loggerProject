package com.org.register.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.register.dao.RegisterDAO;
import com.org.register.dto.EmailDTO;

@Repository
public class EmailServiceImpl implements EmailService {
	
	private static Logger logger = Logger.getLogger(EmailServiceImpl.class);


	@Autowired
	private RegisterDAO dao;

	public EmailServiceImpl() {
		logger.info("inside getMessage()..." + this.getClass().getSimpleName());
	}

	public boolean validateFormFields(EmailDTO mailDTO) {
		boolean valid = false;
		try {
			logger.info("inside getMessage()...Invoked validateEmail method");

			if (Objects.nonNull(mailDTO)) {
				logger.info("inside getMessage()...Entity found");

				String mail = mailDTO.getEmail();
				if (mail != null && mail.contains("@") && mail.length() >= 10) {
					logger.info("inside getMessage()...Entered mail contains valid character");
					valid = true;

				} else {
					logger.info("inside getMessage()...Mail not valid.Please Enter valid characters");
					valid = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);

		}
		return valid;
	}

	public String validateEmail(EmailDTO dto) {

		try {
			logger.info("inside getMessage()...Enter email IS VALID");

			String regEntity1 = dao.UpdatePasswordByEmail(dto.getEmail(), dto.getPassword());

			if (regEntity1 == null) {
				logger.info("inside getMessage()...Entered email is not valid ");
				return null;

			} else {
				logger.info("inside getMessage()..New Password \t" + regEntity1);
				return regEntity1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}
		return null;

	}

}
