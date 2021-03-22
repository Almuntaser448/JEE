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

// documentation  https://docs.oracle.com/cd/E17781_01/appdev.112/e18805/getconn.htm#TDPJD127

public class MediatekData implements PersistentMediatek {
// Jean-François Brette 01/01/2018	
	
	Connection BDD;
	
	/**
	 * Injection dynamique de la dépendance dans le package stable mediatek2021.
	 */
	static {
		
		Mediatek.getInstance().setData(new MediatekData());
	}

	private MediatekData() {
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("OracleDriver ok");
		} catch (ClassNotFoundException e) {
			System.err.println("Class non trouver");
			e.printStackTrace();
			
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez l'identifiant : ");
		String login = sc.next();
		System.out.print("Entrez le mot de passe : ");
		String password = sc.next();
		sc.close();
		
		try {
			
			this.BDD = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", login, password);
			System.out.println("Connexion ok");
		} catch (SQLException e) {
			System.err.println("Erreur connexion");
			e.printStackTrace();
		}
	}
	
	
	//retoure la liste des documents
	
	@Override
	public List<Document> catalogue(int type) {
		// TODO Auto-generated method stub
		
		System.out.println("Liste de docs");
		List<Document> documentList = new ArrayList<Document>();
		String sql = "SELECT * FROM DOCUMENT";
		PreparedStatement prep;
		ResultSet res;
		
		try {
			
			synchronized (BDD) {
				prep = BDD.prepareStatement(sql);
				res = prep.executeQuery();
				
			}
			
			System.out.println("Instruction ok");
			while (res.next()) {
				Document d = new Documents(res.getInt("NumDoc"), res.getString("Titre"), res.getString("Auteur"),
										res.getInt("TypeDocument"), res.getString("Emprunt"));
				if (d != null) {
					
					System.out.println("Document : " + res.getString("Titre") + " de " + res.getString("Auteur"));
					documentList.add(d);
				}
			}
			
			prep.close();
			res.close();
			
		} catch (SQLException e) {
			System.err.println("Une erreur dans l'instruction suivante : " + sql);
		}
		
		System.out.println("Un total de : " + documentList.size() + " document");
		return documentList;
	}

	@Override
	public Document getDocument(int numDocument) {
		// TODO Auto-generated method stub
		System.out.println("Chercher le n° " + numDocument);
		Document rechDoc = null;
		String sql = "SELECT * FROM DOCUMENT WHERE NumDoc = " + numDocument;
		PreparedStatement prep;
		ResultSet res;
		
		try {
			
			synchronized (BDD) {
				prep = BDD.prepareStatement(sql);
				res = prep.executeQuery();
				
			}
			
			System.out.println("Succès");
			if (res.next()) {
				
				System.out.println("Document n°" + numDocument + " " + res.getString("Titre") + " de " + res.getString("Auteur"));
				rechDoc = new Documents(numDocument, res.getString("Titre"), res.getString("Auteur"),
							res.getInt("TypeDocument"), res.getString("Emprunt"));
			}
			
			prep.close();
			res.close();
			
		} catch (SQLException e) {
			System.err.println("Une erreur a l'instruction suivante : " + sql);
			
		}
		
		return rechDoc;
	}

	//récupère l'utilisateur et le retoure
	
	@Override
	public Utilisateur getUser(String login, String password) {
		// TODO Auto-generated method stub
		System.out.println("Utilisateur " + login);
		Utilisateur rechUtilisateur = null;
		String sql = "SELECT * FROM UTILISATEUR WHERE NumUtilisateur = '" + login + "' AND Password = '" + password + "'";
		PreparedStatement prep;
		ResultSet res;
		
		try {
			
			synchronized (BDD) {
				prep = BDD.prepareStatement(sql);
				res = prep.executeQuery();
				
			}
			
			System.out.println("Instruction ok");
			
			if (res.next()) {
				System.out.println("Utilisateur " + login + " Nom: " + res.getString("Nom"));
				rechUtilisateur = new utilisateur(login, res.getString("Nom"), password, res.getInt("TypeUtilisateur"));
			}
			
			prep.close();
			res.close();
			
		} catch (SQLException e) {
			System.err.println("Une erreur a l'instruction suivante : " + sql);
		}
		
		return rechUtilisateur;
	}

	private static String normalizeString(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		return string;
	}
	
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			throw new NewDocException("Il manque le Titre ou l'Auteur");
		} else if (args[0] == "" || args[1] == "") {
			throw new NewDocException("Des arguments vide");
		} else if (type > 3 || type < 1) {
			throw new NewDocException("Type non existent");
		}
		
		args[0] = normalizeString((String) args[0]);
		args[1] = normalizeString((String) args[0]);
		System.out.println("Nouveau doc (" + Documents.typeSelector(type) + ": " + args[0] + " de " + args[1]);
		String sql = "INSERT INTO DOCUMENT (NumDoc, Titre, Auteur, TypeDocument) VALUES(SeqDoc.NEXTVAL, '" + args[0]
				+ "', '" + args[1] + "', " + type + ")";
		
		try {
			
			synchronized (BDD) {
				PreparedStatement prep = BDD.prepareStatement(sql);
				prep.execute();
				prep.close();
			}
			
			System.out.println("Insctruction ok");
			
		} catch (SQLException e) {
			System.err.println("Encrore une erreur d'instruction suivante : " + sql);
		}
	}

	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		// TODO Auto-generated method stub
		
		Object[] documentData = getDocument(numDoc).data();
		if (documentData[2] != null) {
			throw new SuppressException("Document emprunter, donc impossible de le supprimer");
		}
		
		System.out.println("Supprimer le document n° " + numDoc);
		String sql = "DELETE FROM DOCUMENT WHERE NumDoc = " + numDoc;
		
		try {
			
			synchronized (BDD) {
				PreparedStatement prep = BDD.prepareStatement(sql);
				prep.execute();
				prep.close();
				
			}
			
			System.out.println("Inscruction ok");
		} catch (SQLException e) {
			System.err.println("Encore et toujour une erreur a : " + sql);
		}
	}
}