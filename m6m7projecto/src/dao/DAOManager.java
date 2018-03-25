package dao;

import java.sql.SQLException;

public class DAOManager {

	private static DaoAsssistencies DaoAsssistencies;
	private static DaoClients DaoClients;
	private static DaoServeis DaoServeis;
	private static DaoUsuaris DaoUsuaris;

	public static DaoAsssistencies getDaoAsssistencies() {
		if(DaoAsssistencies == null){
			DaoAsssistencies = new DaoAsssistenciesHIB();
		}
		return DaoAsssistencies;
	}

	public static DaoClients getDaoClients() {
		if(DaoClients == null){
			DaoClients = new DaoClientsHIB();
		}
		return DaoClients;
	}

	public static DaoServeis getDaoServeis() {
		if(DaoServeis == null){
			DaoServeis = new DaoServeisHIB();
		}
		return DaoServeis;
	}

	public static DaoUsuaris getDaoUsuaris() throws SQLException{
		if(DaoUsuaris == null){
			DaoUsuaris = new DaoUsuarisJDBC();
		}
		return DaoUsuaris;
	}



}
