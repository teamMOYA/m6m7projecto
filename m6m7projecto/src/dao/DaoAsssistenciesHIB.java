package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modeloH.Assistencies;

public class DaoAsssistenciesHIB implements DaoAsssistencies {
	private SessionFactory sessionFactory = null;
	private Transaction tx = null;

	public DaoAsssistenciesHIB() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}
	@Override
	public void addAssistencia(Assistencies assistencies) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.save(assistencies);


			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Assistencies> getAssistencies() throws HibernateException{
		Session session = sessionFactory.openSession();
		List<Assistencies> assistencies = null;

		try {
			tx = session.beginTransaction();

			assistencies =  session.createQuery("From Assistencies").list();


			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return assistencies;
	}

	@Override
	public Assistencies getAssistenciesById(int id) throws HibernateException{
		Session session = sessionFactory.openSession();
		Assistencies assistencia = null;

		try {
			tx = session.beginTransaction();

			assistencia = session.get(Assistencies.class, (Integer)id);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)tx.rollback();

			throw e;
		} finally {
			session.close();
		}
		return assistencia;
	}

	@Override
	public void updateAssistencia(Assistencies assistencies) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			session.update(assistencies);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void delAssistencia(int id) throws HibernateException{
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			Assistencies dep = (Assistencies)session.get(Assistencies.class, (Integer)id);
			session.delete(dep);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

}
