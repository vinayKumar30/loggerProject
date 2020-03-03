package com.org.register.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.register.dao.RegisterDAO;
import com.org.register.dto.LoginDTO;
import com.org.register.dto.RegisterDTO;
import com.org.register.entity.UserEntity;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	private static Logger logger = Logger.getLogger(RegisterServiceImpl.class);


	@Autowired
	private RegisterDAO dao;

	public RegisterServiceImpl() {
		logger.info("inside getMessage()..." + this.getClass().getSimpleName());
	}

	public boolean validateAndSave(RegisterDTO dto) {
		boolean valid = false;
		try {
			logger.info("inside getMessage()...Invoked ValidateAndSave() from serviceImpl....");

			if (Objects.nonNull(dto)) {
				logger.info("inside getMessage()...starting validation for" + dto);

				String Username = dto.getUserName();

				if (Username != null && !Username.isEmpty() && Username.length() >= 6) {
					logger.info("inside getMessage()...Username is valid");
					valid = true;

					String Email = dto.getEmail();

					if (valid && Email != null && !Email.isEmpty() && Email.length() >= 6) {
						logger.info("inside getMessage()...Email is valid");
						valid = true;

						long phnNum = dto.getPhnNum();
						String Num = Long.toString(phnNum);

						if (valid && Num != null && !Num.isEmpty() && Num.length() == 10) {
							logger.info("inside getMessage()...Phone Number is valid");
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

								} else {
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
							if (Num == null && Num.isEmpty()) {
								logger.info("inside getMessage()...Phone Number given is null.Provide data.");
							}
							if (Num.length() < 10) {
								logger.info("inside getMessage()...Phone Number length doesnt match given validation.");
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

			if (valid) {
				logger.info("inside getMessage()...DATA IS VALID ,CAN SAVE IN DATABASE");

				UserEntity regEntity = new UserEntity();
				BeanUtils.copyProperties(dto, regEntity);
				UserEntity regEntity1 = dao.fetchByEmail(dto.getEmail());

				if (regEntity1 == null) {
					logger.info("inside getMessage()...Entity is ready \t" + regEntity);
					dao.save(regEntity);
					logger.info("inside getMessage()...Entity is saved");

				} else {
					logger.info("inside getMessage()...DATA NOT VALID ,NOT SAVED IN DATABASE");

				}

			}

			return valid;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}
		return false;

	}
}