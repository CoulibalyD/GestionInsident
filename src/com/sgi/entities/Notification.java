package com.sgi.entities;

public class Notification {
	private int idNotification;
	private int idCreateur;
	private int idDestinateur;
	private int idIncident;
	private String dateCreation;
	private int nombreNote;
	private static int nombreNotification;
	public Notification(int idNotification, int idCreateur, int idDestinateur) {
		this.idNotification = idNotification;
		this.idCreateur = idCreateur;
		this.idDestinateur = idDestinateur;
		nombreNotification++;
	}
	
	public Notification(int idNotification, int idCreateur, int idDestinateur, int idIncident) {
		this(idNotification,idCreateur,idDestinateur);
		this.idIncident = idIncident;
	}
	
	public Notification(int idNotification, int idCreateur, int idDestinateur, int idIncident, String dateCreation) {
		this(idNotification,idCreateur,idDestinateur,idIncident);
		this.dateCreation = dateCreation;
	}
	
	public Notification(int idCreateur, int idDestinateur, int idIncident, String dateCreation) {
		this.idCreateur = idCreateur;
		this.idDestinateur = idDestinateur;
		this.idIncident = idIncident;
		this.dateCreation = dateCreation;
		nombreNotification++;
		
	}
	public Notification(int idCreateur, int idDestinateur, int idIncident, String dateCreation,int nombreNotification) {
		this.idCreateur = idCreateur;
		this.idDestinateur = idDestinateur;
		this.idIncident = idIncident;
		this.dateCreation = dateCreation;
		this.nombreNote = nombreNotification;
	}
	
	public int getNombreNote() {
		return nombreNote;
	}

	public void setNombreNote(int nombreNote) {
		this.nombreNote = nombreNote;
	}

	public int getIdNotification() {
		return idNotification;
	}
	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}
	public int getIdCreateur() {
		return idCreateur;
	}
	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}
	public int getIdDestinateur() {
		return idDestinateur;
	}
	public void setIdDestinateur(int idDestinateur) {
		this.idDestinateur = idDestinateur;
	}
	public int getIdIncident() {
		return idIncident;
	}
	public void setIdIncident(int idIncident) {
		this.idIncident = idIncident;
	}
	public static int getNombreNotification() {
		return nombreNotification;
	}
	public static void setNombreNotification(int nombreNotification) {
		Notification.nombreNotification = nombreNotification;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
