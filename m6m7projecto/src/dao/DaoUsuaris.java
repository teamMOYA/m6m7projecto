package dao;

import java.sql.SQLException;
import java.util.List;

import modeloH.Perfils;
import modeloH.Usuaris;

public interface DaoUsuaris {
	//crud usuaris
	public void addUsuaris(Usuaris usuaris) throws SQLException;
	public List<Usuaris> getUsuaris() throws SQLException;
	public Usuaris getUsuarisById(String idUsuari) throws SQLException;
	public void updateUsuaris(Usuaris usuaris) throws SQLException;
	public void delUsuaris(Usuaris usuaris) throws SQLException;
	//crud perfils
	public void addPerfils(Perfils perfils);
	public List<Perfils> getPerfils() throws SQLException;
	public Perfils getPerfilsById(int id) throws SQLException;
	public void updatePerfils(Perfils perfils);
	public void delPerfils(Perfils perfils);
	//extra

}
