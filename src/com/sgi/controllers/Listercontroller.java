package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAdministrateur;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UIListe;
import com.sgi.utils.Utilitaire;

public class Listercontroller {

	private UIAdministrateur uiadministrateur;
	private AdministrateurController controller;
	private UIAuthentification uIauthentification;
	private UIListe uiliste;
	
	public AdministrateurController getController() {
		return controller;
	}

	public void setController(AdministrateurController controller) {
		this.controller = controller;
	}

	public UIAuthentification getuIauthentification() {
		return uIauthentification;
	}

	public void setuIauthentification(UIAuthentification uIauthentification) {
		this.uIauthentification = uIauthentification;
	}

	public Listercontroller(UIListe uiliste,UIAuthentification uIauthentification)
	{
		this.uiliste = uiliste;
		this.uIauthentification = uIauthentification;
		
		List<User> user;
		
		try {
			user = Service.listerUser();
			uiliste.loadUser(user);
		} catch (Exception e) {
			Utilitaire.displayErrorMessage("Error :" + e.getMessage());
		}
		addListener();
	}

	private void addListener() {
		uiliste.AddOuvrirListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				uiliste.setVisible(false);
				int id = uiliste.getUserId();
				if(id != -1) {
				try {
					User user = Service.giveUser(id);
					UIAdministrateur uiadministrateur = new UIAdministrateur();
					AdministrateurController controller = new AdministrateurController(uiadministrateur);
					controller.run();
					uiadministrateur.remplirchamp(user);
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error :" + e1.getMessage());
				}	
			}
			}
			
		});
		uiliste.AddFermerListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiliste.dispose();
				uiadministrateur = new UIAdministrateur();
				controller = new AdministrateurController(uiadministrateur);
				controller.run();
			}
			
		});
		
	}

	public void run() {
		uiliste.setVisible(true);
	}

}
