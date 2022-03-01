package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.utils.Utilitaire;

public class UIDetailIncidentRapporteur extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JButton buttonAjouter;
	private  JButton buttonCancel;
	private JTextArea tANotes;
	private JTextArea tADescription;
	private JTextArea tANouvelleNote;
	private JPanel panelChangementEtat;
	private JTextField tFIdentifiant;
	private JTextField tFApplication;
	private JTextField tFStatut;
	private JTextField tFGravite;
	
	private int idIncident;
		
	public UIDetailIncidentRapporteur(Incident incident) {
		setBackground(new Color(0, 139, 139));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(875, 620));
		setModal(true);
		setResizable(false);
		
		this.idIncident = incident.getId();
		
		setTitle("D\u00E9tails de l'incident <" + idIncident + ">");				
				
		JPanel panelNotes = new JPanel();
		getContentPane().add(panelNotes, BorderLayout.SOUTH);
		panelNotes.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAjouterNote = new JPanel();
		panelNotes.add(panelAjouterNote, BorderLayout.SOUTH);
		panelAjouterNote.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtonAjouter = new JPanel();
		panelButtonAjouter.setBackground(new Color(0, 139, 139));
		panelAjouterNote.add(panelButtonAjouter, BorderLayout.EAST);
		
		buttonAjouter = new JButton("Ajouter");
		buttonAjouter.setFont(new Font("Verdana", Font.BOLD, 11));
		buttonAjouter.setBackground(Color.GREEN);
		buttonAjouter.setForeground(new Color(255, 255, 255));
		panelButtonAjouter.add(buttonAjouter);
		
		JButton bntquitter = new JButton("QUITTER");
		bntquitter.setForeground(Color.WHITE);
		bntquitter.setFont(new Font("Verdana", Font.BOLD, 11));
		bntquitter.setBackground(new Color(139, 0, 0));
		panelButtonAjouter.add(bntquitter);
		
		JPanel paneltFNote = new JPanel();
		paneltFNote.setBackground(new Color(0, 139, 139));
		paneltFNote.setBorder(new TitledBorder(null, "Ajouter une note ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAjouterNote.add(paneltFNote, BorderLayout.CENTER);
		paneltFNote.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneNouvelleNote = new JScrollPane();
		scrollPaneNouvelleNote.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneNouvelleNote.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		paneltFNote.add(scrollPaneNouvelleNote, BorderLayout.CENTER);
		
		tANouvelleNote = new JTextArea();
		tANouvelleNote.setFont(new Font("Verdana", Font.BOLD, 14));
		tANouvelleNote.setRows(3);
		scrollPaneNouvelleNote.setViewportView(tANouvelleNote);
		
		JPanel panelFluxEchanges = new JPanel();
		panelFluxEchanges.setBackground(new Color(0, 139, 139));
		panelFluxEchanges.setForeground(Color.BLUE);
		panelFluxEchanges.setBorder(new TitledBorder(null, "Flux d'\u00E9changes ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNotes.add(panelFluxEchanges, BorderLayout.NORTH);
		panelFluxEchanges.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneFluxEchanges = new JScrollPane();
		scrollPaneFluxEchanges.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFluxEchanges.setPreferredSize(new Dimension(2, 190));
		panelFluxEchanges.add(scrollPaneFluxEchanges, BorderLayout.CENTER);
		
		tANotes = new JTextArea();
		tANotes.setEditable(false);
		tANotes.setFont(new Font("Verdana", Font.BOLD, 14));
		scrollPaneFluxEchanges.setViewportView(tANotes);
		
		JPanel panelDetailIncident = new JPanel();
		panelDetailIncident.setBackground(new Color(0, 139, 139));
		panelDetailIncident.setForeground(Color.BLUE);
		panelDetailIncident.setBorder(new TitledBorder(null, "D\u00E9tails de l'incident ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		panelDetailIncident.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.PREF_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel labelId = new JLabel("Id :");
		labelId.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelId.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelId, "2, 2, right, default");
		
		tFIdentifiant = new JTextField();
		tFIdentifiant.setFont(new Font("Verdana", Font.BOLD, 14));
		tFIdentifiant.setEnabled(false);
		tFIdentifiant.setEditable(false);
		panelDetailIncident.add(tFIdentifiant, "4, 2, fill, default");
		tFIdentifiant.setColumns(20);
		
		JLabel labelApplication = new JLabel("Application :");
		labelApplication.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelApplication.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelApplication, "2, 4, right, default");
		
		tFApplication = new JTextField();
		tFApplication.setFont(new Font("Verdana", Font.BOLD, 14));
		tFApplication.setEnabled(false);
		tFApplication.setEditable(false);
		panelDetailIncident.add(tFApplication, "4, 4, fill, default");
		tFApplication.setColumns(10);
		
		JLabel labelDescription = new JLabel("Description :");
		labelDescription.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelDescription.setForeground(new Color(0, 0, 0));
		labelDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDetailIncident.add(labelDescription, "2, 6");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1, 40));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDetailIncident.add(scrollPane, "4, 6, fill, top");
		
		tADescription = new JTextArea();
		tADescription.setTabSize(2);
		tADescription.setFont(new Font("Verdana", Font.BOLD, 14));
		tADescription.setRows(1);
		tADescription.setEditable(false);
		tADescription.setEnabled(false);
		scrollPane.setViewportView(tADescription);
		
		JLabel labelStatut = new JLabel("Statut :");
		labelStatut.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelStatut.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelStatut, "2, 8, right, default");
		
		tFStatut = new JTextField();
		tFStatut.setFont(new Font("Verdana", Font.BOLD, 14));
		tFStatut.setEnabled(false);
		tFStatut.setEditable(false);
		panelDetailIncident.add(tFStatut, "4, 8, fill, default");
		tFStatut.setColumns(10);
		
		JLabel labelGravite = new JLabel("Niveau de gravit\u00E9 :");
		labelGravite.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelGravite.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelGravite, "2, 10, right, default");
		
		tFGravite = new JTextField();
		tFGravite.setFont(new Font("Verdana", Font.BOLD, 14));
		tFGravite.setEnabled(false);
		tFGravite.setEditable(false);
		panelDetailIncident.add(tFGravite, "4, 10, fill, default");
		tFGravite.setColumns(10);
		
		getContentPane().add(panelDetailIncident, BorderLayout.WEST);
		
		panelChangementEtat = new JPanel();
		panelChangementEtat.setBackground(new Color(0, 139, 139));
		panelChangementEtat.setForeground(Color.BLUE);
		panelChangementEtat.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelChangementEtat, BorderLayout.CENTER);
		panelChangementEtat.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCloturer = new JPanel();
		panelCloturer.setBackground(new Color(0, 139, 139));
		FlowLayout fl_panelCloturer = (FlowLayout) panelCloturer.getLayout();
		fl_panelCloturer.setAlignment(FlowLayout.RIGHT);
		panelChangementEtat.add(panelCloturer, BorderLayout.SOUTH);
		
		buttonCancel = new JButton("CANCEL");
		panelCloturer.add(buttonCancel);
		buttonCancel.setFont(new Font("Verdana", Font.BOLD, 10));
		buttonCancel.setBackground(Color.RED);
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setToolTipText("Cl\u00F4turer le ticket incident ...");
		buttonCancel.setPreferredSize(new Dimension(80, 25));
		
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	
	public int getIdIncident() {
		return idIncident;
	}

	public void addAjouterNouvelleNoteListener(ActionListener actionListener) {
		buttonAjouter.addActionListener(actionListener);
	}
	
	public void addCloturerListener(ActionListener actionListener) {
		buttonCancel.addActionListener(actionListener);
	}
	
	

	public void afficherLesNotes(List<Note> notes) {
		
		String fluxEchanges = "";
		
		for (Note note : notes) {
			fluxEchanges += note.getDateCreation() + ":" + note.getIdCreateur() + "\n";
			fluxEchanges += note.getMessage() + "\n\n";
		}
		
		this.tANotes.setText(fluxEchanges);
	}

	public String getNouvelleNote() {
		return this.tANouvelleNote.getText();
	}
	public void remplirDetail(Incident incident) {
	
		tFIdentifiant.setText(String.valueOf(incident.getId()));
		tFApplication.setText(incident.getApplication());
		tFStatut.setText(incident.getStatut());
		tADescription.setText(incident.getDescription());
		tFGravite.setText(incident.getGravite());
	
	}
	public void fermerChamps()
	{

		panelChangementEtat.setVisible(false);
		
	}
	public void ouverChamps()
	{
		panelChangementEtat.setVisible(true);
	}
	public void effacerLeChampNouvelleNote() {
		this.tANouvelleNote.setText(null);
	}	
}
