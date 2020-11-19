package main.java.customer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.Customer;
import main.java.customer.entity.Product;
import main.java.customer.entity.ShoppingCart;

public class ShoppingCartService {
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

	public static void createCart(Integer customerId, ShoppingCart cart) {
		// TODO Auto-generated method stub

	}

	public static void createCartByProductId(Integer customerId, List<Integer> productIds) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Product> result = new ArrayList<Product>();

			for (Integer ids : productIds) {

				Product product = session.get(Product.class, ids);
				result.add(product);

			}
			Customer customer = session.get(Customer.class, customerId);
			Date createdOn = new Date(System.currentTimeMillis());

			ShoppingCart cart = new ShoppingCart(result, customer, createdOn);
			session.save(customer);
			session.save(cart);

			tx.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}
	}

	public static void removeProductFromCart(Integer cartid, Integer productId) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Product product = session.get(Product.class, productId);
			ShoppingCart cart = session.get(ShoppingCart.class, cartid);

			List<Product> products = cart.getProducts();

			for (Product product2 : products) {
				if (product2.getId() == product.getId()) {
					products.remove(product2);
				}
			}

			cart.setCreatedOn(new Date(System.currentTimeMillis()));

			session.update(cart);

			tx.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}
	}

	public static void removeAllProductFromCart(Integer cartid) {
		Session session = init();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			ShoppingCart cart = session.get(ShoppingCart.class, cartid);

			//List<Product> products = cart.getProducts();
			
		//	products.clear();

//			for (Product product2 : products) {
//
//				products.remove(product2);
//
//			}

		//	cart.setCreatedOn(new Date(System.currentTimeMillis()));
			
			//cart.setProducts(products);
			
			List<Product> nula = new ArrayList<Product>();
			
			cart.setProducts(nula);

			session.update(cart);

			tx.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e);
			tx.rollback();
		}

	}
}
