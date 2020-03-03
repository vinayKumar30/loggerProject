package com.org.register.dao;

import java.io.Serializable;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.register.controller.RegisterAndLoginController;
import com.org.register.entity.UserEntity;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	
	private static Logger logger = Logger.getLogger(RegisterDAOImpl.class);


	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public RegisterDAOImpl() {
		logger.info("inside getMessage()..."+this.getClass().getSimpleName());
	}

	public Serializable save(UserEntity entity) {
		logger.info("inside getMessage()...Invoking save method from DAOImpl");
		Session session = null;
		try {

	
			session = factory.openSession();
			
			session.beginTransaction();
			
			session.save(entity);
	
			session.getTransaction().commit();
			logger.info("inside getMessage()...Data saved in DATABASE SUCCESSFULLY.");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("inside getMessage()...Resources closed.");
			}

		}

		return entity;

	}

	public UserEntity fetchByEmail(String email) {
		logger.info("inside getMessage()...Invoked fetchByEmail() ....");
		Session session = null;
		try {

			session = factory.openSession();

			logger.info("inside getMessage()...fetching email from DB");
			String hql = "SELECT re from UserEntity re where email='" + email + "'";
			Query query = session.createQuery(hql);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {
				logger.info("inside getMessage()...Email already Exits");
				UserEntity entity = (UserEntity) result;
				return entity;

			} else {
				logger.info("inside getMessage()...Email is not duplicate,Will save.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("inside getMessage()...Resources Closed.");
			}

		}
		return null;
	}

	public UserEntity fetchByNameAndPassword(String userName, String password) {
		Session session = null;
		try {
			session = factory.openSession();
			logger.info("inside getMessage()...Invoked fetchBymailAndName()...");
			Query query = session.getNamedQuery("mailAndUserName");
			query.setParameter("n", userName);
			query.setParameter("e", password);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {
				logger.info("inside getMessage()...Entity found:" + userName);
				UserEntity entity = (UserEntity) result;
				return entity;
			} else {
				logger.info("inside getMessage()...Entity not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
			}
		}
		return null;
	}

	public String UpdatePasswordByEmail(String email, String password) {
		logger.info("inside getMessage()...Invoked fetchByEmail() ....");
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "SELECT re from UserEntity re where re.email='" + email + "'";
			logger.info("inside getMessage()..."+hql);
			Query query = session.createQuery(hql);
			Object result = query.uniqueResult();
			logger.info("inside getMessage()...Checking if object is null or not");
			if (Objects.nonNull(result)) {

				logger.info("inside getMessage()...Email Exits in DataBase...");
				UserEntity entity = (UserEntity) result;
				Query query1 = session.getNamedQuery("update");
				query1.setParameter("n", email);
				password = "newUserPassword1234";
				query1.setParameter("p", password);
				Object result1 = query1.executeUpdate();
				tx.commit();
				if (Objects.nonNull(result1)) {
					logger.info("inside getMessage()..." + email);
					logger.info("inside getMessage()..." + entity);
					return password;
				}

			} else {
				logger.info("inside getMessage()...error");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("inside getMessage()...Resources Closed.");
			}

		}
		return null;
	}

	public String ChangePasswordByEmail(String email, String password) {
		Session session = null;
		Transaction tx = null;
		try {
			logger.info("inside getMessage()...Invoked ChangePasswordByEmail");

			session = factory.openSession();
			
			tx = session.beginTransaction();

			String hql = "SELECT re from UserEntity re where re.email='" + email + "'";
			logger.info("inside getMessage()...System.out.println"+hql);
			Query query = session.createQuery(hql);
			Object result = query.uniqueResult();
			if (Objects.nonNull(result)) {

				logger.info("inside getMessage()...Email Exits in DataBase.");
				UserEntity entity = (UserEntity) result;
				Query query1 = session.getNamedQuery("changePassword");
				query1.setParameter("m", email);
				query1.setParameter("w", password);
				Object result1 = query1.executeUpdate();
				tx.commit();
				if (Objects.nonNull(result1)) {
					logger.info("inside getMessage()...Email found:" + email);
					logger.info("inside getMessage()...Showing Old Entity details:" + entity);
					return password;
				}
			}

			else {
				logger.info("inside getMessage()...error");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--Exception occured--",e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("inside getMessage()...Resources Closed.");
			}
		}
		return null;
	}

}
