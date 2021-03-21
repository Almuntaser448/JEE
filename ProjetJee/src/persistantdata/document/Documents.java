package persistantdata.document;

import mediatek2021.Document;

public class Documents implements Document{
	int numDoc;
	String titre;
	String auteur;
	typeDocument docType;
	String emprunt;
	
	public Documents (int numDoc, String titre, String auteur, int docType, String emprunt) {
		this.numDoc = numDoc;
		this.titre = titre;
		this.auteur = auteur;
		this.docType = typeDocumentelector(docType);
		this.emprunt = emprunt;
	}
	
	public Documents (int numDoc, String titre, String auteur, int docType) {
		this.numDoc = numDoc;
		this.titre = titre;
		this.auteur = auteur;
		this.docType = typeDocumentelector(docType);
		this.emprunt = null;
	}
	
	public Documents (String titre, String auteur, int docType) {
		this.numDoc = -1;
		this.titre = titre;
		this.auteur = auteur;
		this.docType = typeDocumentelector(docType);
		this.emprunt = null;
	}

	@Override
	public Object[] data() {
		Object[] documentData = {this.titre, this.auteur, this.emprunt, this.docType, this.numDoc};
		return documentData;
	}
	
	@Override
	public String toString() {
		return this.numDoc + " | " + this.docType + " | " + this.titre + " | " + this.auteur;
	}
	
	static public typeDocument typeDocumentelector(int docType) {
		switch(docType) {
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