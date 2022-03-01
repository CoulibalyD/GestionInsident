package com.sgi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sgi.entities.Incident;
import com.sgi.jdbc.DBManager;
import com.sgi.service.Service;
import com.sgi.utils.Utilitaire;

public class IncidentDaoImpl implements IDao<Incident> {

	@Override
	public void create(Incident incident) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into T_Incidents (idRapporteur,description, application, gravite,dateCreation,statut) values (?,?, ?, ?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, incident.getIdRapporteur());
		preparedStatement.setString(2, incident.getDescription());
		preparedStatement.setString(3, incident.getApplication());
		preparedStatement.setString(4, incident.getGravite());
		preparedStatement.setString(5, Utilitaire.getCurrentTime());
		preparedStatement.setString(6, "NOUVEAU");
		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public Incident read(int id) throws Exception {
		Incident incident = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Incidents Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	incident = new Incident (id,
	        		resultSet.getString("description"),
	        		resultSet.getString("application"),
	        		resultSet.getString("gravite"),
	        		resultSet.getString("statut"));
	    }

	    connection.close();
	    
		return incident;
	}

	@Override
	public void update(Incident incident) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Incidents Set idDeveloppeur=?,description=?,dateCloture=?,statut=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	        
	    prepareStatement.setInt(1, incident.getIdDeveloppeur());
	    prepareStatement.setString(2, incident.getDescription());
	    prepareStatement.setString(3, incident.getDateCloture());
	    prepareStatement.setString(4, incident.getStatut());
	    prepareStatement.setInt(5, incident.getId());
	       
	    prepareStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From T_Incidents Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<Incident> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents";

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
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public Incident readByNom(String nom) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident listDeveloppeurId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> listDeveloppeur() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> listIncidentsDeveloppeur(int idDeveloppeur) throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents WHERE idDeveloppeur = ? ";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1,idDeveloppeur);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			int idRapporteur = resultSet.getInt("idRapporteur");
			int idDevel = resultSet.getInt("idDeveloppeur");
			String description = resultSet.getString("description");
			String application = resultSet.getString("application");
			String gravite = resultSet.getString("gravite");
			String dateCreation = resultSet.getString("dateCreation");
			String dateCloture = resultSet.getString("dateCloture");
			String statut = resultSet.getString("statut");
			Incident incident = new Incident (id,idRapporteur,idDevel, description, application, gravite,dateCreation,dateCloture,statut);
			
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public List<Incident> listIncidentsRapporteur(int idRapporteur) throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents WHERE idRapporteur = ? ";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1,idRapporteur);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			int idRap = resultSet.getInt("idRapporteur");
			int idDevel = resultSet.getInt("idDeveloppeur");
			String description = resultSet.getString("description");
			String application = resultSet.getString("application");
			String gravite = resultSet.getString("gravite");
			String dateCreation = resultSet.getString("dateCreation");
			String dateCloture = resultSet.getString("dateCloture");
			String statut = resultSet.getString("statut");
			Incident incident = new Incident (id,idRap,idDevel, description, application, gravite,dateCreation,dateCloture,statut);
			
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public Incident nombreNotification(int idIncident) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> listNotification() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> listIncidentNouveau() throws Exception {
		Connection connection = DBManager.getConnection() ;

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
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public List<Incident> listIncidentAssigned(int developpeur) throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents Where statut = 'ASSIGNED' And idDeveloppeur = ?  ";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1,developpeur);
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
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public List<Incident> listIncidentResolu(int responsable) throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents,T_Users Where statut = 'RESOLU' And id = ?  ";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1,responsable);
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
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public List<Incident> listIncidentNouveauRaporteur(int raporteur) throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Incident> incidents = new ArrayList<>();
		String query = "Select * From T_Incidents Where statut = 'NOUVEAU' And idRapporteur = ?";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1,raporteur);
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
			Incident incident = new Incident (id,idRapporteur,idDeveloppeur, description, application, gravite,dateCreation,dateCloture,statut);
			Service.updateIncident(incident);
			incidents.add(incident);
		}
		
		connection.close();
		
		return incidents;
	}

	@Override
	public void updates(Incident incident) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Incidents Set description=?,application=?,statut=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	        
	   
	    prepareStatement.setString(1, incident.getDescription());
	    prepareStatement.setString(2, incident.getApplication());
	    prepareStatement.setString(3, incident.getStatut());
	    prepareStatement.setInt(4, incident.getId());
	       
	    prepareStatement.execute();
	    
	    connection.close();
	}
	
}
