package cn.test.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class TesthHibernate {
	@Test
	// 像数据库里插入一条记录
	public void demo() {
		// 1.创建配置对象
		Configuration config = new Configuration();
		// 2.读取配置文件
		config.configure();
		// 3.创建serviceRegistry对象
		ServiceRegistry serviceregistry = new StandardServiceRegistryBuilder().build();
		// 4.创建sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceregistry);
		// 5.获取session对象
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// 6.业务逻辑操作

		// 向数据库中插入一条记录
		Customer customer = new Customer();
		customer.setName("hh");
		customer.setAge(20);

		session.save(customer);

		tx.commit();

		// 7.释放资源
		session.close();
	}
}
