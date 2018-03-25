package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modeloH.Clients;

public class DaoClientsHIB implements DaoClients {
	private SessionFactory sessionFactory = null;
	private Transaction tx = null;

	public DaoClientsHIB() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();

	}
	@Override
	public void addClients(Clients clients) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.save(clients);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();

			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public List<Clients> getClients() throws HibernateException{
		Session session = sessionFactory.openSession();
		List<Clients> clientes = null;

		try {
			tx = session.beginTransaction();

			clientes =  session.createQuery("From Clients").list();


			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();

			throw e;
		} finally {
			session.close();
		}
		return clientes;
	}

	@Override
	public Clients getClientsById(int id) throws HibernateException{
		Session session = sessionFactory.openSession();
		Clients client = null;

		try {
			tx = session.beginTransaction();

			client =(Clients)session.get(Clients.class, (Integer)id);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();

			throw e;
		} finally {
			session.close();
		}
		return client;
	}

	@Override
	public void updateClients(Clients clients) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.update(clients);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void delClients(int id) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			Clients clients = (Clients)session.get(Clients.class, (Integer)id);
			session.delete(clients);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

}
