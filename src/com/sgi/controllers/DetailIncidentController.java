package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.entities.Statut;
import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UIDetailIncident;
import com.sgi.ui.UIListe;
import com.sgi.ui.UIVisualiserIncident;
import com.sgi.utils.Utilitaire;

public class DetailIncidentController {
	
	private UIDetailIncident uiDetailIncident;
	private UIAuthentification  uiauthentification;
	public DetailIncidentController(UIDetailIncident uiDetailIncident,UIAuthentification  _uiauthentification) {
		this.uiDetailIncident = uiDetailIncident;
		this.uiauthentification = _uiauthentification;
		int idIncident = uiDetailIncident.getIdIncident();
		List<Note> notes;
		try {
			notes = Service.listerNotes (idIncident);
			uiDetailIncident.afficherLesNotes (notes);
			
		} catch (Exception e) {
			Utilitaire.displayErrorMessage("Erreur au chargement des notes : " + e.getMessage());
		}				
		
		addListeners ();
	}
	
	private void addListeners () {
		
		uiDetailIncident.addAjouterNouvelleNoteListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = uiDetailIncident.getNouvelleNote();								
				
				if (message.equals("")) return;
				
				try {
					User user = uiauthentification.getUserConnected();
					List<Note> notes;
					Note note = new Note (uiDetailIncident.getIdIncident(), 
							user.getId(),
								message, 
								Utilitaire.getCurrentTime());
					
					Service.creerNote (note);
					uiDetailIncident.effacerLeChampNouvelleNote();
					
					notes = Service.listerNotes (uiDetailIncident.getIdIncident());
					uiDetailIncident.afficherLesNotes (notes);
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error " + e1.getMessage());
				}
				
			}
		});
		
		uiDetailIncident.addAssignerListener (new ActionListener() {
			
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<User> list ;
				int  id = uiDetailIncident.getIdIncident();
				
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut() == "ASSIGNED") {
						Utilitaire.displayErrorMessage("Desole cet Incident a ete assigner....");
					}else
					{
						list = Service.listerDeveloppeur();
						UIListe liste = new UIListe();
						liste.loadUser(list);
						uiDetailIncident.hide();
						liste.montre();
						liste.AddOuvrirListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								liste.hide();
								int id = liste.getUserId();
								try {
									if(incident.getStatut().compareTo("ASSIGNED") == 0) {
										Utilitaire.displayErrorMessage("Desole cet Incident a ete ASSIGNED....");
									}else if(incident.getStatut().compareTo("EN_ATTENTE") == 0)
									{
										Utilitaire.displayNotification("Impossible d'assigner incident car il est a etat EN_ATTENTE....");
									}else if(incident.getStatut().compareTo("RESOLU") == 0)
									{
										Utilitaire.displayNotification("Impossible d'assigner incident car il est a etat RESOLU....");
									}else if(incident.getStatut().compareTo("CLOSED") == 0)
									{
										Utilitaire.displayNotification("IMPOSSIBLE D'ASSIGNER CET INCIDENT CAR IL EST CLOTURE....");
									}else 
									{
										User user = Service.giveUser(id);
										incident.setIdDeveloppeur(user.getId());
										incident.setStatut(Statut.ASSIGNED);
										Service.updateIncident(incident);
										Utilitaire.displayNotification("L'incident est bien assigne au developpeur \n\t Nom: "+user.getNom() +"\n\t Prenom :"+ user.getPrenom()+"\n\t"+ " avec success...");
										uiDetailIncident.remplirDetail(incident);
										uiDetailIncident.show();
									}
								} catch (Exception e1) {
									Utilitaire.displayErrorMessage("ERROR :"+ e1.getMessage());
								}
							}
							
						});
						liste.AddFermerListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								liste.dispose();	
							}
							
						});
					}
					} catch (Exception e1) {
						Utilitaire.displayErrorMessage("Error :" + e1.getMessage());
					}	
			}
		});
		uiDetailIncident.addValiderRapportteurListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				int  id = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut().compareTo("ASSIGNED") == 0) {
						Utilitaire.displayNotification("Desole l'incident est deja à etat EN_ATTENTE--....");
					}else if(incident.getStatut().compareTo("NOUVEAU") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE NOUVEAU CAR INCIDENT EST A ETAT EN_ATTENTE....");
					}else if(incident.getStatut().compareTo("RE_OUVERT") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE RE_OUVERT CAR INCIDENT EST A ETAT EN_ATTENTE....");
					}else if(incident.getStatut().compareTo("CLOSED") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE CLOSED CAR INCIDENT EST A ETAT EN_ATTENTE....");
					}else  if(incident.getStatut().compareTo("RESOLU") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE RESOLU  CAR INCIDENT EST A ETAT EN_ATTENTE....");
					}else
					{
						@SuppressWarnings("unused")
						User user = uiauthentification.getUserConnected();
						incident.setStatut(Statut.ASSIGNED);
						incident.setApplication(uiDetailIncident.gettFApplication().getText());
						incident.setDescription(uiDetailIncident.gettADescription().getText());
						Service.updateIncidents(incident);
						Utilitaire.displayNotification("L'incident est mise en attente avec success...");
						uiDetailIncident.remplirDetail(incident);
						uiDetailIncident.show();
					}
					uiDetailIncident.show();
				} catch (Exception e1) {
					Utilitaire.displayNotification("Error :" + e1.getMessage());
				} 
				
			
				
			}
			
		});
		uiDetailIncident.addAttenteListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				int  id = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut().compareTo("EN_ATTENTE") == 0) {
						Utilitaire.displayNotification("Desole l'incident est deja à etat EN_ATTENTE....");
					}else if(incident.getStatut().compareTo("NOUVEAU") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE mettre EN_ATTENTE CAR INCIDENT EST A ETAT NOUVEAU....");
					}else if(incident.getStatut().compareTo("RE_OUVERT") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE mettre EN_ATTENTE CAR INCIDENT EST A ETAT ATTENT....");
					}else if(incident.getStatut().compareTo("CLOSED") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE mettre EN_ATTENTE CAR INCIDENT EST A ETAT CLOTURE....");
					}else  if(incident.getStatut().compareTo("RESOLU") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE mettre EN_ATTENTE CAR INCIDENT EST A ETAT CLOTURE....");
					}else
					{
						User user = uiauthentification.getUserConnected();
						incident.setStatut(Statut.EN_ATTENTE);
						incident.setIdDeveloppeur(user.getId());
						Service.updateIncident(incident);
						Utilitaire.displayNotification("L'incident est mise en attente avec success...");
						uiDetailIncident.remplirDetail(incident);
						uiDetailIncident.show();
					}
					uiDetailIncident.show();
				} catch (Exception e1) {
					Utilitaire.displayNotification("Error :" + e1.getMessage());
				} 
				
			}
			
		});
		uiDetailIncident.addResolueListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				int  id = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut().compareTo("RESOLU") == 0) {
						Utilitaire.displayNotification("Desole l'incident est deja Resolue....");
					}else if(incident.getStatut().compareTo("NOUVEAU") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE RESOLU CAR INCIDENT EST A ETAT NOUVEAU....");
					}else if(incident.getStatut().compareTo("EN_ATTENTE") == 0)
					{	
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE RESOLU CAR INCIDENT EST A ETAT ATTENT....");
					}else if(incident.getStatut().compareTo("CLOSED") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE RESOLU CAR INCIDENT EST A ETAT CLOTURE....");
					}else  if(incident.getStatut().compareTo("RE_OUVERT") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE RESOLU CAR INCIDENT EST A ETAT CLOTURE....");
					}else
					{
						User user  = uiauthentification.getUserConnected();
						incident.setStatut(Statut.RESOLU);
						incident.setIdDeveloppeur(user.getId());
						Service.updateIncident(incident);
						Utilitaire.displayNotification("L'incident est RESOLU  avec success...");
						uiDetailIncident.remplirDetail(incident);
						uiDetailIncident.show();
					}
					uiDetailIncident.show();
				} catch (Exception e1) {
					Utilitaire.displayNotification("Error :" + e1.getMessage());
				}
			}
			
		});
		uiDetailIncident.addReouvertListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int  id = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut().compareTo("RE_OUVERT") == 0) {
						Utilitaire.displayNotification("Desole l'incident est deja Reouvert....");
					}else if(incident.getStatut().compareTo("NOUVEAU") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE RE_OUVERT CAR INCIDENT EST A ETAT NOUVEAU....");
					}else if(incident.getStatut().compareTo("EN_ATTENTE") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE RE_OUVERT CAR INCIDENT EST A ETAT ATTENT....");
					}else if(incident.getStatut().compareTo("CLOSED") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE RE_OUVERT CAR INCIDENT EST A ETAT CLOTURE....");
					}else  if(incident.getStatut().compareTo("ASSIGNED") == 0 ) {
						Utilitaire.displayErrorMessage("IMPOSSIIBLE DE RE_OUVERT CAR INCIDENT EST A ETAT CLOTURE....");
					}else
					{
						incident.setStatut(Statut.RE_OUVERT);
						incident.setIdDeveloppeur(0);
						Service.updateIncident(incident);
						Utilitaire.displayNotification("L'incident est Reouvrir avec success...");
						uiDetailIncident.remplirDetail(incident);
						uiDetailIncident.show();
					}
					uiDetailIncident.show();
				} catch (Exception e1) {
					Utilitaire.displayNotification("Error :" + e1.getMessage());
				}
			}
			
		});
		uiDetailIncident.addCloturerListener (new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				int  id = uiDetailIncident.getIdIncident();
				try {
					Incident incident = Service.giveIncident(id);
					if(incident.getStatut().compareTo("CLOSED") == 0 ) {
						Utilitaire.displayNotification("Desole cet Incident a ete Cloture....");
					}else if(incident.getStatut().compareTo("NOUVEAU") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE CLOTURE CAR INCIDENT EST A ETAT NOUVEAU....");
					}else if(incident.getStatut().compareTo("EN_ATTENTE") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE CLOTURE CAR INCIDENT EST A ETAT ATTENT....");
					}else if(incident.getStatut().compareTo("RE_OUVERT") == 0)
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE CLOTURE CAR INCIDENT EST A ETAT RE_OUVERT....");
					}else if(incident.getStatut().compareTo("ASSIGNED") == 0 )
					{
						Utilitaire.displayErrorMessage("IMPOSSIBLE DE CLOTURE CAR INCIDENT EST A ETAT ASSIGNE....");
					}else
					{
						incident.setIdDeveloppeur(incident.getIdDeveloppeur());
						incident.setStatut(Statut.CLOSED);
						incident.setDateCloture(Utilitaire.getCurrentTime());
						Service.updateIncident(incident);
						Utilitaire.displayNotification("Cloture effectue avec success....");
						uiDetailIncident.remplirDetail(incident);
						uiDetailIncident.show();
					}
					uiDetailIncident.show();
					} catch (Exception e1) {
						Utilitaire.displayErrorMessage("Error :" + e1.getMessage());
					}
			}
			
		});
		uiDetailIncident.addQuitterListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiDetailIncident.dispose();
				UIVisualiserIncident uivisualiserIncident = new UIVisualiserIncident();
				VisualiserIncidentController selectionControllers = new VisualiserIncidentController (uivisualiserIncident,uiauthentification);
				selectionControllers.run ();
				
			}
			
		});
	}

	public void run() {
		uiDetailIncident.setVisible(true);
	}
}
