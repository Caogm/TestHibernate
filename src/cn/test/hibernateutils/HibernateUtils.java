package cn.test.hibernateutils;
/*
 * hibernate抽取工具类
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		configuration = new Configuration().configure();
		serviceRegistry = new StandardServiceRegistryBuilder().build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}
}
