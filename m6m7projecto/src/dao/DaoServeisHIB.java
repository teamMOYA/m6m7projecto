package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modeloH.Serveis;

public class DaoServeisHIB implements DaoServeis {
	private SessionFactory sessionFactory = null;
	private Transaction tx = null;

	public DaoServeisHIB() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();

	}

	@Override
	public void addServeis(Serveis serveis) throws HibernateException {
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.save(serveis);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();

			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public List<Serveis> getServeis() throws HibernateException {
		Session session = sessionFactory.openSession();
		List<Serveis> serveis = null;

		try {
			tx = session.beginTransaction();

			serveis =  session.createQuery("From Serveis").list();


			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();

			throw e;
		} finally {
			session.close();
		}
		return serveis;
	}

	@Override
	public Serveis getServeisById(int id) throws HibernateException {
		Session session = sessionFactory.openSession();
		Serveis serveis = null;

		try {
			tx = session.beginTransaction();

			serveis =(Serveis)session.get(Serveis.class, (Integer)id);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();

			throw e;
		} finally {
			session.close();
		}
		return serveis;
	}

	@Override
	public void updateServeis(Serveis serveis) throws HibernateException {
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.update(serveis);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void delServeis(int id) throws HibernateException {
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			Serveis serveis = (Serveis)session.get(Serveis.class, (Integer)id);
			session.delete(serveis);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

}
