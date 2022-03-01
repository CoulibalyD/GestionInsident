package com.sgi.entities;

import java.util.List;

public class Incident {
	
	private int id;
	private int idRapporteur;
	private int idDeveloppeur;
	private String datecreation;
	private String dateCloture;


	private String description;
	private String application;
	private String gravite;
	
	private Statut statut;
	//private String statut;
	List<Note> notes;
	
	public Incident(String description, String application, String gravite) {
		this.description = description;
		this.application = application;
		this.gravite = gravite;
		
		this.statut = Statut.NOUVEAU;
	}
	public Incident(int id,String description, String application, String gravite) {
		this(description,application,gravite);
		this.id = id;
	}
	public Incident(int id,String description, String application, String gravite,String status) {
		this(description,application,gravite);
		this.id = id;
		this.statut = Statut.valueOf(status);
	}
	public Incident(int id,int idRapoortteur,String description, String application, String gravite) {
		this( id,description,application,gravite);
		this.idRapporteur = idRapoortteur;
	}
	public Incident(String description, String application, String gravite,String dateCreation) {
		this(description,application,gravite);
		this.datecreation = dateCreation;
	}
	public Incident(int idRapporteur,int idDeveloppeur, String description, String application, String gravite,String dateCreation) {
		this(description, application, gravite,dateCreation);
		this.idRapporteur = idRapporteur;
		this.idDeveloppeur = idDeveloppeur;
	}
	public Incident(int _id,int idRapporteur,int idDeveloppeur, String description, String application, String gravite,String dateCreation) {
		this(idRapporteur,idDeveloppeur,description, application, gravite,dateCreation);
		this.id = _id;
	}
	public Incident(int _id,int idRapporteur,int idDeveloppeur, String description, String application, String gravite,String dateCreation,String dateCloture) {
		this(_id,idRapporteur,idDeveloppeur,description, application, gravite,dateCreation);
		this.dateCloture = dateCloture;
	}
	public Incident(int _id,int idRapporteur,int idDeveloppeur, String description, String application, String gravite,String dateCreation,String dateCloture,String statut) {
		//this(_id,idRapporteur,idDeveloppeur,description, application, gravite,dateCreation,dateCloture);
		this.id = _id;
		this.idRapporteur = idRapporteur;
		this.idDeveloppeur = idDeveloppeur;
		this.description = description;
		this.application = application;
		this.gravite = gravite;
		this.datecreation = dateCreation;
		this.dateCloture = dateCloture;
		this.statut = Statut.valueOf(statut);
	}
	public String getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(String dateCloture) {
		this.dateCloture = dateCloture;
	}

	public String getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}


	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public void setGravite(String gravite) {
		this.gravite = gravite;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getApplication() {
		return application;
	}
	
	public String getGravite() {
		return gravite;
	}
	
	public void setIdDeveloppeur(int idDeveloppeur) {
		this.idDeveloppeur = idDeveloppeur;
	}
	
	public void setIdRapporteur(int idRapporteur) {
		this.idRapporteur = idRapporteur;
	}
	
	public int getIdDeveloppeur() {
		return idDeveloppeur;
	}
	
	public int getIdRapporteur() {
		return idRapporteur;
	}

	public String getStatut() {
		return statut.name();
	}

}

