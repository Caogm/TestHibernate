package cn.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class TesthHibernate {

	// 向数据库里插入一条记录
	@Test
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
		// 6.开启事务
		Transaction tx = session.beginTransaction();
		// 7.业务逻辑操作

		// 向数据库中插入一条记录
		Customer customer = new Customer();
		customer.setName("测试2");
		customer.setAge(25);

		session.save(customer);
		// 事务提交
		tx.commit();

		// 8.释放资源
		session.close();
	}

	// 按照id进行查询
	@Test
	public void demo2() {
		// 1.创建配置对象
		Configuration configuration = new Configuration();
		// 2.加载核心配置文件
		configuration.configure();
		// 3.创建serviceRegistry对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		// 4.创建sessionFactory对象，构建session工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 5.获取session对象
		Session session = sessionFactory.openSession();
		// 6.开启事务
		Transaction tx = session.beginTransaction();
		// 7.业务逻辑操作
		// 按照id进行查询
		// 使用get查询
		Customer customer = (Customer) session.get(Customer.class, 1);// 立即发出SQL语句
		System.out.println(customer);
		// 使用load查询
		Customer customer1 = (Customer) session.load(Customer.class, 1);// 没用发送SQL
		System.out.println(customer1);// 发送SQL

		// 事务提交
		tx.commit();

		// 8.释放资源
		session.close();
	}

	// 修改记录
	@Test
	public void demo3() {
		// 1.创建配置对象
		Configuration configuration = new Configuration();
		// 2.加载核心配置文件
		configuration.configure();
		// 3.创建serviceRegistry对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		// 4.创建sessionFactory对象，构建session工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 5.获取session对象
		Session session = sessionFactory.openSession();
		// 6.开启事务
		Transaction tx = session.beginTransaction();
		// 7.业务逻辑操作
		// 修改记录的两种方式
		// 手动创建对象的方式
		/*
		 * Customer customer2 = new Customer(); customer2.setId(6);
		 * customer2.setName("yaya"); session.update(customer2);
		 */
		// 先查询再修改的方式
		Customer customer2 = (Customer) session.get(Customer.class, 1);
		customer2.setName("测试1");

		session.update(customer2);

		// 事务提交
		tx.commit();

		// 8.释放资源
		session.close();
	}

	// 删除记录
	@Test
	public void demo4() {
		// 1.创建配置对象
		Configuration configuration = new Configuration();
		// 2.加载核心配置文件
		configuration.configure();
		// 3.创建serviceRegistry对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		// 4.创建sessionFactory对象，构建session工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 5.获取session对象
		Session session = sessionFactory.openSession();
		// 6.开启事务
		Transaction tx = session.beginTransaction();
		// 7.业务逻辑操作
		// 删除记录有两种方式:
		// 手动创建对象的方式
		/*
		 * Customer customer3 = new Customer(); customer3.setId(7);
		 * session.delete(customer3);
		 */

		// 5.2先查询在删除的方式

		Customer customer3 = (Customer) session.get(Customer.class, 6);
		session.delete(customer3);

		// 事务提交
		tx.commit();

		// 8.释放资源
		session.close();
	}

	// 查询所有
	@Test
	public void demo5() {
		// 1.创建配置对象
		Configuration configuration = new Configuration();
		// 2.加载核心配置文件
		configuration.configure();
		// 3.创建serviceRegistry对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		// 4.创建sessionFactory对象，构建session工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 5.获取session对象
		Session session = sessionFactory.openSession();
		// 6.开启事务
		Transaction tx = session.beginTransaction();
		// 7.业务逻辑操作
		// 7.1查询所有的客户

		Query query = session.createQuery("from Customer");
		List<Customer> list = query.list();

		// 7.2按名称查询
		/*
		 * Query query = session.createQuery("from Customer where name = ?");
		 * query.setParameter(0, "测试1");
		 */
		/*
		 * Query query = session.createQuery("from Customer where name = :a");
		 * query.setParameter("a", "测试1"); List<Customer> list = query.list();
		 */

		for (Customer customer : list) {
			System.out.println(customer);
		}
		// 事务提交
		tx.commit();

		// 8.释放资源
		session.close();
	}
}
