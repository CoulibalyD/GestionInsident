package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgi.entities.User;
import com.sgi.entities.UserType;
import com.sgi.service.Service;
import com.sgi.ui.UIAdministrateur;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UISelectionOperation;
import com.sgi.ui.UIVisualiserIncident;
import com.sgi.utils.Utilitaire;

public class AuthentificationController {

	private UIAuthentification uiAuthentification;

	public AuthentificationController(UIAuthentification uiAuthentification) {
		this.uiAuthentification = uiAuthentification;
		
		addListeners();
	}
	
	private void addListeners () {
		
		uiAuthentification.addValiderListener ( new ActionListener() {			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String login = uiAuthentification.getLogin();
				String password = uiAuthentification.getPassword();
				
				if (login.equals("") || password.equals("")) {
					
				Utilitaire.displayNotification("Renseigne les champs correctement svp....");
					return;
				}
				
				try {
					User user = Service.authentifier(login, password);
					if (user == null) {
						Utilitaire.displayErrorMessage("Cet  Utilisateur n'existe pas....");
					} else {												
						
						UserType userType = user.getUserType();
						uiAuthentification.setUserConnected(user);
						switch (userType)
						{
							case RAPPORTEUR:
								
								UISelectionOperation uiSelectionOperation = new UISelectionOperation();
								SelectionOperationController selectionController = new SelectionOperationController (uiSelectionOperation, uiAuthentification);
								selectionController.run ();	
								Utilitaire.displayNotification("********************\nWelcom Rapporteur #\n"+"Nom:" + user.getNom()+"\n"+ "Prenom:"+ user.getPrenom()+ "\n********************");
								
								break;
							case DEVELOPPEUR:
								
								UIVisualiserIncident uivisualiserIncidents = new UIVisualiserIncident();
								VisualiserIncidentController selectionControlles = new VisualiserIncidentController (uivisualiserIncidents,uiAuthentification);
								selectionControlles.run ();
								Utilitaire.displayNotification("********************\nWelcom Developpeur #\n"+"Nom:" + user.getNom()+"\n"+ "Prenom:"+ user.getPrenom()+ "\n********************");
								
								break;
							case RESPONSABLE:
								
								UIVisualiserIncident uivisualiserIncident = new UIVisualiserIncident();
								VisualiserIncidentController selectionControllers = new VisualiserIncidentController (uivisualiserIncident,uiAuthentification);
								selectionControllers.run ();
								Utilitaire.displayNotification("********************\nWelcom Responsable #\n"+"Nom:" + user.getNom()+"\n"+ "Prenom:"+ user.getPrenom()+ "\n********************");
								break;
							case ADMINISTRATEUR:
								
								UIAdministrateur uiadministrateur = new UIAdministrateur();
								AdministrateurController administrateur = new AdministrateurController(uiadministrateur);
								administrateur.run();
								Utilitaire.displayNotification("********************\nWelcom Administrateur #\n"+"Nom:" + user.getNom()+"\n"+ "Prenom:"+ user.getPrenom()+ "\n********************");
								
								break;
								
							default :
								Utilitaire.displayErrorMessage("Cet typ de Utilisateur n'existe pas....");
								break;
						}

						uiAuthentification.clear();
						uiAuthentification.cacher();
					}
				} catch (Exception exception) {
					Utilitaire.displayErrorMessage("Une erreur produire quelque part :"+exception.getMessage());
				}
			}
		});
		
		uiAuthentification.addQuitterListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiAuthentification.dispose();				
			}
		});
	}

	public void run() {
		uiAuthentification.montrer();
	}
}
