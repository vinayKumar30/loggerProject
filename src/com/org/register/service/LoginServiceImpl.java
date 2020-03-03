package com.org.register.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.register.dao.RegisterDAO;
import com.org.register.dto.LoginDTO;
import com.org.register.entity.UserEntity;

@Service
public class LoginServiceImpl implements LoginService {

	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);

	
	@Autowired
	private RegisterDAO dao;

	public LoginServiceImpl() {
		logger.info("inside getMessage()..." + this.getClass().getSimpleName());
	}

	public boolean validateLoginDetails(LoginDTO loginDTO) {
		boolean valid = false;
		try {
			logger.info("inside getMessage()...Invoked validateLoginDetails()...");

			if (Objects.nonNull(loginDTO)) {
				logger.info("inside getMessage()...starting validation for" + loginDTO);

				String Username = loginDTO.getUserName();

				if (Username != null && !Username.isEmpty() && Username.length() >= 6) {
					logger.info("inside getMessage()...Username is valid");
					valid = true;

					String Password = loginDTO.getPassword();

					if (valid && Password != null && !Password.isEmpty() && Password.length() >= 6) {
						logger.info("inside getMessage()...Password is valid");
						valid = true;

						if (valid) {
							UserEntity lg1 = dao.fetchByNameAndPassword(loginDTO.getUserName(),
									loginDTO.getPassword());
							if (lg1 != null) {
								logger.info("inside getMessage()...User details exist");

								return true;
							} else {
								logger.info("inside getMessage()...User doesnt not exist");
								return false;
							}
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

					if (Username == null && Username.isEmpty()) {
						logger.info("inside getMessage()...Username is null");
					}
					if (Username.length() < 5) {
						logger.info("inside getMessage()...name length is less than 5");
					}
					valid = false;

				}

			}

			return valid;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}

		return valid;
	}

}
