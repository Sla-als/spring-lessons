package com.geekbrains.hibernate.lesson06;

import com.geekbrains.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BuyerDao {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            init();
            System.out.println(findById(3L));
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

    private static Buyer findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            session.getTransaction().commit();
            return buyer;
        }
    }

    public static List<Buyer> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Buyer> items = session.createQuery("from Buyer").getResultList();
            session.getTransaction().commit();
            return items;
        }
    }

    private static void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            session.delete(buyer);
            session.getTransaction().commit();
        }
    }

    public static Buyer saveOrUpdate(Buyer b) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Buyer> items = session.createQuery("from Buyer ").getResultList();
            if (b.getId() != null) {  //        Update
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getId() == b.getId()) {
                        Buyer buyer = session.get(Buyer.class, b.getId());
                        buyer.setId(b.getId());
                        buyer.setName(b.getName());
                        session.getTransaction().commit();
                        return b;
                    }
                }
            }
            session.save(b);
            return b;
        }
    }
}
