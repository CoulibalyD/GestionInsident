package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sgi.entities.Incident;
import com.sgi.entities.Note;
import com.sgi.entities.User;
import com.sgi.service.Service;
import com.sgi.utils.Utilitaire;

public class UIDetailIncident extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JButton buttonAjouter;
	
	private  JComboBox<String> cBDeveloppeurs;
	private  JButton buttonAssigner;
	private JButton bntquitter;
	private JTextArea tANotes;
	private JTextArea tADescription;
	private JTextArea tANouvelleNote;
	private JPanel panelChangementEtat;
	private JTextField tFIdentifiant;
	private JTextField tFApplication;
	private JTextField tFStatut;
	private JTextField tFGravite;
	private JLabel labelAssigner;
	private JPanel panelAttenteResol;
	private JButton btnAttente;
	private JButton btnResolue;
	private JButton buttonOuvrir;
	private JButton buttonCloturer;
	
	private int idIncident;
	private JPanel panelvalideRapporteur;
	private JButton valideBtn;
	/*---------------------------------------------------------------------------------------------------------------------------*/		
	public UIDetailIncident(Incident incident) {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 139, 139));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(1074, 691));
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
		buttonAjouter.setPreferredSize(new Dimension(130, 30));
		Image ajou = new ImageIcon(this.getClass().getResource("/add.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonAjouter.setIcon(new ImageIcon(ajou));
		buttonAjouter.setFont(new Font("Verdana", Font.BOLD, 14));
		buttonAjouter.setBackground(Color.GREEN);
		buttonAjouter.setForeground(new Color(255, 255, 255));
		panelButtonAjouter.add(buttonAjouter);
		
		bntquitter = new JButton("QUITTER");
		bntquitter.setPreferredSize(new Dimension(130, 30));
		Image quit = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		bntquitter.setIcon(new ImageIcon(quit));
		bntquitter.setFont(new Font("Verdana", Font.BOLD, 14));
		bntquitter.setForeground(new Color(255, 255, 255));
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
		tANouvelleNote.setFont(new Font("Verdana", Font.ITALIC, 14));
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
		tANotes.setFont(new Font("Verdana", Font.ITALIC, 14));
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel labelId = new JLabel("Id :");
		labelId.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelId.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelId, "2, 2, right, default");
		
		tFIdentifiant = new JTextField();
		tFIdentifiant.setBackground(new Color(255, 255, 255));
		tFIdentifiant.setForeground(new Color(0, 0, 0));
		tFIdentifiant.setFont(new Font("Verdana", Font.PLAIN, 14));
		tFIdentifiant.setEnabled(false);
		tFIdentifiant.setEditable(false);
		panelDetailIncident.add(tFIdentifiant, "4, 2, fill, default");
		tFIdentifiant.setColumns(20);
		
		JLabel labelApplication = new JLabel("Application :");
		labelApplication.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelApplication.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelApplication, "2, 4, right, default");
		
		tFApplication = new JTextField();
		tFApplication.setBackground(new Color(255, 255, 255));
		tFApplication.setForeground(new Color(0, 0, 0));
		tFApplication.setFont(new Font("Verdana", Font.PLAIN, 14));
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
		scrollPane.setPreferredSize(new Dimension(1, 70));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDetailIncident.add(scrollPane, "4, 6, fill, top");
		
		tADescription = new JTextArea();
		tADescription.setBackground(new Color(255, 255, 255));
		tADescription.setForeground(new Color(0, 0, 0));
		tADescription.setTabSize(2);
		tADescription.setFont(new Font("Verdana", Font.PLAIN, 14));
		tADescription.setRows(1);
		tADescription.setEditable(false);
		tADescription.setEnabled(false);
		scrollPane.setViewportView(tADescription);
		
		JLabel labelStatut = new JLabel("Statut :");
		labelStatut.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelStatut.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelStatut, "2, 8, right, default");
		
		tFStatut = new JTextField();
		tFStatut.setBackground(new Color(255, 255, 255));
		tFStatut.setForeground(new Color(0, 0, 0));
		tFStatut.setFont(new Font("Verdana", Font.PLAIN, 14));
		tFStatut.setEnabled(false);
		tFStatut.setEditable(false);
		panelDetailIncident.add(tFStatut, "4, 8, fill, default");
		tFStatut.setColumns(10);
		
		JLabel labelGravite = new JLabel("Niveau de gravit\u00E9 :");
		labelGravite.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelGravite.setForeground(new Color(0, 0, 0));
		panelDetailIncident.add(labelGravite, "2, 10, right, default");
		
		tFGravite = new JTextField();
		tFGravite.setBackground(new Color(255, 255, 255));
		tFGravite.setForeground(new Color(0, 0, 0));
		tFGravite.setFont(new Font("Verdana", Font.PLAIN, 14));
		tFGravite.setEnabled(false);
		tFGravite.setEditable(false);
		panelDetailIncident.add(tFGravite, "4, 10, fill, default");
		tFGravite.setColumns(10);
		
		getContentPane().add(panelDetailIncident, BorderLayout.WEST);
		
		panelvalideRapporteur = new JPanel();
		panelvalideRapporteur.setBackground(new Color(0, 139, 139));
		panelDetailIncident.add(panelvalideRapporteur, "4, 16, fill, fill");
		
		valideBtn = new JButton("Valider");
		valideBtn.setBackground(new Color(50, 205, 50));
		Image val = new ImageIcon(this.getClass().getResource("/valide.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		valideBtn.setIcon(new ImageIcon(val));
		valideBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		valideBtn.setForeground(new Color(255, 255, 255));
		panelvalideRapporteur.add(valideBtn);
		
		panelAttenteResol = new JPanel();
		panelDetailIncident.add(panelAttenteResol, "4, 18");
		panelAttenteResol.setBackground(new Color(0, 139, 139));
		
		btnAttente = new JButton("Attente");
		Image att = new ImageIcon(this.getClass().getResource("/attente.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		panelAttenteResol.add(btnAttente);
		btnAttente.setIcon(new ImageIcon(att));
		btnAttente.setPreferredSize(new Dimension(130, 30));
		btnAttente.setForeground(new Color(255, 255, 255));
		btnAttente.setBackground(new Color(218, 165, 32));
		btnAttente.setFont(new Font("Verdana", Font.BOLD, 14));
		
		btnResolue = new JButton("Resolue");
		Image reso = new ImageIcon(this.getClass().getResource("/resolu.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		panelAttenteResol.add(btnResolue);
		btnResolue.setPreferredSize(new Dimension(130, 30));
		btnResolue.setIcon(new ImageIcon(reso));
		btnResolue.setForeground(new Color(255, 255, 255));
		btnResolue.setBackground(new Color(50, 205, 50));
		btnResolue.setFont(new Font("Verdana", Font.BOLD, 14));
		
		panelChangementEtat = new JPanel();
		panelChangementEtat.setBackground(new Color(0, 139, 139));
		panelChangementEtat.setForeground(Color.BLUE);
		panelChangementEtat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Autres actions ...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelChangementEtat, BorderLayout.CENTER);
		panelChangementEtat.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAssigner = new JPanel();
		panelAssigner.setBackground(new Color(0, 128, 128));
		panelChangementEtat.add(panelAssigner, BorderLayout.NORTH);
		
		labelAssigner = new JLabel("Assign\u00E9 \u00E0 :");
		labelAssigner.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelAssigner.setForeground(new Color(0, 0, 0));
		panelAssigner.add(labelAssigner);
		
		cBDeveloppeurs = new JComboBox<>();
		cBDeveloppeurs.setFont(new Font("Verdana", Font.BOLD, 13));
		cBDeveloppeurs.setPreferredSize(new Dimension(150, 22));
		panelAssigner.add(cBDeveloppeurs);
		
		buttonAssigner = new JButton("Assigner");
		Image assg = new ImageIcon(this.getClass().getResource("/assigned.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonAssigner.setIcon(new ImageIcon(assg));
		buttonAssigner.setBackground(new Color(0, 0, 255));
		buttonAssigner.setForeground(new Color(255, 255, 255));
		buttonAssigner.setFont(new Font("Verdana", Font.BOLD, 13));
		panelAssigner.add(buttonAssigner);
		
		JPanel panelCloturer = new JPanel();
		panelCloturer.setBackground(new Color(0, 139, 139));
		FlowLayout fl_panelCloturer = (FlowLayout) panelCloturer.getLayout();
		fl_panelCloturer.setAlignment(FlowLayout.RIGHT);
		panelChangementEtat.add(panelCloturer, BorderLayout.SOUTH);
		
		buttonCloturer = new JButton("Cl\u00F4turer");
		buttonCloturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonCloturer.setVerticalAlignment(SwingConstants.TOP);
		buttonCloturer.setToolTipText("Cl\u00F4turer le ticket incident ...");
		buttonCloturer.setPreferredSize(new Dimension(80, 25));
		buttonCloturer.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCloturer.setForeground(Color.WHITE);
		buttonCloturer.setFont(new Font("Verdana", Font.BOLD, 10));
		buttonCloturer.setBackground(Color.RED);
		panelCloturer.add(buttonCloturer);
		
		buttonOuvrir = new JButton("Ouvrir");
		buttonOuvrir.setVerticalAlignment(SwingConstants.TOP);
		buttonOuvrir.setToolTipText("Ouvrir \u00E0 nouveau le ticket incident ...");
		buttonOuvrir.setSize(new Dimension(130, 25));
		buttonOuvrir.setPreferredSize(new Dimension(80, 25));
		buttonOuvrir.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonOuvrir.setForeground(Color.WHITE);
		buttonOuvrir.setFont(new Font("Verdana", Font.BOLD, 10));
		buttonOuvrir.setBackground(Color.CYAN);
		panelCloturer.add(buttonOuvrir);
		JLabel Labeldetail = new JLabel("");
		panelChangementEtat.add(Labeldetail, BorderLayout.CENTER);
		
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public JButton getButtonAjouter() {
		return buttonAjouter;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void setButtonAjouter(JButton buttonAjouter) {
		this.buttonAjouter = buttonAjouter;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public JComboBox<String> getcBDeveloppeurs() {
		return cBDeveloppeurs;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void setcBDeveloppeurs(JComboBox<String> cBDeveloppeurs) {
		this.cBDeveloppeurs = cBDeveloppeurs;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void caherResoluAttente() {
		panelAttenteResol.setVisible(false);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public JButton getButtonAssigner() {
		return buttonAssigner;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addComboxListe(User user)
	{
		cBDeveloppeurs.addItem(user.getNom());
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void setButtonAssigner(JButton buttonAssigner) {
		this.buttonAssigner = buttonAssigner;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public JButton getButtonCloturer() {
		return buttonCloturer;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void setButtonCloturer(JButton buttonCloturer) {
		this.buttonCloturer = buttonCloturer;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public int getIdIncident() {
		return idIncident;
	}
/*-----------------------------------------------------------------------------------------------------------------------------------------*/
	public void addAjouterNouvelleNoteListener(ActionListener actionListener) {
		buttonAjouter.addActionListener(actionListener);
	}
	/*--------------------------------------------------------------------------------------------------------------------------------------*/	
	public void addQuitterListener(ActionListener actionListener) {
		bntquitter.addActionListener(actionListener);
	}
	public void addAttenteListener(ActionListener actionListener) {
		btnAttente.addActionListener(actionListener);
	}
	public void addResolueListener(ActionListener actionListener) {
		btnResolue.addActionListener(actionListener);
	}
	public void addCloturerListener(ActionListener actionListener) {
		buttonCloturer.addActionListener(actionListener);
	}
	public void addReouvertListener(ActionListener actionListener) {
		buttonOuvrir.addActionListener(actionListener);
	}
/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addAssignerListener(ActionListener actionListener) {
		buttonAssigner.addActionListener(actionListener);
	}
	public void addValiderRapportteurListener(ActionListener actionListener) {
		valideBtn.addActionListener(actionListener);
	}
public JTextArea gettADescription() {
		return tADescription;
	}
	public void settADescription(JTextArea tADescription) {
		this.tADescription = tADescription;
	}
	public JTextField gettFApplication() {
		return tFApplication;
	}
	public void settFApplication(JTextField tFApplication) {
		this.tFApplication = tFApplication;
	}
	public JButton getValideBtn() {
		return valideBtn;
	}
	public void setValideBtn(JButton valideBtn) {
		this.valideBtn = valideBtn;
	}
	@SuppressWarnings("deprecation")
	public void remplircache(Incident incident)
	{
		tFApplication.enable(true);
		tADescription.enable(true);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void afficherLesNotes(List<Note> notes) {
		
		String fluxEchanges = "";
		
		for (Note note : notes) {
			try {
				User user = Service.giveUser(note.getIdCreateur());
				fluxEchanges += "********************************************************|\n Date D'evoi : "+note.getDateCreation() + "|\n Identifiant de createur: " + note.getIdCreateur() + "|\n Type Utilisateur :" + user.getType() + "\n";
				fluxEchanges +=" Message : "+ note.getMessage() + "\n********************************************************\n";
			} catch (Exception e) {
				Utilitaire.displayErrorMessage("Error :" + e.getMessage());
			}
			
		}
		
		this.tANotes.setText(fluxEchanges);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public JTextField gettFIdentifiant() {
		return tFIdentifiant;
	}
	public void settFIdentifiant(JTextField tFIdentifiant) {
		this.tFIdentifiant = tFIdentifiant;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String gettFStatut() {
		return tFStatut.toString();
	}
	public void settFStatut(JTextField tFStatut) {
		this.tFStatut = tFStatut;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String gettFGravite() {
		return tFGravite.toString();
	}
	public void settFGravite(JTextField tFGravite) {
		this.tFGravite = tFGravite;
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String getNouvelleNote() {
		return this.tANouvelleNote.getText();
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void remplirDetail(Incident incident) {
			tFIdentifiant.setText(String.valueOf(incident.getId()));
			tFApplication.setText(incident.getApplication());
			tADescription.setText(incident.getDescription());
			tFStatut.setText(incident.getStatut());
			tFGravite.setText(incident.getGravite());
	}
	public void remplirDetails(Incident incident) {
		tFIdentifiant.setText(String.valueOf(incident.getId()));
		tFApplication.setEditable(true);
		tFApplication.setEnabled(true);
		tFApplication.setText(incident.getApplication());
		tADescription.setEditable(true);
		tADescription.setEnabled(true);
		tADescription.setText(incident.getDescription());
		tFStatut.setText(incident.getStatut());
		tFGravite.setText(incident.getGravite());
}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void fermerChamps()
	{
		panelChangementEtat.setVisible(false);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void ouverChamps()
	{
		panelChangementEtat.setVisible(true);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	@SuppressWarnings("deprecation")
	public void caherCombox()
	{
		cBDeveloppeurs.hide();
	}
	public void cacherValider()
	{
		panelvalideRapporteur.setVisible(false);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void effacerLeChampNouvelleNote() {
		this.tANouvelleNote.setText(null);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
}
