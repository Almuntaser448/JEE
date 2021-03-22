package persistantdata.utilisateurs;

import mediatek2021.Utilisateur;

public class utilisateur implements Utilisateur {

	String numUtilisateur;
	String nom;
	String password;
	typeUtili typeUtilisateur;
	
	/*
	 * Constructeur de  utilisateur.
	 * 
	 * @param matricule.
	 * @param login.
	 * @param nom.
	 * @param password.
	 * @param type, client ou libraire.
	 */
	public utilisateur(String numUtilisateur, String password, String nom, int typeUtilisateur) {
		this.numUtilisateur = numUtilisateur;
		this.password = password;
		this.nom = nom;
		this.typeUtilisateur = typeSelector(typeUtilisateur);
	}
	
	/*
	 * Constructeur du  client.
	 * 
	 * @param matricule.
	 * @param login.
	 * @param nom.
	 * @param password.
	 * @param type, client.
	 */
	public utilisateur(String numUtilisateur, String password, String nom) {
		this.numUtilisateur = numUtilisateur;
		this.password = password;
		this.nom = nom;
		this.typeUtilisateur = typeUtili.CLIENT;
	}
	
	
	@Override
	public String toString() {
		return this.numUtilisateur + " | " + this.nom + " | " + this.typeUtilisateur;
	}
	
	@Override
	public Object[] data() {
		Object[] utilisateurData = {this.numUtilisateur, this.password, this.nom, this.typeUtilisateur};
		return utilisateurData;
	}
	
	
	/* 
	 * @return, mot de passe.
	 */
	@Override
	public String password() {
		return this.password;
	}
	
	/*
	 * @return, le login.
	 */
	@Override
	public String login() {
		return this.numUtilisateur;
	}

	
	private typeUtili typeSelector(int typeUtilisateur) {
		switch(typeUtilisateur) {
			case 1 :
				return typeUtili.CLIENT;
			case 2 :
				return typeUtili.LIBRAIRE;
			default :
				return null;
		}
	}

}