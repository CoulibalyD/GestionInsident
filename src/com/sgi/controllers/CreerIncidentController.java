package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgi.entities.Incident;
import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UICreerIncident;
import com.sgi.utils.Utilitaire;

public class CreerIncidentController {
	private UICreerIncident uiCreerIncident;
	private UIAuthentification uiAuthentification;
	public CreerIncidentController(UICreerIncident uiCreerIncident,UIAuthentification uiAuthentification) {
		this.uiCreerIncident = uiCreerIncident;
		this.uiAuthentification = uiAuthentification;
		addListeners ();

	}
	
	private void addListeners () {
		
		uiCreerIncident.addEffacerListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiCreerIncident.nettoyer();	
			}
		});
			
		uiCreerIncident.addValiderListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String description = uiCreerIncident.getDescription ();
				String application = uiCreerIncident.getApplication ();
				String gravite = uiCreerIncident.getGravite ();
			
				try {
					User user = uiAuthentification.getUserConnected();
					
					Incident incident = new Incident(user.getId(),0,description,application,gravite,Utilitaire.getCurrentTime());
					Service.creerIncident (incident);
					Utilitaire.displayNotification("Incident créé avec succès !");
					uiCreerIncident.nettoyer();
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error : " + e1.getMessage());
				}								
			}
			
		});
		
		uiCreerIncident.addQuitterListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uiCreerIncident.dispose();	
					
			}
		});
	}

	public void run() {
		uiCreerIncident.setVisible(true);
	}
}
