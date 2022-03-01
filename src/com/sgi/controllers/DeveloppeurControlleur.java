package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UIDeveloppeur;

public class DeveloppeurControlleur {
	private UIDeveloppeur uideveloppeur;
	private UIAuthentification uiAthentification;
	public DeveloppeurControlleur(UIDeveloppeur uideveloppeur,UIAuthentification uiAthentification)
	{
		this.uideveloppeur = uideveloppeur;
		this.uiAthentification = uiAthentification;
		addListener();
	}

	private void addListener() {
		
		uideveloppeur.addCancelListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uideveloppeur.dispose();
				uiAthentification = new UIAuthentification();

				AuthentificationController authentificationController = new AuthentificationController(uiAthentification);
				
				authentificationController.run (); 
			}
			
		});
		uideveloppeur.addClearListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uideveloppeur.nettoyer();
			}
			
		});
	}
	public void montre()
	{
		uideveloppeur.setVisible(true);
	}

}
