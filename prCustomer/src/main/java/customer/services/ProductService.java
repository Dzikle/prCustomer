package main.java.customer.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.Address;
import main.java.customer.entity.Manufactorer;
import main.java.customer.entity.Product;

public class ProductService {
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

	public static void createProduct(Product product) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Address address = new Address(product.getManufactorer().getAddress().getStreet(),
					product.getManufactorer().getAddress().getCity(),
					product.getManufactorer().getAddress().getZipCode());
			session.save(address);
			Manufactorer manufactorer = new Manufactorer(product.getManufactorer().getName(), address);
			session.save(manufactorer);
			product.setManufactorer(manufactorer);
			session.save(product);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.err.println(e);
			tx.rollback();
		}
	}

	public static void updateProduct(Integer id, Product product) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Address address = new Address(product.getManufactorer().getAddress().getCity(),
					product.getManufactorer().getAddress().getStreet(),
					product.getManufactorer().getAddress().getZipCode());
			session.save(address);
			Manufactorer manufactorer = new Manufactorer(product.getManufactorer().getName(), address);
			session.save(manufactorer);
			Product product2 = session.get(Product.class, id);
			product2.setCategory(product.getCategory());
			product2.setName(product.getName());
			product2.setStock(product.getStock());
			product2.setManufactorer(manufactorer);
			session.saveOrUpdate(product2);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}

	}

	public static List<Product> getAllProducts() {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List <Product> products = new ArrayList<Product>();
			products = session.createQuery("FROM main.java.customer.entity.Product").list();
			System.out.println(products);

			//tx.commit();
			session.close();
			return products;

		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}
		return null;
	
	}public static void addProducQuantity(Integer id, Product product) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Product product2 = session.get(Product.class, id);
			
			Integer quantity = product.getStock() + product2.getStock();
			
			product2.setStock(quantity);
			
			session.update(product2);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}

	}
}
