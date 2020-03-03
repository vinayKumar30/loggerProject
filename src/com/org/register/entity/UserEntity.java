package com.org.register.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import com.org.register.dto.ChangePasswordDTO;

import lombok.Data;

@Entity
@Table(name = "register")
@Data
@NamedQueries({ @NamedQuery(name = "fetchByEmail", query = "select re from UserEntity re where re.email=:email"),
		@NamedQuery(name = "UpdatePasswordByEmail", query = "select re from UserEntity re where re.email=:email"),
		@NamedQuery(name = "fetchByName", query = "select re from UserEntity re where re.userName=:userName"),
		@NamedQuery(name = "mailAndUserName", query = "select re from UserEntity re where re.userName=:n and re.password=:e"),
		@NamedQuery(name = "update", query = "update UserEntity re set re.password=:p where re.email=:n"),
		@NamedQuery(name = "changePassword", query = "update UserEntity re set re.password=:w where re.email=:m")})
public class UserEntity implements Serializable {
	
	private static Logger logger = Logger.getLogger(UserEntity.class);

	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "id")
	private int id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "phnNum")
	private long phnNum;
	@Column(name = "password")
	private String password;

	public UserEntity() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());

	}

}
