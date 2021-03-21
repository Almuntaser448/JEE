package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mediatek2021.*;
import persistantdata.document.*;
import persistantdata.utilisateurs.utilisateur;


public class MediatekData implements PersistentMediatek {
// Jean-François Brette 01/01/2018	
	Connection DataBase;
	
	/**
	 * Injection dynamique de la dépendance dans le package stable mediatek2021.
	 */
	static {
		Mediatek.getInstance().setData(new MediatekData());
	}

	@Override
	public List<Document> catalogue(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(int numDocument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		// TODO Auto-generated method stub
		
	}
}