package com.sgi.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sgi.entities.User;
import com.sgi.utils.Utilitaire;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.Box;

public class UIAuthentification extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPasswordField passwordPF;
	private JTextField loginTF;
	private User userconnected;
	private JButton buttonQuitter ;
	private JButton buttonValider;
	
	public UIAuthentification()  {
		getContentPane().setBackground(new Color(0, 139, 139));
		setFont(new Font("Verdana", Font.PLAIN, 14));
		setBackground(Color.GRAY);
		setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		passwordLabel.setBackground(Color.BLUE);
		passwordLabel.setBounds(235, 91, 78, 18);
		getContentPane().add(passwordLabel);
		
		passwordPF = new JPasswordField();
		passwordPF.setFont(new Font("Verdana", Font.PLAIN, 12));
		passwordPF.setEchoChar('*');
		passwordPF.setColumns(15);
		passwordPF.setBounds(352, 90, 171, 22);
		getContentPane().add(passwordPF);
		
		JLabel loginLabel = new JLabel("Login :");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		loginLabel.setBackground(Color.BLUE);
		loginLabel.setBounds(235, 44, 49, 18);
		getContentPane().add(loginLabel);
		
		loginTF = new JTextField();
		loginTF.setFont(new Font("Verdana", Font.PLAIN, 12));
		loginTF.setColumns(15);
		loginTF.setBounds(352, 43, 171, 22);
		getContentPane().add(loginTF);
		
		JLabel lblNewLabel = new JLabel("");
		Image secu = new ImageIcon(this.getClass().getResource("/secu.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(secu));
		lblNewLabel.setBounds(34, 24, 161, 161);
		getContentPane().add(lblNewLabel);
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(new Color(0, 139, 139));
		panelBtn.setBounds(205, 150, 318, 35);
		getContentPane().add(panelBtn);
		
		buttonValider = new JButton("Valider");
		Image val = new ImageIcon(this.getClass().getResource("/valide.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		buttonValider.setIcon(new ImageIcon(val));
		buttonValider.setSize(new Dimension(115, 25));
		buttonValider.setPreferredSize(new Dimension(130, 30));
		buttonValider.setForeground(Color.WHITE);
		buttonValider.setFont(new Font("Verdana", Font.BOLD, 14));
		buttonValider.setBackground(Color.GREEN);
		panelBtn.add(buttonValider);
		
		buttonQuitter = new JButton("Quitter");
		Image quit = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBtn.add(horizontalStrut);
		buttonQuitter.setIcon(new ImageIcon(quit));
		buttonQuitter.setSize(new Dimension(109, 25));
		buttonQuitter.setPreferredSize(new Dimension(130, 30));
		buttonQuitter.setForeground(Color.WHITE);
		buttonQuitter.setFont(new Font("Verdana", Font.BOLD, 14));
		buttonQuitter.setBackground(Color.RED);
		panelBtn.add(buttonQuitter);
		initComponents ();
		
	}
	
	private void initComponents () {		
		setPreferredSize(new Dimension(300, 145));
		setSize(new Dimension(547, 234));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Connexion");
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, this.getSize());
	}

	public void montrer () {
		setVisible (true);
	}
	
	public void cacher () {
		setVisible (false);
	}

	public void addValiderListener(ActionListener actionListener) {
		buttonValider.addActionListener(actionListener);
	}

	public void addQuitterListener(ActionListener actionListener) {
		buttonQuitter.addActionListener(actionListener);
	}
	
	public User getUserConnected() {
		return userconnected;
	}
	
	public void setUserConnected(User connected) {
		this.userconnected = connected;
	}

	public String getLogin() {		
		return loginTF.getText();		
	}

	public String getPassword() {
		return String.valueOf(this.passwordPF.getPassword());
	}

	public void clear() {
		this.loginTF.setText(null);
		this.passwordPF.setText(null);
	}
}