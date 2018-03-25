package dao;

import java.util.List;

import org.hibernate.HibernateException;

import modeloH.Assistencies;

public interface DaoAsssistencies {
	//crud
	public void addAssistencia(Assistencies assistencies)throws HibernateException;
	public List<Assistencies> getAssistencies()throws HibernateException;
	public Assistencies getAssistenciesById(int id)throws HibernateException;
	public void updateAssistencia(Assistencies assistencies)throws HibernateException;
	public void delAssistencia(int id)throws HibernateException;
	//extras
}
