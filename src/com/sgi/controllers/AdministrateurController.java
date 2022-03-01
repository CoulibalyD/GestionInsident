package com.sgi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.ui.UIAdministrateur;
import com.sgi.ui.UIAuthentification;
import com.sgi.ui.UIListe;
import com.sgi.utils.Utilitaire;

public class AdministrateurController {
	private UIAdministrateur uiadministrateur;
	private UIListe uiliste = new UIListe();
	private UIAuthentification uiauthentiffication;
	public AdministrateurController(UIAdministrateur uiadministrateur)
	{
		this.uiadministrateur = uiadministrateur;
		addListener();
		Utilitaire.getCurrentTime();
	}

	private void addListener() {
		uiadministrateur.addCreerListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = uiadministrateur.getNomField();
				String prenom = uiadministrateur.getPrenomField();
				String sexe = uiadministrateur.getSexecomboBox();
				String login = uiadministrateur.getLoginField();
				String password = uiadministrateur.getPasswordField();
				String type = uiadministrateur.getTypecomboBox();
				
				User user = new User(nom,prenom,sexe,login,password,type);
				if(user.getNom().compareTo("")== 0||user.getPrenom().compareTo("")==0||user.getSexe().compareTo("")==0)
				{
					Utilitaire.displayErrorMessage("Veuille reseigner les champs......" );
				}else {
				try {
					Service.creerUser(user);
					Utilitaire.displayNotification("Utilisateur cree avec success...");
					uiadministrateur.nottoyer();
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error:" + e1.getMessage());				}
				}
			}
				
		
		});
		uiadministrateur.addUpdateListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = uiadministrateur.getIdField();
				String nom = uiadministrateur.getNomField();
				String prenom = uiadministrateur.getPrenomField();
				String sexe = uiadministrateur.getSexecomboBox();
				String login = uiadministrateur.getLoginField();
				String password = uiadministrateur.getPasswordField();
				String type = uiadministrateur.getTypecomboBox();
				User user = new User(id,nom,prenom,sexe,login,password,type);
				if(user.getNom().compareTo("")== 0|| user.getPrenom().compareTo("")== 0 || user.getLogin().compareTo("")== 0) {
					Utilitaire.displayErrorMessage("Veuille selectionne un Utilisateur ......" );
				}else {
					
				try {
					Service.updateUser(user);
					Utilitaire.displayNotification("Mis en Jour effctue  avec success...");
					uiadministrateur.nottoyer();
				} catch (Exception e2) {
					
					Utilitaire.displayErrorMessage("Error:"+ e2.getMessage());
				}
				
				
			}
			}
		});
		uiadministrateur.addListeListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				uiadministrateur.hide();
				Listercontroller listeControlleur = new Listercontroller(uiliste,uiauthentiffication);
				listeControlleur.run();
				
			}
			
		});
		uiadministrateur.addCancelListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiliste.setVisible(false);
				uiadministrateur.disposeFenetre();
				UIAuthentification uiAuthentification = new UIAuthentification();

				AuthentificationController authentificationController = new AuthentificationController(uiAuthentification);
				
				authentificationController.run ();
			}
		
			
		});
		uiadministrateur.addSearchListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int elementCherche = uiadministrateur.getSaerchField();
				if(elementCherche > 0) {
				try {
					uiadministrateur.remplirchamp(Service.giveUser(elementCherche));
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Cet utilisateur n'existe pas : " + e1.getMessage());
				}
			}else if(String.valueOf(elementCherche).compareTo("") == 0) {
				Utilitaire.displayNotification("Veuillez remplier le champs rechercher");
			}else {
				Utilitaire.displayErrorMessage("Cet utilisateur n'existe pas  ");
			}
				}
			
			
		});
		uiadministrateur.addClearListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiadministrateur.nottoyer();	
			}
		});
		
		uiadministrateur.adddeleteListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = uiadministrateur.getIdField();
				if(String.valueOf(id).compareTo("")== 0) {
					Utilitaire.displayNotification("Veuillez donne l'element a supprimer...");
				}else {
				try {
					Service.deleteUser(Integer.valueOf(id));
					Utilitaire.displayNotification("Utilisateur d'identifiant:"+ id + "a ete supprimer avec success");
				} catch (Exception e1) {
					Utilitaire.displayErrorMessage("Error: " + e1.getMessage());
				}
				uiadministrateur.nottoyer();
			}
			}
			
		});
	}
	
	public void run() {
		
		uiadministrateur.setVisible(true);
		
		 
	}
}