package com.org.register.controller;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.register.dto.ChangePasswordDTO;
import com.org.register.dto.EmailDTO;
import com.org.register.dto.LoginDTO;
import com.org.register.dto.RegisterDTO;
import com.org.register.service.ChangePassService;
import com.org.register.service.EmailService;
import com.org.register.service.LoginService;
import com.org.register.service.RegisterService;

@Controller
@RequestMapping
public class RegisterAndLoginController {

	@Autowired
	private RegisterService ser;
	@Autowired
	private LoginService log;
	@Autowired
	private EmailService email;
	@Autowired
	private ChangePassService change;
	
	private static Logger logger = Logger.getLogger(RegisterAndLoginController.class);

	public RegisterAndLoginController() {
		logger.info("inside getMessage()...Invoked"+this.getClass().getSimpleName());
	}

	@RequestMapping("/reg.do")
	public String onRegister(RegisterDTO dto, ModelMap map) {

		try {
			logger.info("inside getMessage()...Invoked onRegister method");
			boolean check = this.ser.validateAndSave(dto);

			if (check) {

				ModelMap x = map.addAttribute("name", dto.getUserName());
				ModelMap suc = map.addAttribute("successMessage", " Registeration Successful for:");
			} else {
				ModelMap fail = map.addAttribute("failureMessage", "Failure:Data not Saved.Enter valid details");
			}

		} catch (Exception e) {
			e.printStackTrace();
		
			logger.error("--Exception occured--",e);
		}

		return "signup";

	}

	@RequestMapping("/login.do")
	public String onLogin(LoginDTO loginDTO, ModelMap map) {

		try {

			boolean check = this.log.validateLoginDetails(loginDTO);

			if (check) {

				ModelMap name = map.addAttribute("username", loginDTO.getUserName());

				return "ReturnLoginSuccess";

			} else {

				ModelMap fail = map.addAttribute("failureMessage",
						"Login Unsuccessful.Please Check If Entered Email and Password Is Correct.");
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}
		return "login";

	}

	@RequestMapping("/fp.do")
	public String onReset(EmailDTO emailDTO, ModelMap map) {
		try {

			logger.info("inside getMessage()...Invoked onReset method");

			boolean xyz = this.email.validateFormFields(emailDTO);

			if (xyz) {

				String registerEntity = this.email.validateEmail(emailDTO);
				if (Objects.nonNull(registerEntity)) {

					ModelMap newpass = map.addAttribute("message", "Your New Password:");
					ModelMap email = map.addAttribute("password", registerEntity);

					return "ForgotPassword";

				} else {

					String xxx = this.email.validateEmail(emailDTO);
					{
						ModelMap fail = map.addAttribute("failureMessage", "Entered Email is Invalid");
						return "InvalidEmail";
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}
		return "ForgotPassword";

	}

	@RequestMapping("/cp.do")
	public String onChange(ChangePasswordDTO changeDTO, ModelMap map) {
		try {
			logger.info("inside getMessage()...Invoked onChange method");
			logger.info("inside getMessage()..."+changeDTO);
			boolean check = this.change.formFieldValidation(changeDTO);
			logger.info("inside getMessage()...validate is " + check);

			if (!check) {

				String userEntity = this.change.validateEmail(changeDTO);
				if (!Objects.nonNull(userEntity)) {
					ModelMap email = map.addAttribute("InvalidEmail",
							"Entered Email is not valid and Passwords do not match");
				} else {
					ModelMap pass = map.addAttribute("PasswordsDontMatch",
							"Passwords Do Not Match.Please Enter Again.");
				}
			} else {

				String registerEntity = this.change.validateEmail(changeDTO);
				if (Objects.nonNull(registerEntity)) {
					ModelMap newpass = map.addAttribute("SucccessMessage", "Password Changed Successfully");
				} else {
					ModelMap mail = map.addAttribute("EnteredEmailIsNotValid", "Entered Email Is Not Valid");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
			
		}
		return "ChangePassword";

	}

}
