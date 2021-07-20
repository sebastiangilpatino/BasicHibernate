package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AppPerson {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Person.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		openCreate3Person(session, tx);
		openRetrieveAllPrint(session, tx);
		openRetrieveChangePriceTitleDelete(session, tx);
		openRetrieveAllPrint(session, tx);
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
	
	public static void openCreate3Person(Session session, Transaction tx) {
		// Hibernate placeholders

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Person person1 = new Person();
			Person person2 = new Person();
			Person person3 = new Person();

			person1.setFirstname("Jorge");
			person1.setLastname("Gil");
			person1.setDateofbirth(LocalDate.now());
			
			person2.setFirstname("Maria");
			person2.setLastname("Estrada");
			person2.setDateofbirth(LocalDate.now());
			
			person3.setFirstname("Naem");
			person3.setLastname("Haddad");
			person3.setDateofbirth(LocalDate.now());

			session.persist(person1);
			session.persist(person2);
			session.persist(person3);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void openRetrieveAllPrint(Session session, Transaction tx) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all persons
			List<Person> personList = session.createQuery("from Person", Person.class).list();
			for (Person p : personList) {
				System.out.println(p);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void openRetrieveChangePriceTitleDelete(Session session, Transaction tx) {
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Person p1 = (Person) session.get(Person.class, 1L);
			p1.setFirstname("Sebastian");
			p1.setLastname("Patino");
			
			Person p2 = (Person) session.load(Person.class, 2L);
			session.delete(p2);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
