package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import com.sgi.entities.Incident;
import com.sgi.tablemodels.IncidentModel;
import com.sgi.utils.Utilitaire;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

public class UIVisualiserIncident extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable tableIncidents;
	private JButton buttonOuvrir;
	private JButton buttonFermer;
	private IncidentModel incidentModel;
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public UIVisualiserIncident() {
		setFont(new Font("Verdana", Font.PLAIN, 13));
		setBackground(new Color(0, 139, 139));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(983, 496));
		setResizable(false);
		setTitle("Visualiser les incidents");
		
		JPanel panelListeIncidents = new JPanel();
		panelListeIncidents.setBorder(new TitledBorder(null, "Liste des incidents ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelListeIncidents, BorderLayout.CENTER);
		panelListeIncidents.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelListeIncidents.add(scrollPane, BorderLayout.CENTER);
		
		tableIncidents = new JTable();
		
		incidentModel = new IncidentModel();
		tableIncidents.setModel(incidentModel);
		scrollPane.setViewportView(tableIncidents);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setForeground(SystemColor.window);
		panelButtons.setBackground(new Color(0, 139, 139));
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonOuvrir = new JButton("Ouvrir");
		Image ouv = new ImageIcon(this.getClass().getResource("/ouvrir.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonOuvrir.setIcon(new ImageIcon(ouv));
		buttonOuvrir.setBackground(Color.CYAN);
		buttonOuvrir.setForeground(SystemColor.window);
		buttonOuvrir.setFont(new Font("Verdana", Font.BOLD, 14));
		panelButtons.add(buttonOuvrir);
		
		buttonFermer = new JButton("Fermer");
		Image fer = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonFermer.setIcon(new ImageIcon(fer));
		buttonFermer.setForeground(SystemColor.window);
		buttonFermer.setFont(new Font("Verdana", Font.BOLD, 14));
		buttonFermer.setBackground(Color.RED);
		panelButtons.add(buttonFermer);	
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void AddOuvrirListener(ActionListener actionListener) {
		buttonOuvrir.addActionListener(actionListener);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void AddFermerListener(ActionListener actionListener) {
		buttonFermer.addActionListener(actionListener);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public int getSelectedIncidentId() {		
		int idRow = tableIncidents.getSelectedRow();
		
		if (idRow == -1) return idRow;
		else {
			return (int) incidentModel.getValueAt(idRow, 0);
		}		
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void femerChamps()
	{
		buttonOuvrir.setText(null);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void loadIncident (List<Incident> incidents) {
		incidentModel.setIncidents(incidents);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void run() {
		
		setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
}
