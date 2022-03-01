package com.sgi.dao;

import java.util.List;

public interface IDao<T> {
	
	public void create(T obj) throws Exception;
	
	public T read(int id) throws Exception;
	
	public T readByNom(String nom) throws Exception;
	
	public void update(T obj) throws Exception;
	
	public void updates(T obj) throws Exception;
	
	public void delete(Integer id) throws Exception;
	
	public List<T> list() throws Exception;
	
	public T listDeveloppeurId(int id_developpeur) throws Exception;
	
	public List<T> listDeveloppeur() throws Exception;
	
	public List<T> listIncidentsDeveloppeur(int idDeveloppeur) throws Exception;
	
	public List<T> listIncidentsRapporteur(int idRapporteur) throws Exception;
	
	public T nombreNotification(int idIncident) throws Exception;
	
	public List<T> listNotification( ) throws Exception;
	
	public List<T> listIncidentNouveau() throws Exception;
	
	public List<T> listIncidentNouveauRaporteur(int raporteur) throws Exception;
	
	public List<T> listIncidentAssigned(int developpeur) throws Exception;
	
	public List<T> listIncidentResolu(int responsable) throws Exception;
}
