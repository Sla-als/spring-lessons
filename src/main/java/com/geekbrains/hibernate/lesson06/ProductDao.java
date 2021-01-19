package com.geekbrains.hibernate.lesson06;

import com.geekbrains.hibernate.PrepareDataApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ProductDao {
    private static SessionFactory factory;


    public static void main(String[] args) {
        try {
            init();
            //System.out.println(findById(3L));
            //deleteById(2L);
            System.out.println(findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    private static void init() {
        PrepareDataApp.forcePrepareData();
         factory = new Configuration()
                .configure("configs/lesson06/hibernate.cfg.xml")
                .buildSessionFactory();

    }

    private static void shutdown() {
        factory.close();
    }

    private static Product findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public static List<Product> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> items = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return items;
        }
    }

    private static void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static Product saveOrUpdate(Product p) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> items = session.createQuery("from Product").getResultList();
            if (p.getId() != null) {  //        Update
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == p.getId()) {
                        Product product = session.get(Product.class, p.getId());
                        product.setCost(p.getCost());
                        product.setTitle(p.getTitle());
                        session.getTransaction().commit();
                        return p;
                    }
                }
            }
            session.save(p);
            return p;
        }
    }
}
