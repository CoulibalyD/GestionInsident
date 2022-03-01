package com.sgi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.entities.Notification;
import com.sgi.jdbc.DBManager;
import com.sgi.service.Service;
import com.sgi.utils.Utilitaire;

public class NotificationDaoImpl implements IDao<Notification> {

	@Override
	public void create(Notification notification) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into T_Notification (idCreateur,idDestinateur,idIncident,dateCreation) values ( ?, ?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		//preparedStatement.setInt(1, notification.getIdNotification());
		preparedStatement.setInt(1, notification.getIdCreateur());
		preparedStatement.setInt(2, notification.getIdDestinateur());
		preparedStatement.setInt(3, notification.getIdIncident());
		preparedStatement.setString(4, Utilitaire.getCurrentTime());
		preparedStatement.execute();
		
		connection.close();
		
	}

	@Override
	public Notification read(int id) throws Exception {
		
		return null;
	}

	@Override
	public Notification readByNom(String nom) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Notification notification) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Notification  Set idDestinateur=?,nombreNotification = ? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	        
	    prepareStatement.setInt(1, notification.getIdDestinateur());
	   
	    prepareStatement.setInt(2, notification.getNombreNote());  
	    prepareStatement.execute();
	    
	    connection.close();
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		
		
	}

	@Override
	public List<Notification> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification listDeveloppeurId(int id_developpeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listDeveloppeur() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listIncidentsDeveloppeur(int idDeveloppeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listIncidentsRapporteur(int idDeveloppeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification nombreNotification(int idIncident) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listNotification() throws Exception {
		Connection connection = DBManager.getConnection() ;
		List<Notification> note = null;
		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents Where statut = 'NOUVEAU'";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			int idRapporteur = resultSet.getInt("idRapporteur");
			int idDeveloppeur = resultSet.getInt("idDeveloppeur");
			String description = resultSet.getString("description");
			String application = resultSet.getString("application");
			String gravite = resultSet.getString("gravite");
			String dateCreation = resultSet.getString("dateCreation");
			String dateCloture = resultSet.getString("dateCloture");
			String statut = resultSet.getString("statut");
			//Notification notes = new Notification(id);
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return note;
	}

	@Override
	public List<Notification> listIncidentNouveau() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listIncidentAssigned(int developpeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listIncidentResolu(int responsable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> listIncidentNouveauRaporteur(int raporteur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updates(Notification obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
