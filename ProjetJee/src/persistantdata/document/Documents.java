package persistantdata.document;

import mediatek2021.Document;

public class Documents implements Document{
	int numDoc;
	String titre;
	String auteur;
	String emprunt;
	typeDocument typeDoc;
	
	public Documents (int numDoc, String titre, String auteur, int typeDoc, String emprunt) {
		this.numDoc = numDoc;
		this.titre = titre;
		this.auteur = auteur;
		this.typeDoc = typeSelector(typeDoc);
		this.emprunt = emprunt;
	}
	
	public Documents (int numDoc, String titre, String auteur, int typeDoc) {
		this.numDoc = numDoc;
		this.titre = titre;
		this.auteur = auteur;
		this.typeDoc = typeSelector(typeDoc);
		this.emprunt = null;
	}
	
	
	@Override
	public Object[] data() {
		Object[] documentData = {this.titre, this.auteur, this.emprunt, this.typeDoc, this.numDoc};
		return documentData;
	}
	
	
	static public typeDocument typeSelector(int typeDoc) {
		switch(typeDoc) {
			case 1 :
				return typeDocument.LIVRE;
			case 2 :
				return typeDocument.DVD;
			case 3 :
				return typeDocument.CD;
			default :
				return null;
		}
	}
}