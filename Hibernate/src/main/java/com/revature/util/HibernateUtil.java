package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	static {
		sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	private static Session session;
	private static SessionFactory sf;

	private HibernateUtil() {
		super();
	}

	public static Session getSession() {
		if (session == null || !session.isOpen()) {
			session = sf.openSession();
		}
		return session;
	}

	public static void closeSession() {
		if (session != null) {
			session.close();
		}
	}
}
