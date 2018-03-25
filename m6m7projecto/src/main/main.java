package main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DAOManager;
import dao.DaoAsssistencies;
import dao.DaoClients;
import dao.DaoServeis;
import dao.DaoUsuaris;
import modeloH.Assistencies;
import modeloH.Clients;
import modeloH.Perfils;
import modeloH.Serveis;
import modeloH.Usuaris;

public class main {

	public static void main(String[] args) throws SQLException, ParseException {
		DaoUsuaris daoUser = DAOManager.getDaoUsuaris();
		DaoClients daocli = DAOManager.getDaoClients();
		DaoAsssistencies daoassist = DAOManager.getDaoAsssistencies();
		DaoServeis daoserv = DAOManager.getDaoServeis();
		/*
		//usuario con perfil
		Perfils perfil = new Perfils(10, "programer");
		Usuaris usuario = new Usuaris("admin", perfil, "admin", "administrador", "admin", "mail@mail.com",20,"programer");
		//daoUser.addUsuaris(usuario);
		//get usuario
		System.out.println(daoUser.getUsuarisById("admin"));

		//auto inc/ creamos clientes
		Clients client1 = new Clients("david", "moya", "655929229", "mail@sad.com");
		Clients client2 = new Clients("jose2", "moya2", "66666662", "mail@sad.com2");
		//daocli.addClients(client1);
		//daocli.addClients(client2);

		//creamos servicios
		Serveis serv = new Serveis(1,"servtest");
		//daoserv.addServeis(serv);
		//creamos assistencias
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Assistencies assist = new Assistencies(serv, usuario, daocli.getClientsById(2),sdf.parse("25/03/1994") ,"ss");
		//daoassist.addAssistencia(assist);

		//obtener assistencias
		for (Assistencies assis : daoassist.getAssistencies()) {
			System.out.println(assis.toString());
		}

		//test borrado cascade/ borramos cliente
		daocli.delClients(1);
		*/

		//update cliente 
		Clients client4 = new Clients(2, "david", "moya", "655929229", "mail@sad.com");

		daocli.updateClients(client4);


	}

}
