package main.java.customer.services;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.Address;
import main.java.customer.entity.Customer;

public class CustomerServices {

	static SessionFactory factory;

	public static Session init() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(main.java.customer.entity.Address.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Customer.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Manufactorer.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Product.class);
		cfg.addAnnotatedClass(main.java.customer.entity.ShoppingCart.class);
		cfg.configure();

		factory = cfg.configure().buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	public static void createCustomer(Customer customer) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Address address = new Address(customer.getAddress().getStreet(), customer.getAddress().getCity(),
					customer.getAddress().getZipCode());
			session.save(address);
			
			
			customer.setAddress(address);
			session.save(customer);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}
	}

	public static void updateCustomer(Integer id, Customer customer) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM main.java.customer.entity.Customer c WHERE c.id=:id");
			query.setParameter("id", id);
			Customer customerNew = session.get(Customer.class, id);
			customerNew.setAddress(customer.getAddress());
			customerNew.setAge(customer.getAge());
			customerNew.setName(customerNew.getName());
			session.update(customerNew);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}
	}
}
