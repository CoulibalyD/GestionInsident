  package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.utils.Utilitaire;

public class UIDeveloppeur extends JDialog {
	
private static final long serialVersionUID = 1L;
	
	private JButton buttonAjouter;
	
	private JTextArea tANotes;
	private JTextArea tADescription;
	private JTextArea tANouvelleNote;
	
	private JTextField tFIdentifiant;
	private JTextField tFApplication;
	
	private JComboBox<String> statutcomboBox;
	
	private JButton clearButton;
	private JButton cancelButton;
	private int userId;
	private JTextField stautField;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UIDeveloppeur() {
		setBackground(new Color(0, 139, 139));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(835, 683));
		setModal(true);
		setResizable(false);
		
		//this.userId = idUser;
		
		//setTitle("D\u00E9tails de l'incident <" + idUser + ">");				
				
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
		buttonAjouter.setForeground(new Color(255, 255, 255));
		buttonAjouter.setBackground(new Color(0, 191, 255));
		buttonAjouter.setFont(new Font("Verdana", Font.BOLD, 13));
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
		tANouvelleNote.setFont(new Font("Arial", Font.PLAIN, 12));
		tANouvelleNote.setRows(3);
		scrollPaneNouvelleNote.setViewportView(tANouvelleNote);
		
		JPanel panelFluxEchanges = new JPanel();
		panelFluxEchanges.setBackground(new Color(0, 139, 139));
		panelFluxEchanges.setBorder(new TitledBorder(null, "Flux d'\u00E9changes ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNotes.add(panelFluxEchanges);
		panelFluxEchanges.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneFluxEchanges =  new JScrollPane();
		scrollPaneFluxEchanges.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFluxEchanges.setPreferredSize(new Dimension(2, 190));
		panelFluxEchanges.add(scrollPaneFluxEchanges, BorderLayout.CENTER);
		
		tANotes = new JTextArea();
		tANotes.setEditable(false);
		tANotes.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPaneFluxEchanges.setViewportView(tANotes);
		
		JPanel panelDetailIncident = new JPanel();
		panelDetailIncident.setBackground(new Color(0, 139, 139));
		panelDetailIncident.setBorder(new TitledBorder(null, "D\u00E9tails de l'incident ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		panelDetailIncident.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(226dlu;default):grow"),},
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel labelId = new JLabel("Id :");
		labelId.setFont(new Font("Verdana", Font.PLAIN, 13));
		panelDetailIncident.add(labelId, "2, 2, right, default");
		
		tFIdentifiant = new JTextField();
		tFIdentifiant.setEnabled(false);
		tFIdentifiant.setEditable(false);
		panelDetailIncident.add(tFIdentifiant, "4, 2, fill, default");
		tFIdentifiant.setColumns(20);
		
		JLabel labelApplication = new JLabel("Application :");
		labelApplication.setFont(new Font("Verdana", Font.PLAIN, 13));
		panelDetailIncident.add(labelApplication, "2, 4, right, default");
		
		tFApplication = new JTextField();
		tFApplication.setEnabled(false);
		tFApplication.setEditable(false);
		panelDetailIncident.add(tFApplication, "4, 4, fill, default");
		tFApplication.setColumns(10);
		
		JLabel labelDescription = new JLabel("Description :");
		labelDescription.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDetailIncident.add(labelDescription, "2, 6");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1, 40));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDetailIncident.add(scrollPane, "4, 6, fill, top");
		
		tADescription = new JTextArea();
		tADescription.setFont(new Font("Arial", Font.PLAIN, 11));
		tADescription.setRows(3);
		tADescription.setEditable(false);
		tADescription.setEnabled(false);
		scrollPane.setViewportView(tADescription);
		
		JLabel labelStatut = new JLabel("Statut :");
		labelStatut.setFont(new Font("Verdana", Font.PLAIN, 13));
		panelDetailIncident.add(labelStatut, "2, 8, right, default");
		
		getContentPane().add(panelDetailIncident, BorderLayout.WEST);
		
		stautField = new JTextField();
		panelDetailIncident.add(stautField, "4, 8, fill, default");
		stautField.setColumns(10);
		
		JLabel lbIGravite = new JLabel("Niveau de gravite :");
		lbIGravite.setFont(new Font("Verdana", Font.PLAIN, 13));
		panelDetailIncident.add(lbIGravite, "2, 10");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Statut:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel.add(lblNewLabel, "10, 26");
		
		statutcomboBox = new JComboBox();
		statutcomboBox.setForeground(new Color(255, 255, 255));
		statutcomboBox.setFont(new Font("Verdana", Font.BOLD, 14));
		statutcomboBox.addItem("EN_ATTENTE");
		statutcomboBox.addItem("RESOLU");
		panel.add(statutcomboBox, "6, 28, 5, 1, fill, bottom");
		
		clearButton = new JButton("CLEAR");
		clearButton.setForeground(new Color(255, 255, 255));
		clearButton.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(clearButton, "12, 28");
		
		cancelButton = new JButton("CANCEL");
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(cancelButton, "14, 28");
		
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	
	public int getUserId() {
		return userId;
	}

	public void addAjouterNouvelleNoteListener(ActionListener actionListener) {
		buttonAjouter.addActionListener(actionListener);
	}
	
	public void addCancelListener(ActionListener actionListener)
	{
		this.cancelButton.addActionListener(actionListener);
	}
	public void addClearListener(ActionListener actionListener)
	{
		this.clearButton.addActionListener(actionListener);
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

	public void effacerLeChampNouvelleNote() {
		this.tANouvelleNote.setText(null);
	}

	public void run() {
		this.setVisible(true);
		
	}
	public void remplirChamp(Incident incident)
	{
		tFIdentifiant.setText(String.valueOf(incident.getId()));
		tFApplication.setText(incident.getApplication());
		tADescription.setText(incident.getDescription());
		stautField.setText(incident.getStatut());
	}
	public void nettoyer() {
		tANotes.setText(null);
		tANouvelleNote.setText(null);
	}
}

