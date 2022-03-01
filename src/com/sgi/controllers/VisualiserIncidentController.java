package com.sgi.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UIDetailIncident;
import com.sgi.ui.UIVisualiserIncident;
import com.sgi.utils.Utilitaire;

public class VisualiserIncidentController {
	private UIVisualiserIncident uiVisualiserIncident;
	private UIAuthentification  uiauthentification;
	@SuppressWarnings("unused")
	public VisualiserIncidentController(UIVisualiserIncident uiVisualiserIncident,UIAuthentification  _uiauthentification) {
		this.uiVisualiserIncident = uiVisualiserIncident;
		this.uiauthentification = _uiauthentification;
		User user = uiauthentification.getUserConnected();
		List<Incident> incidents;
		
		List<Incident> listeIcidentNouvea;
		List<Incident> listeIcidentResolu;
		try {
			if(user.getType().compareTo("DEVELOPPEUR")==0) {
				int idDeveloppeur = user.getId();
				incidents = Service.listeIncidentsDev(idDeveloppeur);
				listeIcidentNouvea = Service.listeIncidentAssigned(idDeveloppeur);
				Utilitaire.displayNotification("Vous avez recu \n***\n" +listeIcidentNouvea.size()+ "\n *** \n Incident Assigne");
				uiVisualiserIncident.loadIncident(incidents);
			}else if(user.getType().compareTo("RAPPORTEUR")== 0) {
				int idRapporteur = user.getId();
				incidents = Service.listeIncidentsRapporteur(idRapporteur);
				listeIcidentNouvea = Service.listeIncidentNouveauRapporteur(idRapporteur);
				Utilitaire.displayNotification("Vous avez creer \n***\n" +listeIcidentNouvea.size()+ "\n *** \n Incident Nouveau");
				uiVisualiserIncident.loadIncident(incidents);
			}else {
				int idResponsable = user.getId();
				listeIcidentNouvea = Service.listeIncidentNouvea();
				incidents = Service.listerIncidents ();
				//listeIcidentResolu = Service.listeIncidentResolu(idResponsable);
				Utilitaire.displayNotification("Vous avez recu \n *** \n" + listeIcidentNouvea.size() + "\n *** \nouveau Incident" );
				uiVisualiserIncident.loadIncident(incidents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		addListeners();
	}
	private void addListeners () {
		uiVisualiserIncident.AddOuvrirListener (new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				uiVisualiserIncident.hide();
				int idIncident = uiVisualiserIncident.getSelectedIncidentId ();	
				User user = uiauthentification.getUserConnected();
				try {
                      if(user.getType().compareTo("RESPONSABLE")== 0) {
                    		UIDetailIncident uiDetailIncident;
                    		uiVisualiserIncident.hide();
                    		Incident incident = Service.giveIncident(idIncident);
    						uiDetailIncident = new UIDetailIncident(incident);
    						uiDetailIncident.remplirDetail(Service.giveIncident(idIncident));
    						uiDetailIncident.caherCombox();
    						uiDetailIncident.cacherValider();
    						uiDetailIncident.caherResoluAttente();
    						DetailIncidentController detailIncidentController = 
    									new DetailIncidentController (uiDetailIncident,uiauthentification);
    								detailIncidentController.run (); 
                      }else if(user.getType().compareTo("RAPPORTEUR")== 0)
                    	  {
                    	  UIDetailIncident uiDetailIncident;
  						uiVisualiserIncident.hide();
  						uiDetailIncident = new UIDetailIncident(Service.giveIncident(idIncident));
  						Incident incident = Service.giveIncident(idIncident);
  						if(incident.getStatut().compareTo("EN_ATTENTE")==0) {
  							uiDetailIncident.remplirDetails(Service.giveIncident(idIncident));
  	  						uiDetailIncident.fermerChamps();
  	  						uiDetailIncident.caherResoluAttente();
  	  						
  	  						DetailIncidentController detailIncidentController = 
  	  									new DetailIncidentController (uiDetailIncident,uiauthentification);
  	  								detailIncidentController.run (); 
  						}else {
  						uiDetailIncident.remplirDetail(Service.giveIncident(idIncident));
  						uiDetailIncident.fermerChamps();
  						uiDetailIncident.caherResoluAttente();
  						
  						DetailIncidentController detailIncidentController = 
  									new DetailIncidentController (uiDetailIncident,uiauthentification);
  								detailIncidentController.run (); 
  						}
                      }else {
						UIDetailIncident uiDetailIncident;
						uiVisualiserIncident.hide();
						uiDetailIncident = new UIDetailIncident(Service.giveIncident(idIncident));
						uiDetailIncident.remplirDetail(Service.giveIncident(idIncident));
						uiDetailIncident.fermerChamps();
						uiDetailIncident.cacherValider();
						DetailIncidentController detailIncidentController = 
									new DetailIncidentController (uiDetailIncident,uiauthentification);
								detailIncidentController.run (); 
                      }

				} catch (Exception e1) {
					
					System.out.println("Error d'affichage sur l'inteface detail");	
				}	
			}		
		});
		uiVisualiserIncident.AddFermerListener (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User user = uiauthentification.getUserConnected();
				if(user.getType().compareTo("RAPPORTEUR")== 0)
				{
					uiVisualiserIncident.dispose();
					uiauthentification.cacher(); 

				}else {
					uiVisualiserIncident.dispose();
					uiauthentification.montrer();
				}
			}
		});				
	}
	public void run() {
		uiVisualiserIncident.setVisible(true);
	}
}
