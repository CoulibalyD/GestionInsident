package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.sgi.utils.Utilitaire;
import java.awt.SystemColor;
/*---------------------------------------------------------------------------------------------------------------------------*/	
public class UICreerIncident extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField tFApplication;
	private JButton buttonEffacer;
	private JButton buttonValider;
	private JButton buttonQuitter;
	private JTextArea tADescription;
	private JComboBox<String> cBGravite;
	
	public UICreerIncident() {
		setBackground(new Color(0, 139, 139));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(623, 329));
		setResizable(false);
		setTitle("Cr\u00E9er un incident");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBorder(new TitledBorder(null, "D\u00E9tails de l'incident ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel labelApplication = new JLabel("Application :");
		labelApplication.setForeground(SystemColor.activeCaptionText);
		labelApplication.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelApplication.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelApplication, "2, 2, right, default");
		
		tFApplication = new JTextField();
		tFApplication.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel.add(tFApplication, "4, 2, fill, default");
		tFApplication.setColumns(10);
		
		JLabel labelDescription = new JLabel("Description :");
		labelDescription.setForeground(SystemColor.activeCaptionText);
		labelDescription.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelDescription, "2, 4");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, "4, 4, fill, fill");
		
		tADescription = new JTextArea();
		tADescription.setFont(new Font("Verdana", Font.PLAIN, 14));
		scrollPane.setViewportView(tADescription);
		
		JLabel labelGravite = new JLabel("Niveau de gravit\u00E9 :");
		labelGravite.setForeground(SystemColor.activeCaptionText);
		labelGravite.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelGravite.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelGravite, "2, 6, right, default");
		
		cBGravite = new JComboBox<>();
		cBGravite.setFont(new Font("Verdana", Font.BOLD, 11));
		cBGravite.addItem("LOW");
		cBGravite.addItem("HIGH");
		cBGravite.addItem("CRITICAL");		
		panel.add(cBGravite, "4, 6, fill, default");
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(0, 139, 139));
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		buttonEffacer = new JButton("Effacer");
		Image eff = new ImageIcon(this.getClass().getResource("/clear.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonEffacer.setIcon(new ImageIcon(eff));
		buttonEffacer.setBackground(new Color(255, 0, 0));
		buttonEffacer.setForeground(SystemColor.window);
		buttonEffacer.setFont(new Font("Verdana", Font.BOLD, 14));
		panelButtons.add(buttonEffacer);
		
		buttonValider = new JButton("Valider");
		Image val = new ImageIcon(this.getClass().getResource("/valide.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonValider.setIcon(new ImageIcon(val));
		buttonValider.setForeground(SystemColor.window);
		buttonValider.setBackground(Color.GREEN);
		buttonValider.setFont(new Font("Verdana", Font.BOLD, 14));
		panelButtons.add(buttonValider);
		
		buttonQuitter = new JButton("Quitter");
		Image quit = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonQuitter.setIcon(new ImageIcon(quit));
		buttonQuitter.setBackground(new Color(255, 165, 0));
		buttonQuitter.setForeground(SystemColor.window);
		buttonQuitter.setFont(new Font("Verdana", Font.BOLD, 14));
		panelButtons.add(buttonQuitter);
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addEffacerListener(ActionListener actionListener) {
		this.buttonEffacer.addActionListener(actionListener);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addValiderListener(ActionListener actionListener) {
		this.buttonValider.addActionListener(actionListener);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addQuitterListener(ActionListener actionListener) {
		this.buttonQuitter.addActionListener(actionListener);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String getDescription() {
		return tADescription.getText();
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String getApplication() {
		return this.tFApplication.getText();
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public String getGravite() {
		return (String) cBGravite.getSelectedItem();
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void nettoyer() {
		tFApplication.setText(null);
		tADescription.setText(null);
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
}
