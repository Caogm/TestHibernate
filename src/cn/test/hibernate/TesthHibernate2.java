package cn.test.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.test.hibernateutils.HibernateUtils;

public class TesthHibernate2 {

	// 向数据库里插入一条记录
	@Test
	public void demo() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// 业务逻辑操作

		// 向数据库中插入一条记录
		Customer customer = new Customer();
		customer.setName("测试3");
		customer.setAge(25);

		session.save(customer);
		// 事务提交
		tx.commit();

		// 释放资源
		session.close();
	}
}
