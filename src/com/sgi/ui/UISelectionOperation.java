package com.sgi.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.sgi.entities.TypeOperation;
import com.sgi.utils.Utilitaire;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;

public class UISelectionOperation extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JButton buttonQuitter;
	private JButton buttonValider;
	private JRadioButton radioVisualiserIncident;
	private JRadioButton radioCreerIncident;
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public UISelectionOperation() {
		getContentPane().setBackground(new Color(0, 139, 139));
		setBackground(new Color(0, 139, 139));
		setSize(new Dimension(524, 224));
		setResizable(false);
		setTitle("S\u00E9lectionner une op\u00E9ration");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCreerIncident = new JPanel();
		panelCreerIncident.setBackground(new Color(0, 139, 139));
		panelCreerIncident.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "S\u00E9lectionner une op\u00E9ration ...", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelCreerIncident, BorderLayout.NORTH);
		
		radioCreerIncident = new JRadioButton("Cr\u00E9er un incident");
		radioCreerIncident.setBackground(new Color(0, 128, 128));
		radioCreerIncident.setForeground(SystemColor.window);
		radioCreerIncident.setFont(new Font("Verdana", Font.BOLD, 14));
		radioCreerIncident.setSelected(true);
		panelCreerIncident.add(radioCreerIncident);
		
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(radioCreerIncident);
		
		JPanel panelVisualiserIncident = new JPanel();
		panelCreerIncident.add(panelVisualiserIncident);
		FlowLayout flowLayout_1 = (FlowLayout) panelVisualiserIncident.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		radioVisualiserIncident = new JRadioButton("Visualiser les incidents");
		radioVisualiserIncident.setBackground(new Color(0, 128, 128));
		radioVisualiserIncident.setForeground(SystemColor.window);
		radioVisualiserIncident.setFont(new Font("Verdana", Font.BOLD, 14));
		panelVisualiserIncident.add(radioVisualiserIncident);
		groupe.add(radioVisualiserIncident);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(0, 139, 139));
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
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
		buttonQuitter.setBackground(Color.RED);
		buttonQuitter.setForeground(SystemColor.window);
		buttonQuitter.setFont(new Font("Verdana", Font.BOLD, 14));
		panelButtons.add(buttonQuitter);
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addValiderlistener(ActionListener actionListener) {
		buttonValider.addActionListener(actionListener);		
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public void addQuitterlistener(ActionListener actionListener) {
		buttonQuitter.addActionListener(actionListener);		
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
	public TypeOperation getTypeOperation() {		
		if (radioCreerIncident.isSelected()) {
			return TypeOperation.CREER_INCIDENT;
		} else {
			return TypeOperation.VISUALISER_INCIDENT;
		}
	}
	/*---------------------------------------------------------------------------------------------------------------------------*/	
}
