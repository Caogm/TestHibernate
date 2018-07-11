package cn.test.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.test.hibernateutils.HibernateUtils;

//使用HQL语句进行查询
public class TesthHibernate3 {
	@Test
	public void demo1() {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		// 业务逻辑操作
		// 简单全表查询
		// List<Customer> customers = session.createQuery("from Customer").list();
		// 条件查询
		// List<Customer> customers = session.createQuery("from Customer where
		// name=?").setParameter(0, "测试1").list();
		// 分页查询
		Query query = session.createQuery("from Customer");
		query.setFirstResult(0);
		query.setMaxResults(4);
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		// 事务提交
		tx.commit();

		// 释放资源
		session.close();
	}
}
