package dao;

import java.util.List;

import org.hibernate.HibernateException;

import modeloH.Clients;

public interface DaoClients {
	//crud
	public void addClients(Clients clients)throws HibernateException;
	public List<Clients> getClients()throws HibernateException;
	public Clients getClientsById(int id)throws HibernateException;
	public void updateClients(Clients clients)throws HibernateException;
	public void delClients(int id)throws HibernateException;
	//extras


}
