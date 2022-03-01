package com.sgi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sgi.entities.User;
import com.sgi.jdbc.DBManager;

public class UserDaoImpl implements IDao<User> {

	@Override
	public void create(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into T_Users (nom,prenom,sexe,login,password,type) values (?,?,?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, user.getNom());
		preparedStatement.setString(2, user.getPrenom());
		preparedStatement.setString(3, user.getSexe());
		preparedStatement.setString(4, user.getLogin());
		preparedStatement.setString(5, user.getPassword());
		preparedStatement.setString(6, user.getType());

		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public User read(int id) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (id,
	    			resultSet.getString("nom"),
	    			resultSet.getString("prenom"),
	    			resultSet.getString("sexe"),
	        		resultSet.getString("login"), 
	        		resultSet.getString("password"), 
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}
	@Override
	public User readByNom(String nom) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where nom = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, nom);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (
	    			resultSet.getInt(0),
	    			resultSet.getString("nom"), 
	    			resultSet.getString("prenom"),
	    			resultSet.getString("sexe"),
	        		resultSet.getString("login"), 
	        		resultSet.getString("password"), 
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}
	@Override
	public void update(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update T_Users Set nom=?, prenom=?, sexe=?, login=?, password=?,  type=? Where id=?";

	    PreparedStatement prepareStatement = connection.prepareStatement(query);
	    
	    prepareStatement.setString(1, user.getNom());
	    prepareStatement.setString(2, user.getPrenom());
	    prepareStatement.setString(3, user.getSexe());
	    prepareStatement.setString(4, user.getLogin());
	    prepareStatement.setString(5, user.getPassword());
	    prepareStatement.setString(6, user.getType());
	    prepareStatement.setInt(7, user.getId());
	    prepareStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From T_Users Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<User> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<User> users = new ArrayList<>();
		String query = "Select * From T_Users";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			String sexe = resultSet.getString("sexe");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			String type = resultSet.getString("type");
			
			User user = new User (id,nom,prenom,sexe, login, password, type);
			
			users.add(user);
		}
		
		connection.close();
		
		return users;
	}

	public User readByLoginPassword(String login, String password) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From T_Users Where login = ? and password = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, login);
	    preparedStatement.setString(2, password);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (resultSet.getInt("id"), 
	    			resultSet.getString("nom"),
	    			resultSet.getString("prenom"),
	    			resultSet.getString("sexe"),
	        		resultSet.getString("login"), 
	        		resultSet.getString("password"), 
	        		resultSet.getString("type"));
	    }

	    connection.close();
	    
		return user;
	}

	@Override
	public User listDeveloppeurId(int id_developpeur) throws Exception {
		Connection connection = DBManager.getConnection() ;
		String query = "Select * From T_Users Where id=id_developpeur";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			String sexe = resultSet.getString("sexe");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			String type = resultSet.getString("type");
			
			User user = new User (id,nom,prenom,sexe, login, password, type);

		connection.close();
		
		return user;
		
	}

	@Override
	public List<User> listDeveloppeur() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<User> users = new ArrayList<>();
		String query = "Select * From T_Users Where type='DEVELOPPEUR'";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String nom = resultSet.getString("nom");
			String prenom = resultSet.getString("prenom");
			String sexe = resultSet.getString("sexe");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			String type = resultSet.getString("type");
			
			User user = new User (id,nom,prenom,sexe, login, password, type);
			
			users.add(user);
		}
		
		connection.close();
		
		return users;
	
	}

	@Override
	public List<User> listIncidentsDeveloppeur(int idDeveloppeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listIncidentsRapporteur(int idDeveloppeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User nombreNotification(int idIncident) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listNotification() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listIncidentNouveau() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listIncidentAssigned(int developpeur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listIncidentResolu(int responsable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listIncidentNouveauRaporteur(int raporteur) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updates(User obj) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
