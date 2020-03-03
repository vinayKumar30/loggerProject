package com.org.register.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.register.dao.RegisterDAO;
import com.org.register.dto.ChangePasswordDTO;

@Service
public class ChangePassServiceImpl implements ChangePassService {
	
	private static Logger logger = Logger.getLogger(ChangePassServiceImpl.class);

	@Autowired
	private RegisterDAO dao;

	public ChangePassServiceImpl() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());

	}

	public boolean formFieldValidation(ChangePasswordDTO dto) {
		boolean valid = false;
		try {
			logger.info("inside getMessage()...invoked formFieldValidation");
;
			if (Objects.nonNull(dto)) {
				logger.info("inside getMessage()...Checking if entered character are valid...");

				String Email = dto.getEmail();

				if (Email != null && !Email.isEmpty() && Email.length() >= 6) {
					logger.info("inside getMessage()...Email is valid");
					valid = true;

					String Password = dto.getPassword();

					if (valid && Password != null && !Password.isEmpty() && Password.length() >= 6) {
						logger.info("inside getMessage()...Password is valid");
						valid = true;

						String ConfirmPassword = dto.getConfirmPassword();

						if (valid && ConfirmPassword != null && !ConfirmPassword.isEmpty()
								&& ConfirmPassword.equals(Password)) {
							logger.info("inside getMessage()...ConfirmPassword is valid");
							valid = true;

						}

						else {
							if (ConfirmPassword == null && ConfirmPassword.isEmpty()) {
								logger.info("inside getMessage()...ConfirmPassword is null.");
							}
							if (!ConfirmPassword.equals(Password)) {
								logger.info("inside getMessage()...ConfirmPassword is invalid because its not matching");
							}
							valid = false;
						}

					} else {

						if (Password == null && Password.isEmpty()) {
							logger.info("inside getMessage()...Password is invalid");
						}
						if (Password.length() < 6) {
							logger.info("inside getMessage()...Password is small.Cannot Accept");
						}
						valid = false;

					}
				} else {
					if (Email == null && Email.isEmpty()) {
						logger.info("inside getMessage()...Email given is null.Provide data.");
					}
					if (Email.length() < 10) {
						logger.info("inside getMessage()...Email length exceeds.");
					}
					valid = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}

		return valid;
	}

	public String validateEmail(ChangePasswordDTO mailDTO) {
		try {
			logger.info("inside getMessage()...Invoked validateEmail() method...");

			String regEntity1 = dao.ChangePasswordByEmail(mailDTO.getEmail(), mailDTO.getPassword());

			if (regEntity1 == null) {
				logger.info("inside getMessage()...Entered email is not valid ");
				return null;

			} else {
				
				return regEntity1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}
		return null;
	}

}
