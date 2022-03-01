package com.sgi.tablemodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.sgi.entities.User;

public class UserModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	
	private Vector<Object[]> rows = new Vector<>();
	
	private List<User> users = new ArrayList<>();
	
	private String[] columns = {"Id", "Nom", "Prenom", "Sexe", "Login","Type"};
	
	public UserModel() {}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {		
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case OBJECT_COL : return users.get(rowIndex);
			default : return rows.get(rowIndex)[columnIndex];
		}
	}

	public List<User> getUser() {
		return users;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public void setUser (List<User> users) {		
		rows.clear();
		this.users.clear();
		this.users.addAll(0, users);
		
		for (User user : users ){			
			rows.add( new Object [] {
					user.getId(), 
					user.getNom(), 
					user.getPrenom(),
					user.getSexe(),
					user.getLogin(),
					user.getType(), 			 
				});
		}
		
		fireTableDataChanged();
	}
	
	public void delete(User user) {
		int indexOf = users.indexOf(user);
		users.remove(user);
		rows.remove(indexOf);
		
		fireTableDataChanged();
	}
	
	public void update (int id,String nom,String prenom,String sexe, String login, String password,String type) {
		User user = new User(id,nom,prenom,sexe, login, password,type);
		int indexOf = users.indexOf(user);
		users.get(indexOf).setNom(nom);
		users.get(indexOf).setPrenom(prenom);
		users.get(indexOf).setSexe(sexe);
		users.get(indexOf).setLogin(login);
		users.get(indexOf).setPassword(password);
		users.get(indexOf).setType(type);
		Object [] obj = rows.get(indexOf);
		obj[0] = id;
		obj[1] = nom;
		obj[2] = prenom;
		obj[3] = sexe;
		obj[4] = login;
		obj[5] = password;
		obj[6] = type;
		fireTableDataChanged();
	}
	public void add(int id,String nom,String prenom,String sexe, String login, String password,String type) {
		User user = new User(id,nom,prenom,sexe, login, password,type);
		users.add(user);
		rows.add( new Object [] {
				user.getId(),
				user.getNom(), 
				user.getPrenom(),
				user.getSexe(),
				user.getLogin(),
				user.getPassword(),
				user.getType()
		});
		
		fireTableDataChanged();
	}
	
	public void clear() {
		rows.clear();
		this.users.clear();
		
		fireTableDataChanged();
	}
}
