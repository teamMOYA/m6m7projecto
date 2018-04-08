package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modeloH.Perfils;
import modeloH.Usuaris;

public class DaoUsuarisJDBC implements DaoUsuaris {
	private Connection conexion;



	public DaoUsuarisJDBC() throws SQLException{
		conexion = GestorConnexions.obtenirConnexio();
	}

	@Override
	public void addUsuaris(Usuaris usuaris) throws SQLException {
		conexion = GestorConnexions.obtenirConnexio();

		String sql = "insert into usuaris values (?,?,?,?,?,?,?,?)";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, usuaris.getIdUsuari());
		sentencia.setString(2, usuaris.getPassword());
		sentencia.setString(3, usuaris.getNom());
		sentencia.setString(4, usuaris.getCognoms());
		sentencia.setString(5, usuaris.getCognoms());
		sentencia.setInt(6, usuaris.getPerfils().getCodi());
		sentencia.setInt(7, usuaris.getNumcolegiat());
		sentencia.setString(8, usuaris.getEspecialitat());

		sentencia.executeUpdate();

	}

	@Override
	public List<Usuaris> getUsuaris() throws SQLException {
		conexion = GestorConnexions.obtenirConnexio();

		String sql = "SELECT idUsuari FROM usuaris";
		PreparedStatement sentencia = conexion.prepareStatement(sql);

		ResultSet resultat = sentencia.executeQuery();

		List<Usuaris> llistaUsuaris = new LinkedList<>();
		while (resultat.next()){
			llistaUsuaris.add(this.getUsuarisById(resultat.getString(1)));
		}
		return llistaUsuaris;
	}

	@Override
	public Usuaris getUsuarisById(String idUsuari) throws SQLException {
		conexion = GestorConnexions.obtenirConnexio();

		Usuaris usuari= null;

		String sql = "SELECT * FROM usuaris WHERE idUsuari = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, idUsuari);
		ResultSet resultat = sentencia.executeQuery();

		while (resultat.next()){
			usuari = new Usuaris();
			usuari.setIdUsuari(resultat.getString(1));
			usuari.setPassword(resultat.getString(2));
			usuari.setNom(resultat.getString(3));
			usuari.setCognoms(resultat.getString(4));
			usuari.setCorreu(resultat.getString(5));
			usuari.setPerfils(this.getPerfilsById(resultat.getInt(6)));
			usuari.setNumcolegiat(resultat.getInt(7));
			usuari.setEspecialitat(resultat.getString(8));
		}

		return usuari;
	}

	@Override
	public void updateUsuaris(Usuaris usuaris) throws SQLException {
		String sql = "update usuaris set password=?,nom=?,cognoms=?,correu=?,perfil=?,numcolegiat=?,especialitat=? where idUsuari=?";

		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, usuaris.getPassword());
		sentencia.setString(2, usuaris.getNom());
		sentencia.setString(3, usuaris.getCognoms());
		sentencia.setString(4, usuaris.getCorreu());
		sentencia.setInt(5, usuaris.getPerfils().getCodi());
		sentencia.setInt(6, usuaris.getNumcolegiat());
		sentencia.setString(7, usuaris.getEspecialitat());
		sentencia.setString(8, usuaris.getIdUsuari());

		sentencia.executeUpdate();

	}

	@Override
	public void delUsuaris(Usuaris usuaris) throws SQLException {
		String sql = "delete from usuaris where idUsuari= ?";

		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setString(1, usuaris.getIdUsuari());

		sentencia.executeUpdate();

	}

	@Override
	public Perfils getPerfilsById(int id) throws SQLException {
		conexion = GestorConnexions.obtenirConnexio();

		Perfils perfil= null;

		String sql = "SELECT * FROM perfils WHERE codi = ?";
		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, id);
		ResultSet resultat = sentencia.executeQuery();

		while (resultat.next()){
			perfil = new Perfils();
			perfil.setCodi(resultat.getInt(1));
			perfil.setDescripcio(resultat.getString(2));

		}
		return perfil;
	}

	@Override
	public void addPerfils(Perfils perfils) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Perfils> getPerfils() throws SQLException {
		conexion = GestorConnexions.obtenirConnexio();

		String sql = "SELECT codi FROM perfils";
		PreparedStatement sentencia = conexion.prepareStatement(sql);

		ResultSet resultat = sentencia.executeQuery();

		List<Perfils> llistaUsuaris = new LinkedList<>();
		while (resultat.next()){
			llistaUsuaris.add(this.getPerfilsById(resultat.getInt(1)));
		}
		return llistaUsuaris;
	}

	@Override
	public void updatePerfils(Perfils perfils) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delPerfils(Perfils perfils) {
		// TODO Auto-generated method stub

	}

}
