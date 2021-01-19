package com.geekbrains.hibernate.lesson06;

import com.geekbrains.hibernate.PrepareDataApp;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class SessionApp {

    //@Autowired
    private static SessionFactory factory;

    @PostConstruct
    static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/lesson06/hibernate.cfg.xml")
                .buildSessionFactory();

    }

    public static SessionFactory getFactory() {
        return factory;
    }

    static void shutdown() {
        factory.close();
    }


}

//package com.geekbrains.hibernate.lesson06;
//
//        import com.geekbrains.hibernate.PrepareDataApp;
//        import org.hibernate.Session;
//        import org.hibernate.SessionFactory;
//        import org.hibernate.cfg.Configuration;
//
//public class CartApp {
//    public static void main(String[] args) {
//        PrepareDataApp.forcePrepareData();
//
//        SessionFactory factory = new Configuration()
//                .configure("configs/lesson06/hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        Session session = null;
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
////            //Reader reader = session.get(Reader.class, 1L);
//            Buyer buyer01 = session.get(Buyer.class,1L);
//            System.out.println(buyer01);
//            System.out.println("Books: ");
//            for (Product p : buyer01.getProducts()) {
//                System.out.println(p.getTitle());
//            }
////            reader.getBooks().clear();
////            session.getTransaction().commit();
////            List<Reader> readers = session.createQuery("SELECT r FROM Reader r ORDER BY size(r.books) DESC").getResultList();
////            System.out.println(readers);
////            session.getTransaction().commit();
////
////            session = factory.getCurrentSession();
////            session.beginTransaction();
////            List<Book> allBooks = session.createQuery("SELECT b FROM Book b").getResultList();
////            Reader r = session.get(Reader.class, 2L);
////            allBooks.stream().forEach(b -> {
////                if(b.getId() > 4 && b.getId() < 8) {
////                    r.getBooks().add(b);
////                }
////            });
////            r.getBooks().clear();
////            session.getTransaction().commit();
////            System.out.println(1);
////            session = factory.getCurrentSession();
////            session.beginTransaction();
////
////            Reader reader1 = session.get(Reader.class, 1L);
////            reader1.getBooks().removeIf(b -> b.getId() % 2 != 0);
////
////            session.createQuery("DELETE FROM Book b WHERE b.id = 1").executeUpdate();
////
////
////
////            session.getTransaction().commit();
////            System.out.println(2);
////            session = factory.getCurrentSession();
////            session.beginTransaction();
////            System.out.println(session.createQuery("SELECT b.title FROM Book b WHERE b.id = 1", String.class).getSingleResult());
////            session.getTransaction().commit();
////
////            session = factory.getCurrentSession();
////            session.beginTransaction();
////            List<Book> books = session.createQuery("FROM Book").getResultList();
////            session.getTransaction().commit();
//        } finally {
//            factory.close();
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
//}

