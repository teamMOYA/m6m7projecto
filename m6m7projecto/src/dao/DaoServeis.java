package dao;

import java.util.List;

import org.hibernate.HibernateException;

import modeloH.Serveis;

public interface DaoServeis {
	//crud
	public void addServeis(Serveis serveis)throws HibernateException;
	public List<Serveis> getServeis()throws HibernateException;
	public Serveis getServeisById(int id)throws HibernateException;
	public void updateServeis(Serveis serveis)throws HibernateException;
	public void delServeis(int id)throws HibernateException;
	//extras

}
