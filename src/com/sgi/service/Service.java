package com.sgi.service;

import java.util.ArrayList;
import java.util.List;

import com.sgi.dao.IDao;
import com.sgi.dao.IncidentDaoImpl;
import com.sgi.dao.NoteDaoImpl;
import com.sgi.dao.UserDaoImpl;
import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.entities.User;

public class Service {

	public static User authentifier (String login, String password) throws Exception {
		
		IDao<User> dao = new UserDaoImpl();
		
		if (dao instanceof UserDaoImpl) {
			return ((UserDaoImpl) dao).readByLoginPassword(login, password);
		}
		
		return null;
	}

	public static void creerIncident(Incident incident) throws Exception {
		IDao<Incident> dao = new IncidentDaoImpl();
		dao.create(incident);
	}

	public static List<Incident> listerIncidents() throws Exception {
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.list();
		
		return incidents;
	}
	public static User rechercheruser(String nom) throws Exception{
		IDao<User> dao = new UserDaoImpl();
		return dao.readByNom(nom);
	}
	public static List<Note> listerNotes(int idIncident) throws Exception {
		IDao<Note> dao = new NoteDaoImpl();
				
		if (dao instanceof NoteDaoImpl) {
			return ((NoteDaoImpl) dao).list(idIncident);
		}
		
		return new ArrayList<Note>(); 
	}
	
	public static void creerNote(Note note) throws Exception {
		IDao<Note> dao = new NoteDaoImpl();
		
		dao.create(note);
	}
	
	public static void creerUser(User user) throws Exception {
		IDao<User> dao = new UserDaoImpl();
			dao.create(user);
	}
	public static List<User> listerUser() throws Exception{
		IDao<User> dao = new UserDaoImpl();
		if(dao instanceof UserDaoImpl) {
			return ((UserDaoImpl)dao).list();
		}
		
		return new ArrayList<User>(); 
	}
	public static List<User> listerDeveloppeur() throws Exception{
		IDao<User> dao = new UserDaoImpl();
		if(dao instanceof UserDaoImpl) {
			return ((UserDaoImpl)dao).listDeveloppeur();
		}
		
		return new ArrayList<User>(); 
	}
	public static User listerDeveloppeur(int id) throws Exception{
		IDao<User> dao = new UserDaoImpl();
		return dao.read(id);
	}
	public static void updateIncident(Incident incident) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		dao.update(incident);
		
	}
	public static void updateIncidents(Incident incident) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		dao.updates(incident);
		
	}
	public static void updateUser(User user) throws Exception
	{
		IDao<User> dao = new UserDaoImpl();
		dao.update(user);
	}
	public static void deleteUser(Integer id) throws Exception
	{
		IDao<User> dao = new UserDaoImpl();
		dao.delete(id);
	}
	public static User giveUser(Integer id) throws Exception
	{
		IDao<User> dao = new UserDaoImpl();
		return dao.read(id);
	}
	public static Incident giveIncident(Integer id) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		return dao.read(id);
	}
	public static List<Incident>  listeIncidentsDev(int idDev) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentsDeveloppeur(idDev);
		
		return incidents;	
	}
	public static List<Incident>  listeIncidentsRapporteur(int idRapporteur) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentsRapporteur(idRapporteur);
		
		return incidents;	
	}
	public static List<Incident> listeIncidentNouvea() throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentNouveau();
		
		return incidents;	
	}
	public static List<Incident> listeIncidentAssigned(int developpeur) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentAssigned(developpeur);
		
		return incidents;	
	}
	public static List<Incident> listeIncidentResolu(int responsable) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentResolu(responsable);
		
		return incidents;	
	}
	public static List<Incident> listeIncidentNouveauRapporteur(int idRapporteur) throws Exception
	{
		IDao<Incident> dao = new IncidentDaoImpl();
		
		List<Incident> incidents = dao.listIncidentNouveauRaporteur(idRapporteur);
		
		return incidents;	
	}
	
}

