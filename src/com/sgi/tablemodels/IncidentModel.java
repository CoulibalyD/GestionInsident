package com.sgi.tablemodels;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.sgi.entities.Incident;
/*---------------------------------------------------------------------------------------------------------------------------*/	
public class IncidentModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	
	private Vector<Object[]> rows = new Vector<>();
	
	private List<Incident> incidents = new ArrayList<>();
	private String[] columns = {"Id_Incident", "Id_Rapporteur", "Id_Developpeur", "Application","Description", "Gravité", "Open date", "Close Date", "Statut"};
	
	public IncidentModel() {}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	@Override
	public int getRowCount() {		
		return rows.size();
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case OBJECT_COL : return incidents.get(rowIndex);
			default : return rows.get(rowIndex)[columnIndex];
		}
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public List<Incident> getIncidents() {
		return incidents;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void setIncidents (List<Incident> incidents) {		
		rows.clear();
		this.incidents.clear();
		this.incidents.addAll(0, incidents);
		
		for (Incident incident : incidents ){			
			rows.add( new Object [] {
					incident.getId(),
					incident.getIdRapporteur(),
					incident.getIdDeveloppeur(),
					incident.getApplication(),
					incident.getDescription(),
					incident.getGravite(), 
					incident.getDatecreation(), 
					incident.getDateCloture(),
					incident.getStatut(),
				});
		}
		
		fireTableDataChanged();
	}
	
	public void clear() {
		rows.clear();
		this.incidents.clear();
		
		fireTableDataChanged();
	}

}